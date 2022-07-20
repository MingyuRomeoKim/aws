package com.kimmingyu.aws.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kimmingyu.aws.dto.MemberDTO;
import com.kimmingyu.aws.service.util.ObjectMapperUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(value = "/")
public class HomeController {

    @GetMapping(value = "")
    public String home() {
        return "home";
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
        String result = restTemplate.getForObject(uri+"/members/orderByName", String.class);
        Gson gson = new Gson();
        List<Map<String,Object>> messages = gson.fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());

        ModelAndView mav = new ModelAndView("users");
        mav.addObject("messages",messages);

        return mav;

    }

    @GetMapping(value = "/signup")
    public String signup() {
        return "signup";
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
