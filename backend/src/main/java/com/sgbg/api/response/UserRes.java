package com.sgbg.api.response;


import com.sgbg.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRes extends BaseResponseBody {

    @Schema(name = "user", description = "회원 정보")
    BaseUserRes user;

    public static UserRes of(Integer statusCode, String message, String kakaoId, User user) {
        UserRes userRes = new UserRes();
        userRes.setUser(user);
        userRes.setStatusCode(statusCode);
        userRes.setMessage(message);
        return userRes;
    }

    public void setUser(User user) {
        this.user = BaseUserRes.of(user);
    }
}
