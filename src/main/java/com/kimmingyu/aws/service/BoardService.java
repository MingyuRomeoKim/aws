package com.kimmingyu.aws.service;

import com.kimmingyu.aws.model.Board;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    List<Board> findAll();
    List<Board> findAllByOrderByIdxDesc();
    List<Board> findAllByOrderByViewCntDesc();
    List<Board> findAllByCategoryOrderByIdxDesc();
    Board findByIdx(Long idx);
    Board saveOrUpdateBoard(Board board);

    void deleteBoardById(String id);

}
