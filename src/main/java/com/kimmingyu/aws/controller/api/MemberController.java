package com.kimmingyu.aws.controller.api;

import com.kimmingyu.aws.dto.MemberDTO;
import com.kimmingyu.aws.model.Member;
import com.kimmingyu.aws.service.classes.SequenceGeneratorService;
import com.kimmingyu.aws.service.impl.MemberServiceImpl;
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
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping(value = "/")
    public List<MemberDTO> getAllMembers() {
        return ObjectMapperUtils.mapAll(memberService.findAll(), MemberDTO.class);
    }

    @GetMapping(value = "/byEmail/{email}")
    public MemberDTO getMemberByEmail(@PathVariable("email") String email) {
        return ObjectMapperUtils.map(memberService.findByEmail(email),MemberDTO.class);
    }

    @GetMapping(value = "/byPhone/{phone}")
    public MemberDTO getMemberByPhone(@PathVariable("phone") String phone) {
        return ObjectMapperUtils.map(memberService.findByPhone(phone),MemberDTO.class);
    }

    @GetMapping(value = "/orderByName")
    public List<MemberDTO> findAllByOrderByNameDesc() {
        return ObjectMapperUtils.mapAll(memberService.findAllByOrderByNameDesc(),MemberDTO.class);
    }

    @GetMapping(value = "/orderByIdxDesc")
    public List<MemberDTO> findAllByOrderByIdxDesc() {
        return ObjectMapperUtils.mapAll(memberService.findAllByOrderByIdxDesc(),MemberDTO.class);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveOrUpdateMember(@RequestBody MemberDTO memberDTO) {
        // 이메일 체크
        Member member = memberService.findByEmail(memberDTO.getEmail());
        String responseMessage = "Member added success";
        boolean successOrFailed = true;
        if(member != null && member.getId() != null && member.getId().length() > 0) {
            responseMessage = "This Email already Exist";
            successOrFailed = false;
        } else {
            memberDTO.setIdx(sequenceGeneratorService.generateSequence(Member.SEQUENCE_NAME));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            memberDTO.setRegDate(dateTimeFormatter.format(LocalDateTime.now()).toString());

            member = memberService.saveOrUpdateMember(ObjectMapperUtils.map(memberDTO, Member.class));
        }

        JSONObject returnJson = new JSONObject();
        returnJson.put("value",successOrFailed);
        returnJson.put("message",responseMessage);
        returnJson.put("data",member);

        return new ResponseEntity(returnJson, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{email}")
    public ResponseEntity<?> deleteMemberByEmail(@PathVariable String email) {
        // 이메일 중복 체크
        Member member = memberService.findByEmail(email);
        String responseMessage = "Member Deleted success";
        if(member != null && member.getId() != null && member.getId().length() > 0) {
            memberService.deleteMemberById(memberService.findByEmail(email).getId());
        } else {
            responseMessage = "This Email Dose Not exist our Member List";
        }
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

}
