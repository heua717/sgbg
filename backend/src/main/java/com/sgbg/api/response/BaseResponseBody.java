package com.sgbg.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BaseResponseBody")
public class BaseResponseBody {
    @ApiModelProperty(name = "응답 코드", example = "2000")
    Integer statusCode = null;

    @ApiModelProperty(name = "응답 메시지", example = "Success")
    String message = null;

    public BaseResponseBody() {
    }

    public BaseResponseBody(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public BaseResponseBody(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static BaseResponseBody of(Integer statusCode, String message) {
        BaseResponseBody body = new BaseResponseBody();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }
}
