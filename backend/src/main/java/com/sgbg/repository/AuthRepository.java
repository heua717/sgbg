package com.sgbg.repository;

import com.sgbg.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByKakaoNumber(Long kakaoNumber);
}

//public class AuthRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public void save(Auth auth) {
//        em.persist(auth);
//    }
//
//    public List<Auth> findByKakaoNumber(Long kakaoNumber) {
//        return em.createQuery("select a from Auth a where a.kakaoNumber = :kakaoNumber", Auth.class)
//                .setParameter("kakaoNumber", kakaoNumber).getResultList();
//    }
//
//}