package com.example.seekerpool_springboot.marc.dao.impl;

import com.example.seekerpool_springboot.marc.dao.JobContentDao;
import com.example.seekerpool_springboot.marc.mapper.JobContentMapper;
import com.example.seekerpool_springboot.marc.vo.ApplyRecordVo;
import com.example.seekerpool_springboot.marc.vo.CollectJobVo;
import com.example.seekerpool_springboot.marc.vo.ReportEnterpriseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JobContentDaoImpl implements JobContentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> showJobContent(int jobNo){

        String sql = "SELECT JOB_NAME,C.COM_NAME,J.COM_MEM_ID,JOB_CONTENT,JOB_OTHER,PT_NAME,JOB_SALARY,JOB_ADDRESS,C.COM_PICTURE FROM JOB J " +
                "JOIN COMPANY_MEMBER C ON J.COM_MEM_ID = C.COM_MEM_ID " +
                "JOIN POSITION_TYPE P ON J.PT_NO = P.PT_NO " +
                "WHERE JOB_NO = :jobNo ;";

        Map<String, Object> job = new HashMap<>();
        job.put("jobNo",jobNo);

        return namedParameterJdbcTemplate.query(sql,job,new JobContentMapper());
    }

    @Override
    public String resumeJob(int memId, int comMemId, int jobNo) {

        String sql = "INSERT INTO APPLY_RECORD SET COM_MEM_ID = :comMemId ,MEM_ID = :memId ,JOB_NO = :jobNo , HIRE_STATUS = 0 ;";
        Map<String,Object> map = new HashMap<>();
        map.put("memId",memId);
        map.put("comMemId",comMemId);
        map.put("jobNo",jobNo);

        namedParameterJdbcTemplate.update(sql,map);

        return "Dao層 新增應徵職缺";
    }

    @Override
    public List<ApplyRecordVo> selectAllJobApply() {

        String sql = "SELECT MEM_ID,COM_MEM_ID,JOB_NO FROM APPLY_RECORD;";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ApplyRecordVo.class));

    }

    @Override
    public String collectJob(int memId, int jobNo) {

        String sql = "INSERT INTO COLLECT_JOB SET MEM_ID = :memId , JOB_NO = :jobNo ;";
        Map<String,Object> map = new HashMap<>();
        map.put("memId",memId);
        map.put("jobNo",jobNo);

        namedParameterJdbcTemplate.update(sql,map);

        return "Dao層 新增收藏職缺";
    }

    @Override
    public List<CollectJobVo> selectAllJobCollect() {

        String sql = "SELECT MEM_ID,JOB_NO FROM COLLECT_JOB ;";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(CollectJobVo.class));

    }

    @Override
    public String addReport(ReportEnterpriseVo reportEnterpriseVo) {

        String sql ="INSERT INTO report_enterprise (rjt_no,re_content,re_end_time,re_status,re_result,re_upload,mem_id,com_mem_id,job_no) " +
                "VALUES (:RJT_NO, :RE_CONTENT, :RE_END_TIME, :RE_STATUS, :RE_RESULT, :RE_UPLOAD, :MEM_ID, :COM_MEM_ID, :JOB_NO);";

        Map<String,Object> map = new HashMap<>();
        map.put("RJT_NO",reportEnterpriseVo.getRjtNo());
        map.put("RE_CONTENT",reportEnterpriseVo.getReContent());
        map.put(("RE_END_TIME"),null);
        map.put("RE_STATUS",0);
        map.put(("RE_RESULT"),0);
        map.put("RE_UPLOAD",reportEnterpriseVo.getReUpload());
        map.put("MEM_ID",reportEnterpriseVo.getMemId());
        map.put("COM_MEM_ID",reportEnterpriseVo.getComMemId());
        map.put("JOB_NO",reportEnterpriseVo.getJobNo());

        if (namedParameterJdbcTemplate.update(sql,map) == 1){
            return "ture";
        }else {
            return "false";
        }
    }

}
