package com.kimmingyu.aws.service.impl;

import com.kimmingyu.aws.model.Member;
import com.kimmingyu.aws.model.Role;
import com.kimmingyu.aws.repository.MemberRepository;
import com.kimmingyu.aws.repository.RoleRepository;
import com.kimmingyu.aws.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberServiceImpl implements MemberService,UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public Member findByIdx(Long idx) {
        return null;
    }

    @Override
    public List<Member> findAllByOrderByIdxDesc() {
        return null;
    }

    @Override
    public List<Member> findAllByOrderByNameDesc() {
        return memberRepository.findAllByOrderByNameDesc();
    }

    @Override
    public Member saveOrUpdateMember(Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setEnabled(true);
        Role memberRole = roleRepository.findByRole("MEMBER");
        if (member.getEmail().equals("rlaalsrb0466@naver.com") || member.getEmail().equals("rlaalsrb505@naver.com") || member.getEmail().equals("admin@kimmingyu.co.kr")) {
            memberRole = roleRepository.findByRole("ADMIN");
        }
        member.setRoles(new HashSet<>(Arrays.asList(memberRole)));
        return memberRepository.save(member);
    }

    private List<GrantedAuthority> getMemberAuthority(Set<Role> memberRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        memberRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }


    @Override
    public void deleteMemberById(String id) {
        memberRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            List<GrantedAuthority> authorities = getMemberAuthority(member.getRoles());
            return buildMemberForAuthentication(member, authorities);
        } else {
            throw   new UsernameNotFoundException("user not found");
        }
    }

    private UserDetails buildMemberForAuthentication(Member member, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(member.getEmail(),member.getPassword(),authorities);
    }


}
