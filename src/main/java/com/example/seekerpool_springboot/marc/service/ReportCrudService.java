package com.example.seekerpool_springboot.marc.service;

import com.example.seekerpool_springboot.marc.vo.ReportEnterpriseVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public interface ReportCrudService {

    List<Map<String, Object>> getReportData();

    List<Map<String, Object>> getReportByNumber(Integer reNo);

    List<Map<String, Object>> getReportByResult(Integer reResult);

    List<Map<String, Object>> getEditReport(Integer reNo);

    String editReportResult(Integer reResult,Integer reStatus,Integer reNo);

}
