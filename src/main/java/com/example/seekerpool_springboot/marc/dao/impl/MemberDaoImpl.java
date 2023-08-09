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
}
