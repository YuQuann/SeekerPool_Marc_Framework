package com.example.seekerpool_springboot.marc.dao.impl;

import com.example.seekerpool_springboot.marc.dao.JobResultSearchDao;
import com.example.seekerpool_springboot.marc.mapper.JobResultSearchMapper;
import com.example.seekerpool_springboot.marc.vo.JobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JobResultSearchDaoImpl implements JobResultSearchDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<JobVo> searchResult(String keyword, String city, String town){

        String sql = "SELECT JOB_NO,JOB_NAME,CITY_NAME,DISTRICT_NAME,C.COM_MEM_ID,C.COM_NAME,JOB_TOP,C.COM_PICTURE FROM JOB "
                + "JOIN company_member C ON JOB.COM_MEM_ID = C.COM_MEM_ID "
                + "WHERE JOB_NAME LIKE :keyword " + "AND JOB_UPLOAD = 1 "
                + "AND CITY_NAME LIKE :city " + "AND DISTRICT_NAME LIKE :town "
                + "ORDER BY JOB_TOP DESC;";

        Map<String,Object> map = new HashMap<>();
        map.put("keyword", "%" + keyword + "%");
        map.put("city", "%"+ city + "%");
        map.put("town", "%"+ town + "%");

        return namedParameterJdbcTemplate.query(sql,map,new JobResultSearchMapper());
    }

}
