package com.example.seekerpool_springboot.marc.service;

import jakarta.servlet.http.Part;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public interface JobContentService {

    List<Map<String,Object>> showJobContent(int jobNo);

    String resumeJob(int memId,int comMemId,int jobNo);

    Boolean resumeJobCheck(int memId,int comMemId,int jobNo);

    String collectJob(int memId,int jobNo);

    Boolean collectJobCheck(int memId,int jobNo);

    String addReport(int jobNo, int comMemId, int memId, int rjtNo, String reContent, MultipartFile reUpload) throws IOException;

}
