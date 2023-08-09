package com.example.seekerpool_springboot.marc.mapper;

import com.example.seekerpool_springboot.marc.vo.JobVo;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JobResultSearchMapper implements RowMapper<JobVo> {


    @Override
    public JobVo mapRow(ResultSet rs, int rowNum) throws SQLException {

        JobVo vo = new JobVo();
        vo.setJobNo(rs.getInt("JOB_NO"));
        vo.setJobName(rs.getString("JOB_NAME"));
        vo.setCityName(rs.getString("CITY_NAME"));
        vo.setDistrictName(rs.getString("DISTRICT_NAME"));
        vo.setComName(rs.getString("COM_NAME"));
        vo.setComMemId(rs.getInt("COM_MEM_ID"));
        vo.setJobTop(rs.getInt("JOB_TOP"));
        vo.setComPicture(rs.getString("COM_PICTURE"));

        return vo;
    }
}
