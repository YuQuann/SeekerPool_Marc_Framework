package com.example.seekerpool_springboot.marc.dao.impl;

import com.example.seekerpool_springboot.marc.dao.MemberDao;
import com.example.seekerpool_springboot.marc.mapper.MemberMapper;
import com.example.seekerpool_springboot.marc.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<MemberVo> memberLogin(String memAccount, String memPassword) {

        String sql = "SELECT * FROM member where mem_account = :memAccount AND mem_password = :memPassword ;" ;

        Map<String,Object> map = new HashMap<>();
        map.put("memAccount",memAccount);
        map.put("memPassword",memPassword);

        return namedParameterJdbcTemplate.query(sql,map,new MemberMapper());
    }

    @Override
    public Boolean registerMember(MemberVo vo) {

        String sql = "INSERT INTO member (mem_address,mem_name,mem_gender,mem_pic,mem_email,mem_mobile,mem_account,mem_password) " +
                "VALUES (:memAddress,:memName,:memGender,:memPic,:memEmail,:memMobile,:memAccount,:memPassword);";

        Map<String,Object> map = new HashMap<>();
        map.put("memAddress",vo.getMemAddress());
        map.put("memName",vo.getMemName());
        map.put("memGender",vo.getMemGender());
        map.put("memPic",vo.getMemPic());
        map.put("memEmail",vo.getMemEmail());
        map.put("memMobile",vo.getMemMobile());
        map.put("memAccount",vo.getMemAccount());
        map.put("memPassword",vo.getMemPassword());


        if (namedParameterJdbcTemplate.update(sql,map) == 1){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public List<MemberVo> getMemberByAccount(String memAccount){

        String sql = "SELECT * FROM member where mem_account = :memAccount ;";

        Map<String,Object> map = new HashMap<>();
        map.put("memAccount",memAccount);

        return namedParameterJdbcTemplate.query(sql,map,new MemberMapper());

    }
}
