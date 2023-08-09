package com.example.seekerpool_springboot.marc.dao;

import com.example.seekerpool_springboot.marc.vo.ApplyRecordVo;
import com.example.seekerpool_springboot.marc.vo.CollectJobVo;
import com.example.seekerpool_springboot.marc.vo.ReportEnterpriseVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface JobContentDao {


    List<Map<String,Object>> showJobContent(int jobNo);

    String resumeJob(int memId,int comMemId,int jobNo);

    List<ApplyRecordVo> selectAllJobApply();

    String collectJob(int memId,int jobNo);

    List<CollectJobVo> selectAllJobCollect();

    String addReport(ReportEnterpriseVo reportEnterpriseVo);


}
