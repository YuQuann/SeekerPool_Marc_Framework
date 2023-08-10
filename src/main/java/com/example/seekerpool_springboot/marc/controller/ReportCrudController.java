package com.example.seekerpool_springboot.marc.controller;

import com.example.seekerpool_springboot.marc.service.ReportCrudService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportCrudController {

    @Autowired
    private ReportCrudService reportCrudService;
    @Autowired
    private Gson gson;

    @GetMapping("/datas")
    public List<Map<String, Object>> displayReportData(){
        return reportCrudService.getReportData();
    }

    @PostMapping("/data/number")
    public String selectReportNumber(@RequestParam Integer reNo){
        return gson.toJson(reportCrudService.getReportByNumber(reNo));
    }

    @PostMapping("/data/result")
    public List<Map<String, Object>> selectReportResult(@RequestParam Integer reResult){
        return reportCrudService.getReportByResult(reResult);
    }

    @GetMapping("/data/edit")
    public List<Map<String, Object>> editReport(@RequestParam Integer reNo){
        return reportCrudService.getEditReport(reNo);
    }
    @PatchMapping("/data/{reNo}")
    public String updateReportResult(@RequestBody Map<String, Integer> requestBody,
                                     @PathVariable Integer reNo){

        Integer reResult = requestBody.get("reResult");
        Integer reStatus = requestBody.get("reStatus");
        reportCrudService.editReportResult(reResult,reStatus,reNo);
        return gson.toJson("更新資料成功");
    }

}
