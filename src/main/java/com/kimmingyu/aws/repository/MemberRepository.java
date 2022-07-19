package com.kimmingyu.aws.repository;

import com.kimmingyu.aws.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member, String> {

    Member findByEmail(String email);
    Member findByPhone(String phone);


    List<Member> findAllByOrderByNameDesc();
}
