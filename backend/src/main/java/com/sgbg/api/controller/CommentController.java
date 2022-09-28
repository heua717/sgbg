package com.sgbg.api.controller;

import com.sgbg.api.request.CommentReq;
import com.sgbg.api.response.CommentRes;
import com.sgbg.domain.Comment;
import com.sgbg.service.interfaces.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/create")
    public  ResponseEntity<? extends  CommentRes> createComment(@RequestBody CommentReq commentReq) throws Exception {
        String result = commentService.createComment(commentReq);
        return ResponseEntity.status(200).body(CommentRes.of(2010, result));
    }


    @PostMapping("/update")
    public ResponseEntity<? extends CommentRes> updateComment(@Valid @RequestBody CommentReq commentReq) throws Exception {
        String result = commentService.updateComment(commentReq);
        return ResponseEntity.status(200).body(CommentRes.of(2000, result));
    }


    @PostMapping("/{comment_id}/delete")
    public ResponseEntity<? extends CommentRes> deleteComment(@PathVariable Long commentId) throws Exception {
        String result = commentService.deleteComment(commentId);

        if(result.equals("Success")){
            return ResponseEntity.status(204).body(CommentRes.of(2040, result));
        }
        return ResponseEntity.status(404).body(CommentRes.of(4040, result));
    }
}
