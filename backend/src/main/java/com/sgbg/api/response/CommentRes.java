package com.sgbg.api.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRes extends BaseResponseBody {

    private String content;
    private Integer commentId;
    private String createdAt;
    private String username;
    private Integer userScore;

    public static CommentRes of(Integer statusCode, String message){
        CommentRes res = new CommentRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }


    public static CommentRes detailComment(Integer statusCode, String message, Integer commentId, String content, String createdAt, String username, Integer userScore) {
        CommentRes res = new CommentRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setCommentId(commentId);
        res.setContent(content);
        res.setCreatedAt(createdAt);
        res.setUsername(username);
        res.setUserScore(userScore);
        return res;
    }




}
