package com.sgbg.api.request;

import com.sgbg.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CommentReq {
    private Long commentId;
    private  String content;
    private Long roomId;
}
