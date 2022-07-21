package com.kimmingyu.aws.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kimmingyu.aws.model.Member;
import com.kimmingyu.aws.repository.MemberRepository;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(value = {"/","/index","/main","/dashboard"})
    public ModelAndView dashboard() {
        // Model And View 데이터 처리
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(auth.getName());
        if(member != null) {
            mav.addObject("currentMember",member);
            mav.addObject("fullName","Welcome "+member.getName());;
            if(member.getRoles().equals("MEMBER")) {
                mav.addObject("roleMessage","회원가입 및 로그인이 완료되었습니다!");
            }
        }else {

        }

        mav.setViewName("dashboard");
        return mav;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView();
        Member member = new Member();
        mav.addObject("member",member);
        mav.setViewName("signup");
        return mav;
    }

    @GetMapping(value = "/users")
    public ModelAndView users(HttpServletRequest request) throws MalformedURLException, URISyntaxException, ParseException, JSONException {

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);

        // parameter 세팅
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("email", "test@naver.com");

        // REST API 호출 - URI 생성
        URL url = new URL(request.getRequestURL().toString());
        String host  = url.getHost();
        String userInfo = url.getUserInfo();
        String scheme = url.getProtocol();
        int port = url.getPort();
        URI uri = new URI(scheme,userInfo,host,port,null,null,null);

        // REST API 호출 - 데이터 수신 및 객체화
        String result = restTemplate.getForObject(uri+"/api/members/orderByName", String.class);
        Gson gson = new Gson();
        List<Map<String,Object>> messages = gson.fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());

        // Model And View 데이터 처리
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(auth.getName());
        if(member != null) {
            mav.addObject("currentMember",member);
            mav.addObject("fullName","Welcome "+member.getName());;
            if(member.getRoles().equals("MEMBER")) {
                mav.addObject("roleMessage","회원가입 및 로그인이 완료되었습니다!");
            }
        }
        mav.addObject("messages",messages);
        mav.setViewName("users");

        return mav;

    }

    @GetMapping(value = "/products")
    public String products() {
        return "products";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }

}
