package com.example.seekerpool_springboot.marc.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JobContentMapper implements RowMapper<Map<String,Object>> {


    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {

        Map<String,Object> job = new HashMap<>();
        job.put("jobName", rs.getString("JOB_NAME"));
        job.put("comName", rs.getString("COM_NAME"));
        job.put("comMemId", rs.getInt("COM_MEM_ID"));
        job.put("jobContent", rs.getString("JOB_CONTENT"));
        job.put("jobOther",rs.getString("JOB_OTHER"));
        job.put("ptName", rs.getString("PT_NAME"));
        job.put("jobSalary", rs.getInt("JOB_SALARY"));
        job.put("jobAddress", rs.getString("JOB_ADDRESS"));
        job.put("comPicture", rs.getString("COM_PICTURE"));

        return job;
    }
}
