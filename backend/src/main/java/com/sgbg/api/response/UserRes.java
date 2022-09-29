package com.sgbg.api.response;


import io.swagger.v3.oas.annotations.media.Schema;

public class UserRes {
    @Schema(name = "이름", example = "Bungle")
    private String name;

    @Schema(name = "이메일", example = "sgbg@sgbg.com")
    private String email;

    @Schema(name = "방장 신뢰도 점수", example = "95")
    private int hostScore;

    @Schema(name = "참가자 점수", example = "84")
    private int memberScore;

//    public static UserRes of(String name, String email, int hostScore, int memberScore) {
//        this.name = name;
//        this.email = email;
//        this.hostScore = hostScore;
//        this.memberScore = memberScore;
//    }
}
