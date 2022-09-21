package com.sgbg.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("AuthResponse")
public class AuthRes extends BaseResponseBody {

    @ApiModelProperty(name = "Access Token")
    private String accessToken;

    @ApiModelProperty(name = "is completed")
    private boolean isCompleted;

    @ApiModelProperty(name = "user id")
    private Long userId;

    public static AuthRes of(Integer statusCode, String message, String accessToken, boolean isCompleted, Long userId) {
        AuthRes res = new AuthRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAccessToken(accessToken);
        res.setCompleted(isCompleted);
        res.setUserId(userId);
        return res;
    }

}
