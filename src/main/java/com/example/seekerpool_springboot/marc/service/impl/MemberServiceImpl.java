package com.example.seekerpool_springboot.marc.service.impl;

import com.example.seekerpool_springboot.marc.dao.MemberDao;
import com.example.seekerpool_springboot.marc.service.MemberService;
import com.example.seekerpool_springboot.marc.vo.MemberVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private HttpSession session;

    @Override
    public Boolean memberLogin(String memAccount, String memPassword) {

        List<MemberVo> list = memberDao.memberLogin(memAccount,memPassword);

        if (list.isEmpty()){
            System.out.println("我是空值，代表帳號密碼不存在或錯誤");
            return false;
        }else{
            session.setAttribute("memId", list.get(0).getMemId());
            session.setAttribute("memName",list.get(0).getMemName());
        }
        return true;
    }

    @Override
    public Boolean registerMember(MemberVo memberVo) {

        Boolean flag = memberDao.registerMember(memberVo);
        if (flag){
            //新增成功
            return true;
        }else {
            //新增失敗
            return false;
        }
    }
}
