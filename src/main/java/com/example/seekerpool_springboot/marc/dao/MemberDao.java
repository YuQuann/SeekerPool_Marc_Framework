package com.example.seekerpool_springboot.marc.dao;

import com.example.seekerpool_springboot.marc.vo.MemberVo;

import java.util.List;

public interface MemberDao {

    List<MemberVo> memberLogin(String memAccount, String memPassword);

}
