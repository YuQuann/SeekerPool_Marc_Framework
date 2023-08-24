package com.example.seekerpool_springboot.marc.service.impl;

import com.example.seekerpool_springboot.marc.dao.MemberDao;
import com.example.seekerpool_springboot.marc.service.MemberService;
import com.example.seekerpool_springboot.marc.util.SendMailUtil;
import com.example.seekerpool_springboot.marc.util.VerificationCodeUtil;
import com.example.seekerpool_springboot.marc.vo.MemberVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private HttpSession session;
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Boolean memberLogin(String memAccount, String memPassword) {

        List<MemberVo> list = memberDao.memberLogin(memAccount,memPassword);

        if (list.isEmpty()){
            System.out.println("我是空值，代表帳號密碼不存在或錯誤");
            return false;
        }else{
            session.setAttribute("memId", list.get(0).getMemId());
            session.setAttribute("memName",list.get(0).getMemName());
            return true;
        }
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

    @Override
    public void sendMail(String email,String memName,String memAccount) {

        String authWord = VerificationCodeUtil.generateVerificationCode(6);
        //刪除舊的驗證碼
        redisTemplate.delete(memAccount + "-checkCode");
        //設置驗證碼至redis內
        redisTemplate.opsForValue().set(memAccount + "-checkCode",authWord);
        //設置驗證碼自動銷毀時間5分鐘
        redisTemplate.expire(memAccount + "-checkCode",300, TimeUnit.SECONDS);

        //開立執行緒讓寄信效率提升
        Thread thread = new Thread(() -> SendMailUtil.sendMail(authWord,email,memName));
        thread.start();
    }

    @Override
    public List<MemberVo> getMemberByAccount(String memAccount) {
        return memberDao.getMemberByAccount(memAccount);
    }

}
