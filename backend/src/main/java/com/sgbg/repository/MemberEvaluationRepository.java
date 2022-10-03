package com.sgbg.repository;

import com.sgbg.domain.MemberEvaluation;
import com.sgbg.repository.interfaces.MemberEvaluationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEvaluationRepository extends JpaRepository<MemberEvaluation, Long>, MemberEvaluationRepositoryCustom {



}
