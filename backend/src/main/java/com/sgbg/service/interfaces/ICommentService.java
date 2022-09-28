package com.sgbg.service.interfaces;

import com.sgbg.api.request.CommentReq;

public interface ICommentService {

String createComment(CommentReq commentReq) throws Exception;

String updateComment(CommentReq commentReq) throws Exception;
String deleteComment(Long commentId) throws Exception;
}
