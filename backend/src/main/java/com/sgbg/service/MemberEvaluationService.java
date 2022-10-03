package com.sgbg.service;

import com.sgbg.api.request.BaseMemberEvaluationReq;
import com.sgbg.domain.MemberEvaluation;
import com.sgbg.domain.Room;
import com.sgbg.domain.User;
import com.sgbg.repository.MemberEvaluationRepository;
import com.sgbg.service.interfaces.IMemberEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberEvaluationService implements IMemberEvaluationService {

    @Autowired
    MemberEvaluationRepository memberEvaluationRepository;

    @Override
    public MemberEvaluation createEvaluation(BaseMemberEvaluationReq memberEvaluationReq, Room room, User evaluator, User user) {
        // TODO: set transaction id
        MemberEvaluation memberEvaluation = MemberEvaluation.builder()
                .room(room)
                .evaluator(evaluator)
                .user(user)
                .score(memberEvaluationReq.getReview().getScore())
//                .transactionId()
                .build();

        memberEvaluation.addMemberEvaluation(room, evaluator, user);
        memberEvaluationRepository.save(memberEvaluation);

        return memberEvaluation;
    }
}
