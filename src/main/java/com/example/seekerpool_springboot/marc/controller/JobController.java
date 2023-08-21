package com.example.seekerpool_springboot.marc.controller;

import com.example.seekerpool_springboot.marc.service.JobContentService;
import com.example.seekerpool_springboot.marc.service.JobResultSearchService;
import com.example.seekerpool_springboot.marc.vo.JobVo;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobResultSearchService jobResultSearchService;
    @Autowired
    private JobContentService jobContentService;
    @Autowired
    private Gson gson;
    @Autowired
    private HttpSession session;

    @PostMapping("/resultSearch")
    public List<JobVo> ResultSearch(@RequestBody Map<String, String> request) {
        String keyword = request.get("keyword");
        String city = request.get("city");
        String town = request.get("town");
        return jobResultSearchService.searchResult(keyword, city, town);
    }

    @GetMapping("/content")
    public List<Map<String, Object>> showJobContent(@RequestParam int jobNo) {
        return jobContentService.showJobContent(jobNo);
    }

    @PostMapping("/resumeJob")
    public Map<String, String> resumeJob(@RequestParam("jobNo") int jobNo,
                                         @RequestParam("comMemId") int comMemId) {

        Map<String, String> map = new HashMap<>();
        //該區塊作登入確認的判斷
        if (session.getAttribute("memId") != null) {
            //若確認登入了則截取session得到會員的ID並且在map內放入登入成功的資訊
            int memId = (int) session.getAttribute("memId");
            map.put("loginInfo", "true");
            if (jobContentService.resumeJob(memId, comMemId, jobNo).equals("true")) {
                //若遍歷資料庫發現還未收藏過該職缺，則在map內放入應徵職缺的確認訊息
                map.put("resumeCheck", "true");
                return map;
            }else {
                map.put("resumeCheck", "false");
            }
            map.put("loginInfo", "false");
            map.put("resumeCheck", "false");
        }
        return map;
    }

    @PostMapping("/collectJob")
    public Map<String, String> collectJob(@RequestParam int jobNo) {

        Map<String, String> map = new HashMap<>();
        //該區塊作登入確認的判斷
        if (session.getAttribute("memId") != null) {
            //若確認登入了則截取session得到會員的ID並且在map內放入登入成功的資訊
            int memId = (int) session.getAttribute("memId");
            map.put("loginInfo", "true");

            if (jobContentService.collectJob(memId, jobNo).equals("true")) {
                //若遍歷資料庫發現還未收藏過該職缺，則在map內放入收藏職缺的確認訊息
                map.put("collectCheck", "true");
                return map;
            }else {
                map.put("collectCheck", "false");
            }
        } else {
            map.put("loginInfo", "false");
            map.put("collectCheck", "false");
        }
        return map;
    }

    @PostMapping(value = "/reportJob")
    public String reportJob(@RequestParam("reUpload") MultipartFile reUpload,
                            @RequestParam("jobNo") int jobNo,
                            @RequestParam("comMemId") int comMemId,
                            @RequestParam("rjtNo") int rjtNo,
                            @RequestParam("reContent") String reContent) throws IOException {

        //有使用攔截器攔截未登入的使用者
        int memId = (int) session.getAttribute("memId");

        if (jobContentService.addReport(jobNo, comMemId, memId, rjtNo, reContent, reUpload)) {
            return "true";
        } else {
            return "false";
        }
    }


}
