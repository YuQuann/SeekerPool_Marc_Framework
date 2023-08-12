package com.example.seekerpool_springboot.marc.controller;

import com.example.seekerpool_springboot.marc.service.MemberService;
import com.example.seekerpool_springboot.marc.vo.MemberVo;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private HttpSession session;
    @Autowired
    private Gson gson;

    @PostMapping("/memberLogin")
    public Boolean memberLogin(@RequestParam String memAccount,
                               @RequestParam String memPassword){

        return memberService.memberLogin(memAccount,memPassword);
    }

    @PostMapping("/memberLogout")
    public String memberLogout(@RequestParam String logout){

        if (logout.equals("logout")){
            session.removeAttribute("memName");
            session.removeAttribute("memId");
            return "MemberLogoutSuccess";
        }
            return "MemberLogoutFail";
    }

    @PostMapping("/displayMemberLoginName")
    public String displayMemberLoginName(){

        Object memName = session.getAttribute("memName");

        if (memName != null){
            return gson.toJson(memName);
        }else {
            return gson.toJson("null");
        }
    }

    @GetMapping("/loginCheck")
    public Map<String,String> loginCheck(){

        Map<String,String> map = new HashMap<>();

        if (session.getAttribute("memId") == null){
               map.put("loginInfo","false");
               return map;
        }else {
            map.put("loginInfo","true");
            return map;
        }
    }

}
