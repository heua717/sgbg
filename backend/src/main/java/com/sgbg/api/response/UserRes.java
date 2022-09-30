package com.sgbg.api.response;


import com.sgbg.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRes extends BaseResponseBody {

    @Schema(description = "회원 번호", example = "1234567")
    private String kakaoId;

    @Schema(description = "이름", example = "Bungle")
    private String name;

    @Schema(description = "이메일", example = "sgbg@sgbg.com")
    private String email;

    @Schema(description = "방장 신뢰도 점수", example = "95")
    private int hostScore;

    @Schema(description = "참가자 점수", example = "84")
    private int memberScore;

    public static UserRes createUser(User user) {
        UserRes userRes = new UserRes();
        userRes.setName(user.getName());
        userRes.setEmail(user.getEmail());
        userRes.setHostScore(user.getHostScore());
        userRes.setMemberScore(user.getMemberScore());
        return userRes;
    }

    public static UserRes of(Integer statusCode, String message, String kakaoId, User user) {
        UserRes userRes = createUser(user);
        userRes.setKakaoId(kakaoId);
        userRes.setStatusCode(statusCode);
        userRes.setMessage(message);
        return userRes;
    }

}
