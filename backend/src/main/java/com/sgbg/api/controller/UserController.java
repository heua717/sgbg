package com.sgbg.api.controller;

import com.sgbg.api.response.RoomListRes;
import com.sgbg.api.response.UserRes;
import com.sgbg.blockchain.service.interfaces.ISingleBungleService;
import com.sgbg.common.util.exception.NotFoundException;
import com.sgbg.common.util.CookieUtil;
import com.sgbg.domain.Auth;
import com.sgbg.domain.Participation;
import com.sgbg.domain.Room;
import com.sgbg.domain.User;
import com.sgbg.service.interfaces.IAuthService;
import com.sgbg.service.interfaces.IHostEvaluationService;
import com.sgbg.service.interfaces.IMemberEvaluationService;
import com.sgbg.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "User API", description = "회원 등록, 회원 정보 조회 등의 기능 제공")
@RestController
@RequestMapping("/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService userService;
    private IAuthService authService;

    private IHostEvaluationService hostEvaluationService;

    private IMemberEvaluationService memberEvaluationService;

    private ISingleBungleService singleBungleService;
    private CookieUtil cookieUtil;

    @Autowired
    public UserController(IUserService userService, IAuthService authService,
                          IHostEvaluationService hostEvaluationService, IMemberEvaluationService memberEvaluationService,
                          ISingleBungleService singleBungleService, CookieUtil cookieUtil) {
        Assert.notNull(userService, "userService 개체가 반드시 필요!");
        this.userService = userService;
        this.authService = authService;
        this.hostEvaluationService = hostEvaluationService;
        this.memberEvaluationService = memberEvaluationService;
        this.singleBungleService = singleBungleService;
        this.cookieUtil = cookieUtil;
    }

    @GetMapping("/{kakaoId}")
    @Operation(summary = "회원 정보 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserRes.class))),
            @ApiResponse(responseCode = "500", description = "회원 정보 조회 실패 - 존재하지 않는 계정")
    })
    public ResponseEntity<? extends UserRes> getUser(@PathVariable String kakaoId) {
        Auth auth = authService.isUser(kakaoId);
        if (auth == null) {
            throw new NotFoundException(kakaoId + " 회원 정보를 찾을 수 없습니다.");
        }
        User user = auth.getUser();
        if (user == null) {
            throw new NotFoundException(kakaoId + " 회원 정보를 찾을 수 없습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(UserRes.of(2000, "Success", kakaoId, user));
    }

    @GetMapping("/room")
    @Operation(summary = "내가 참여한 방 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "참여한 방 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = RoomListRes.class))),
            @ApiResponse(responseCode = "500", description = "")
    })
    public ResponseEntity<? extends RoomListRes> getRooms(
            @RequestParam(value = "host") String host,
            HttpServletRequest request) {
        Long userId = cookieUtil.getUserIdByToken(request);
        List<Room> rooms = userService.getMyRooms(userId, Boolean.parseBoolean(host));

        User user = userService.getUserById(userId);
        List<Boolean> hostReviews = new ArrayList<>();
        List<Boolean> memberReviews = new ArrayList<>();

        for (Room room: rooms) {
            Boolean hostReview = hostEvaluationService.checkHostEvaluation(user, room);
            Boolean memberReview = memberEvaluationService.checkMemberEvaluation(user, room);
            hostReviews.add(hostReview);
            memberReviews.add(memberReview);
        }

        return ResponseEntity.status(HttpStatus.OK).body(RoomListRes.createMyRoomList(2000, "Success", rooms, hostReviews, memberReviews));
    }

    @GetMapping("/room/{roomId}/add")
    @Operation(summary = "방 입장하기")
    // TODO: API Response 추가
    public void addRoom(@PathVariable String roomId, HttpServletRequest request) {
        Long userId = cookieUtil.getUserIdByToken(request);
        User user = userService.getUserById(userId);
        if (user == null) {
           throw new NotFoundException("User Not Found");
        }

        Participation participation = userService.addMyRoom(userId, Long.valueOf(roomId));
        Room room = participation.getRoom();

        try {
            // TODO: wallet 잔액 부족한 경우

            singleBungleService.enterRoom(userId, room.getHostId(), room.getContractAddress(), room.getPrice());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: 방 나가기
    @GetMapping("/room/{roomId}/delete")
    @Operation(summary = "방 나가기")
    // TODO: API Response 추가
    public void deleteRoom(@PathVariable String roomId, HttpServletRequest request) {
        Long userId = cookieUtil.getUserIdByToken(request);
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("User Not Found");
        }

        try {
            Room room = userService.deleteMyRoom(userId, Long.valueOf(roomId));
            singleBungleService.exitRoom(userId, room.getHostId(), room.getContractAddress(), room.getPrice());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
