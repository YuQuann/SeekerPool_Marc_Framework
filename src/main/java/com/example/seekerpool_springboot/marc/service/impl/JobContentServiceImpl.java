package com.example.seekerpool_springboot.marc.service.impl;

import com.example.seekerpool_springboot.marc.dao.JobContentDao;
import com.example.seekerpool_springboot.marc.service.JobContentService;
import com.example.seekerpool_springboot.marc.vo.ApplyRecordVo;
import com.example.seekerpool_springboot.marc.vo.CollectJobVo;
import com.example.seekerpool_springboot.marc.vo.ReportEnterpriseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@Service
public class JobContentServiceImpl implements JobContentService {

    @Autowired
    private JobContentDao jobContentDao;


    public List<Map<String,Object>> showJobContent(int jobNo){
        return jobContentDao.showJobContent(jobNo);
    }

    @Override
    public String resumeJob(int memId, int comMemId, int jobNo) {

        if (resumeJobCheck(memId, comMemId, jobNo)){
            jobContentDao.resumeJob(memId, comMemId, jobNo);
        }else {
            return "false";
        }
        return "true";
    }

    @Override
    public Boolean resumeJobCheck(int memId, int comMemId, int jobNo) {

        List<ApplyRecordVo> list = jobContentDao.selectAllJobApply();

        for (ApplyRecordVo vo: list) {

            int dbMemId = vo.getMemId();
            int dbComMemId = vo.getComMemId();
            int dbjobNo = vo.getJobNo();

            if (memId == dbMemId && comMemId == dbComMemId && jobNo == dbjobNo){
                return false;
            }
        }
        return true;
    }

    @Override
    public String collectJob(int memId, int jobNo) {

        if (collectJobCheck(memId, jobNo)){
            jobContentDao.collectJob(memId, jobNo);
            return "true";
        }else {
            return "false";
        }
    }

    @Override
    public Boolean collectJobCheck(int memId, int jobNo) {

        List<CollectJobVo> list = jobContentDao.selectAllJobCollect();

        for (CollectJobVo vo: list) {

            int dbMemId = vo.getMemId();
            int dbJobNO = vo.getJobNo();

            if (memId == dbMemId && jobNo == dbJobNO){
                return false;
            }
        }
        return true;
    }

    @Override
    public String addReport(int jobNo, int comMemId, int memId, int rjtNo, String reContent, MultipartFile reUpload)
            throws IOException {

        /*
         * ================================ 處理圖片檔案的部分 ================================
         */

        // 取得圖片檔的名字
        String fileName = reUpload.getOriginalFilename();

        // 宣告未來存放上傳圖片的資料夾路徑
        // File.separator用於在路徑中分隔目錄和文件名稱。
        // uploadImage是自訂上傳資料夾的名稱，可以看需求更名
        //之後再想辦法處理可否使用相對路徑找出src裡的路徑
        String projectRoute = System.getProperty("user.dir"); // C:\Git_Project\SeekerPool_SpringBoot
        String imageFolder = "/src/main/resources/static/webapp";
        String uploadPath = projectRoute + imageFolder + "/uploadImage";


        // 儲存檔案至uploadImage資料夾並判斷資料夾是否存在
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }

        // reUploadStr為存進去資料庫的的檔案路徑
        String reUploadStr = "uploadImage/" + fileName;

        String imageRelativeUrl = uploadPath + File.separator + fileName;
//        System.out.println("imageRelativeUrl : " + imageRelativeUrl);
        try (InputStream fileContent = reUpload.getInputStream()) {
            Files.copy(fileContent, new File(imageRelativeUrl).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        /*
         * ================================ 處理圖片檔案的部分 ================================
         */

        ReportEnterpriseVo vo = new ReportEnterpriseVo();

        vo.setRjtNo(rjtNo);
        vo.setJobNo(jobNo);
        vo.setReContent(reContent);
        vo.setReEndTime(null);
        vo.setComMemId(comMemId);
        vo.setMemId(memId);
        vo.setReUpload(reUploadStr);

        if (jobContentDao.addReport(vo) == "true"){
            return "true";
        }else {
            return "false";
        }
    }

}
