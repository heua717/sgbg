package com.sgbg.service;

import com.sgbg.api.request.CommentReq;
import com.sgbg.domain.Comment;
import com.sgbg.domain.Room;
import com.sgbg.repository.RoomRepository;
import com.sgbg.repository.interfaces.CommentRepository;
import com.sgbg.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional
    public String createComment(CommentReq commentReq) throws Exception {

        Comment comment = new Comment();
        // 임의로 저장한 값 -> token 이용해서 useId 가져오기
        comment.setUserId(1L);
        Room room = roomRepository.findById(commentReq.getRoomId()).orElseThrow();
        comment.setRoom(room);
        comment.setContent(commentReq.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        return "Accepted";
    }

    @Override
    public String updateComment(CommentReq commentReq) throws Exception {
        Comment comment = commentRepository.findById(commentReq.getCommentId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        comment.setContent(commentReq.getContent());
        commentRepository.save(comment);

        return "Success";
    }

    @Override
    @Transactional
    public String deleteComment(Long commentId) throws Exception {

        if(commentRepository.existsById(commentId)){
            commentRepository.deleteById(commentId);
            return "Success";
        }else{
            return "Fail";
        }
    }
}
