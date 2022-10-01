package com.sgbg.api.controller;

import com.sgbg.api.request.CommentReq;
import com.sgbg.api.response.BaseResponseBody;
import com.sgbg.api.response.CommentRes;
import com.sgbg.domain.Comment;
import com.sgbg.service.interfaces.ICommentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;



    @PostMapping("/create")
    public  ResponseEntity createComment(@RequestBody CommentReq commentReq) throws Exception {
        String result = commentService.createComment(commentReq);
        return ResponseEntity.status(200).body(BaseResponseBody.of(2010, result));
    }


    @PostMapping("/{commentId}/update")
    public ResponseEntity updateComment(@PathVariable Long commentId, @RequestBody @Valid String content) throws Exception {

        JSONObject parser =new JSONObject(content);
        String parsedContent = parser.getString("content");
        String result = commentService.updateComment(commentId, parsedContent);
        return ResponseEntity.status(200).body(BaseResponseBody.of(2000, result));
    }


    @PostMapping("/{commentId}/delete")
    public ResponseEntity deleteComment(@PathVariable Long commentId) throws Exception {
        String result = commentService.deleteComment(commentId);

        if(result.equals("Success")){
            return ResponseEntity.status(200).body(BaseResponseBody.of(2000, result));
        }
        return ResponseEntity.status(404).body(BaseResponseBody.of(4040, result));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity detailComment(@PathVariable Long roomId) throws Exception {

        List<CommentRes> commentRes = commentService.detailComment(roomId);
      
        return ResponseEntity.status(200).body(commentRes);
    }


}
