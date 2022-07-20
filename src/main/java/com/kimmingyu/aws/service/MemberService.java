package com.kimmingyu.aws.service;

import com.kimmingyu.aws.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    List<Member> findAll();

    Member findByEmail(String email);

    Member findByPhone(String phone);

    Member findByIdx(Long idx);

    List<Member> findAllByOrderByNameDesc();

    List<Member> findAllByOrderByIdxDesc();

    Member saveOrUpdateMember(Member member);

    void deleteMemberById(String id);
}
