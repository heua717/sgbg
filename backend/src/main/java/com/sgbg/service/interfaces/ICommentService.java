package com.sgbg.service.interfaces;

import com.sgbg.api.request.CommentReq;
import com.sgbg.api.response.CommentRes;

import java.util.List;

public interface ICommentService {

String createComment(CommentReq commentReq) throws Exception;

String updateComment(Long commentId, String content) throws Exception;
String deleteComment(Long commentId) throws Exception;

List<CommentRes> detailComment(Long roomId) throws Exception;
}
