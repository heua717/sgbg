package com.sgbg.api.controller;

import com.sgbg.api.request.BaseMemberEvaluationReq;
import com.sgbg.api.request.HostEvalutionReq;
import com.sgbg.api.request.MemberEvaluationReq;
import com.sgbg.api.response.BaseResponseBody;
import com.sgbg.blockchain.service.interfaces.ISingleBungleService;
import com.sgbg.common.util.exception.NotFoundException;
import com.sgbg.common.util.CookieUtil;
import com.sgbg.domain.Room;
import com.sgbg.domain.User;
import com.sgbg.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Tag(name = "Evaluation API", description = "방장 평가, 참여자 평가 기능 제공")
@RestController
@RequestMapping("/eval")
public class EvaluationController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    HostEvaluationService hostEvaluationService;

    @Autowired
    MemberEvaluationService memberEvaluationService;

    @Autowired
    ISingleBungleService singleBungleService;

    @Autowired
    CookieUtil cookieUtil;

    @Operation(summary = "방장 평가 조회 메서드")
    @GetMapping("/host/{roomId}")
    public void getHostEvaluation() {

    }

    @Operation(summary = "방장 평가 메서드")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "방장 평가 성공",
                    content = @Content(schema = @Schema(implementation = BaseResponseBody.class))),
            @ApiResponse(responseCode = "500", description = "방장 평가 실패")
    })
    @PostMapping("/host/{roomId}")
    public ResponseEntity<? extends BaseResponseBody> hostEvaluation(@PathVariable String roomId,
                                                                     @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "모임 성공/실패 여부 확인", required = true,
                                                                             content = @Content(schema = @Schema(implementation = HostEvalutionReq.class))) HostEvalutionReq hostEvaluation,
                                                                     HttpServletRequest request) {
        Room room = roomService.getRoom(Long.valueOf(roomId));
        if (room == null) {
            throw new NotFoundException("Room Not Found");
        }

        Long userId = cookieUtil.getUserIdByToken(request);
        User user = userService.getUserById(userId);

        Boolean isSuccess = hostEvaluation.getIsSuccess();

        hostEvaluationService.createEvaluation(user, room, isSuccess);

        // TODO: 스마트 컨트랙트 - 성공/실패 평가 메서드 호출
//        singleBungleService.

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(2010, "Success"));
    }

    @Operation(summary = "참여자 평가 조회 메서드")
    @GetMapping("/member/{roomId}")
    public void getMemberEvaluation() {

    }

    @Operation(summary = "참여자 평가 메서드")
    @PostMapping("/member/{roomId}")
    public ResponseEntity<? extends BaseResponseBody> memberEvaluation(@PathVariable String roomId,
                                                                       @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "모임 참여자에 대한 평가", required = true,
                                                                               content = @Content(schema = @Schema(implementation = MemberEvaluationReq.class)))
                                                                       MemberEvaluationReq memberEvaluationInfo,
                                                                       HttpServletRequest request) {
        Room room = roomService.getRoom(Long.valueOf(roomId));
        if (room == null) {
            throw new NotFoundException("Room Not Found");
        }

        Long userId = cookieUtil.getUserIdByToken(request);
        User evaluator = userService.getUserById(userId);

        List<BaseMemberEvaluationReq> memberEvaluations = memberEvaluationInfo.getEvaluations();
        if (memberEvaluations == null) {
            throw new NotFoundException("Evaluation Request Not Received");
        }

        double scoreSum = 0, totalScore = 0;
        int avgEvaluateScore = evaluator.getAvgEvaluateScore();
        for (BaseMemberEvaluationReq memberEvaluation : memberEvaluations) {
            Long kakaoId = memberEvaluation.getKakaoId();
            User user = authService.isUser(String.valueOf(kakaoId)).getUser();

            // TODO: create transaction and get transaction id
            memberEvaluationService.createEvaluation(memberEvaluation, room, evaluator, user);

            scoreSum += memberEvaluation.getReview().getScore() - avgEvaluateScore;
        }

        totalScore = scoreSum / memberEvaluations.size(); // 평균 점수 + avg evaluate score "나"

        // TODO: 방장의 avgEvaluateScore 변경, 참여자들의 신뢰도 결과 반영

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(2010, "Success"));
    }

}
