package com.example.seekerpool_springboot.marc.mapper;

import com.example.seekerpool_springboot.marc.vo.MemberVo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<MemberVo> {

    @Override
    public MemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {

        MemberVo vo = new MemberVo();
        vo.setMemId(rs.getInt("MEM_ID"));
        vo.setMemName(rs.getString("MEM_NAME"));
        vo.setMemAddress(rs.getString("MEM_ADDRESS"));
        vo.setCvStatus(rs.getInt("CV_STATUS"));
        vo.setMemBio(rs.getString("MEM_BIO"));
        vo.setMemCollege(rs.getString("MEM_COLLEGE"));
        vo.setMemDepartment(rs.getString("MEM_DEPARTMENT"));
        vo.setMemEmail(rs.getString("MEM_EMAIL"));
        vo.setMemGender(rs.getString("MEM_GENDER"));
        vo.setMemLang(rs.getString("MEM_LANG"));
        vo.setMemMobile(rs.getString("MEM_MOBILE"));
        vo.setNlSub(rs.getInt("NL_SUB"));
        vo.setMemPic(rs.getString("MEM_PIC"));
        vo.setSkNo(rs.getString("SK_NO"));
        vo.setMemStatus(rs.getInt("MEM_STATUS"));
        vo.setMemAccount(rs.getString("MEM_ACCOUNT"));
        vo.setMemPassword(rs.getString("MEM_PASSWORD"));

        return vo;
    }
}
