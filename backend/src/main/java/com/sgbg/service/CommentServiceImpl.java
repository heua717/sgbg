package com.sgbg.service;

import com.sgbg.api.request.CommentReq;
import com.sgbg.api.response.CommentRes;
import com.sgbg.domain.Auth;
import com.sgbg.domain.Comment;
import com.sgbg.domain.Room;
import com.sgbg.domain.User;
import com.sgbg.repository.AuthRepository;
import com.sgbg.repository.RoomRepository;
import com.sgbg.repository.UserRepository;
import com.sgbg.repository.interfaces.CommentRepository;
import com.sgbg.service.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.TIME;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;


    @Override
    @Transactional
    public String createComment(CommentReq commentReq, Long userId) throws Exception {

        Comment comment = new Comment();
        // 임의로 저장한 값 -> token 이용해서 useId 가져오기
        comment.setUserId(userId);
        Room room = roomRepository.findById(commentReq.getRoomId()).orElseThrow();
        comment.setRoom(room);
        comment.setContent(commentReq.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        return "Accepted";
    }

    @Override
    public String updateComment(Long commentId, String content) throws Exception {
        Comment comment = commentRepository.findById(commentId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        comment.setContent(content);
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

    @Override
    public List<CommentRes> detailComment(Long roomId) throws Exception {

        List<Comment> commentList = commentRepository.findAllByRoom_RoomId(roomId);
        List<CommentRes> commentResList = new ArrayList<>();

        for(Comment comment : commentList){

            CommentRes commentRes = CommentRes.detailComment(comment);

            Optional<User> user = userRepository.findById(comment.getUserId());


            commentRes.setUsername(user.get().getName());
           commentRes.setUserScore(user.get().getMemberScore());
            commentRes.setHostScore(user.get().getHostScore());

            Date createdDate = java.sql.Timestamp.valueOf(comment.getCreatedAt());
            long regTime = createdDate.getTime();
            long curTime = System.currentTimeMillis();
            long diffTime = (curTime - regTime) / 1000;

            String msg = "";

            if (diffTime < 60) {
                // sec
                msg = diffTime + "초 전";
            } else if ((diffTime /= 60) < 60) {
                // min
                msg = diffTime + "분 전";
            } else if ((diffTime /= 60) < 24) {
                // hour
                msg = (diffTime) + "시간 전";
            } else if ((diffTime /= 24) < 30) {
                // day
                msg = (diffTime) + "일 전";
            } else if ((diffTime /= 30) < 12) {
                // day
                msg = (diffTime) + "달 전";
            } else {
                msg = (diffTime) + "년 전";
            }
            commentRes.setCreatedAt(msg);

            Optional<Auth> auth = authRepository.findByUser_userId(comment.getUserId());

            commentRes.setKakaoNumber(auth.get().getKakaoNumber());


           commentResList.add(commentRes);
        }

        return commentResList;
    }
}



