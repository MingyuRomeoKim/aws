package com.kimmingyu.aws.controller.api;

import com.kimmingyu.aws.dto.BoardDTO;
import com.kimmingyu.aws.dto.MemberDTO;
import com.kimmingyu.aws.model.Board;
import com.kimmingyu.aws.model.Member;
import com.kimmingyu.aws.service.classes.SequenceGeneratorService;
import com.kimmingyu.aws.service.impl.BoardServiceImpl;
import com.kimmingyu.aws.service.util.ObjectMapperUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/api/boards")
public class BoardController {
    @Autowired
    private BoardServiceImpl boardService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping(value = "/orderByIdxDesc")
    public List<BoardDTO> findAllByOrderByIdxDesc() {
        return ObjectMapperUtils.mapAll(boardService.findAllByOrderByIdxDesc(),BoardDTO.class);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrUpdateBoard(@RequestBody BoardDTO boardDTO) {
        // 이메일 체크
        String responseMessage = "Board added success";
        boolean successOrFailed = true;

        boardDTO.setIdx(sequenceGeneratorService.generateSequence(Board.SEQUENCE_NAME));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        boardDTO.setRegDate(dateTimeFormatter.format(LocalDateTime.now()).toString());


        Board board = boardService.saveOrUpdateBoard(ObjectMapperUtils.map(boardDTO, Board.class));


        JSONObject returnJson = new JSONObject();
        returnJson.put("value",successOrFailed);
        returnJson.put("message",responseMessage);
        returnJson.put("data",board);

        return new ResponseEntity(returnJson, HttpStatus.OK);
    }
}
