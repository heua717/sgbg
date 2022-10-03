package com.sgbg.repository;

import com.sgbg.domain.MemberEvaluation;
import com.sgbg.domain.Room;
import com.sgbg.domain.User;
import com.sgbg.repository.interfaces.MemberEvaluationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberEvaluationRepository extends JpaRepository<MemberEvaluation, Long>, MemberEvaluationRepositoryCustom {

    List<MemberEvaluation> findMemberEvaluationByEvaluatorAndRoom(User evaluator, Room room);

}
