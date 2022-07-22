package com.kimmingyu.aws.service.impl;

import com.kimmingyu.aws.model.Board;
import com.kimmingyu.aws.repository.BoardRepository;
import com.kimmingyu.aws.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> findAllByOrderByIdxDesc() {
        return boardRepository.findAllByOrderByIdxDesc();
    }

    @Override
    public List<Board> findAllByOrderByViewCntDesc() {
        return boardRepository.findAllByOrderByViewCntDesc();
    }

    @Override
    public Board findByIdx(Long idx) {
        return boardRepository.findByIdx(idx);
    }

    @Override
    public Board saveOrUpdateBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public void deleteBoardById(String id) { boardRepository.deleteById(id); }

    @Override
    public List<Board> findAllByCategoryOrderByIdxDesc() {
        return boardRepository.findAllByCategoryOrderByIdxDesc();
    }
}
