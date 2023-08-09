package com.example.seekerpool_springboot.marc.service.impl;

import com.example.seekerpool_springboot.marc.dao.ReportRepository;
import com.example.seekerpool_springboot.marc.service.ReportCrudService;
import com.example.seekerpool_springboot.marc.vo.ReportEnterpriseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportCrudServiceImpl implements ReportCrudService {

    @Autowired
    private ReportRepository reportRepository;
    @Override
    public List<Map<String, Object>> getReportData() {
        return reportRepository.getAll();
    }
    @Override
    public List<Map<String, Object>> getReportByNumber(Integer reNo) {
        return reportRepository.getByReportId(reNo);
    }

    @Override
    public List<Map<String, Object>> getReportByResult(Integer reResult) {

        if (reResult == 3){
            return reportRepository.getAll();
        }else {
            return reportRepository.getByReportResult(reResult);
        }
    }
    @Override
    public List<Map<String, Object>> getEditReport(Integer reNo) {
        return reportRepository.getEditReport(reNo);
    }
    @Override
    public String editReportResult(Integer reResult, Integer reStatus,Integer reNo) {
       reportRepository.updateReport(reResult,reStatus,reNo);
       return "執行更新動作";
    }
}
