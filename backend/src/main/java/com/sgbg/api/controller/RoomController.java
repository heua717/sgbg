package com.sgbg.api.controller;

import com.sgbg.api.request.RoomReq;
import com.sgbg.api.response.BaseResponseBody;
import com.sgbg.api.response.RoomListRes;
import com.sgbg.api.response.RoomRes;
import com.sgbg.blockchain.service.SingleBungleService;
import com.sgbg.common.exception.NotFoundException;
import com.sgbg.common.util.CookieUtil;
import com.sgbg.domain.Room;
import com.sgbg.service.RedisService;
import com.sgbg.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Tag(name = "Room API", description = "방 생성, 카테고리별 방 목록 조회, 방 상세정보 조회 기능 제공")
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    RedisService redisService;

    @Autowired
    SingleBungleService singleBungleService;

    @Autowired
    CookieUtil cookieUtil;


    @Operation(summary = "방 생성 메서드")
    @Parameters()
    @PostMapping("/create")
    public ResponseEntity createRoom(@RequestBody RoomReq roomReq, HttpServletRequest request) {
        Long userId = cookieUtil.getUserIdByToken(request);

        try {
            String contractAddress = singleBungleService.createRoom(
                    userId, ChronoUnit.DAYS.between(LocalDateTime.now(), roomReq.getEndDate()), roomReq.getPrice());

            roomService.createRoom(roomReq, userId, contractAddress);

            // TODO: wallet 잔액 부족한 경우
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(2010, "Accepted"));
    }

    // 요청 url = /room?page=pageNum&size=pageSize&sort=desc
    @Operation(summary = "전체 방 목록 조회 메서드")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "방 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = RoomListRes.class)))
    )
    @GetMapping("")
    public ResponseEntity<? extends RoomListRes> getRoomList() {
        List<Room> roomList = roomService.getRoomList();

        return ResponseEntity.status(200).body(RoomListRes.of(2000, "Success", roomList));
    }

    @Operation(summary = "방 상세정보 조회 메서드")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "방 상세정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = RoomRes.class))),
            @ApiResponse(responseCode = "500", description = "방 조회 실패")
    })
    @GetMapping("/{roomId}")
    public ResponseEntity<? extends RoomRes> getRoom(@PathVariable Long roomId) {
        Room room = roomService.getRoom(roomId);
        if (room == null) {
            throw new NotFoundException("Room Not Found");
        }
//        System.out.println(room);

        return ResponseEntity.status(200).body(RoomRes.of(2000, "Success", room));

    }

    @Operation(summary = "대분류 별 방 목록 조회")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "대분류 방 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = RoomListRes.class)))
    )
    @GetMapping("/parentcategory/{parentCategory}")
    public ResponseEntity<? extends RoomListRes> getParentCategoryList(@PathVariable String parentCategory) {
        List<Room> roomList = roomService.getParentRoomList(parentCategory);

        return ResponseEntity.status(200).body(RoomListRes.of(2000, "Success", roomList));
    }

    @Operation(summary = "소분류 별 방 목록 조회")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "소분류 방 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = RoomListRes.class)))
    )
    @GetMapping("/childcategory/{childCategory}")
    public ResponseEntity<? extends RoomListRes> getChildCategoryList(@PathVariable String childCategory) {
        List<Room> roomList = roomService.getChildRoomList(childCategory);

        return ResponseEntity.status(200).body(RoomListRes.of(2000, "Success", roomList));
    }


}
