package com.sgbg.service.interfaces;

import com.sgbg.api.request.BaseMemberEvaluationReq;
import com.sgbg.domain.MemberEvaluation;
import com.sgbg.domain.Room;
import com.sgbg.domain.User;

public interface IMemberEvaluationService {


    MemberEvaluation createEvaluation(BaseMemberEvaluationReq memberEvaluationReq, Room room, User evaluator, User user);

    Boolean checkMemberEvaluation(User evaluator, Room room);
}
