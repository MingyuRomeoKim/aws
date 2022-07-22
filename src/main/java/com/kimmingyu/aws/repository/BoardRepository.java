package com.kimmingyu.aws.repository;

import com.kimmingyu.aws.model.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BoardRepository extends MongoRepository<Board,String> {

    Board findByid(String id);
    Board findByIdx(Long idx);

    List<Board> findAllByOrderByIdxDesc();

    List<Board> findAllByOrderByViewCntDesc();

    List<Board> findAllByCategoryOrderByIdxDesc();
}
