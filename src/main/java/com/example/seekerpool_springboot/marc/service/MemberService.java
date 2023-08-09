package com.example.seekerpool_springboot.marc.service;

import com.example.seekerpool_springboot.marc.vo.MemberVo;

import java.util.List;

public interface MemberService {

    List<MemberVo> memberLogin(String memAccount, String memPassword);

}
