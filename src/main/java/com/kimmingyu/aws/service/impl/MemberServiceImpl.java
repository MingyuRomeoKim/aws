package com.kimmingyu.aws.service.impl;

import com.kimmingyu.aws.model.Member;
import com.kimmingyu.aws.repository.MemberRepository;
import com.kimmingyu.aws.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Member findByPhone(String phone) {
        return memberRepository.findByPhone(phone);
    }

    @Override
    public Member findByIdx(Long idx) { return null; }

    @Override
    public List<Member> findAllByOrderByIdxDesc() { return null; }

    @Override
    public List<Member> findAllByOrderByNameDesc() {
        return memberRepository.findAllByOrderByNameDesc();
    }

    @Override
    public Member saveOrUpdateMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMemberById(String id) {
        memberRepository.deleteById(id);
    }
}
