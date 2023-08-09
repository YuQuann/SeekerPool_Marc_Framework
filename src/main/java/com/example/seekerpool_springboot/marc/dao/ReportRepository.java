package com.example.seekerpool_springboot.marc.dao;

import com.example.seekerpool_springboot.marc.vo.ReportEnterpriseVo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportRepository extends JpaRepository<ReportEnterpriseVo,Integer> {

    @Query(value = "SELECT re_no,mem_account,com_name,re_start_time,re_status,re_result FROM report_enterprise " +
                   "LEFT JOIN company_member ON report_enterprise.com_mem_id = company_member.com_mem_id " +
                   "LEFT JOIN member ON report_enterprise.mem_id = member.mem_id;",nativeQuery = true)
    List<Map<String, Object>> getAll();

    @Query(value = "SELECT re_no,mem_account,com_name,re_start_time,re_status,re_result FROM report_enterprise " +
                   "LEFT JOIN company_member ON report_enterprise.com_mem_id = company_member.com_mem_id " +
                   "LEFT JOIN member ON report_enterprise.mem_id = member.mem_id where re_no = ?;",nativeQuery = true)
    List<Map<String, Object>> getByReportId(Integer reNo);
    @Query(value = "SELECT re_no,mem_account,com_name,re_start_time,re_status,re_result FROM report_enterprise " +
                   "LEFT JOIN company_member ON report_enterprise.com_mem_id = company_member.com_mem_id " +
                   "LEFT JOIN member ON report_enterprise.mem_id = member.mem_id where re_result = ?;",nativeQuery = true)
    List<Map<String, Object>> getByReportResult(Integer reResult);

    @Query(value = "SELECT re_no,mem_account,com_name,re_upload,re_status,re_result,re_content,rjt_no FROM report_enterprise " +
                   "LEFT JOIN company_member ON report_enterprise.com_mem_id = company_member.com_mem_id " +
                   "LEFT JOIN member ON report_enterprise.mem_id = member.mem_id where re_no = ? ;",nativeQuery = true)
    List<Map<String, Object>> getEditReport(Integer reNo);

    @Transactional
    @Modifying
    @Query(value = "UPDATE report_enterprise SET re_result = ?,re_status = ? WHERE re_no = ? ;",nativeQuery = true)
    void updateReport(Integer reResult, Integer reStatus,Integer reNo);
}
