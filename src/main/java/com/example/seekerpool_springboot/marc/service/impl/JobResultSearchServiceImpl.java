package com.example.seekerpool_springboot.marc.service.impl;

import com.example.seekerpool_springboot.marc.dao.JobResultSearchDao;
import com.example.seekerpool_springboot.marc.service.JobResultSearchService;
import com.example.seekerpool_springboot.marc.vo.JobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobResultSearchServiceImpl implements JobResultSearchService {

    @Autowired
    private JobResultSearchDao jobResultSearchDao;

public List<JobVo> searchResult(String keyword, String city, String town){

    return jobResultSearchDao.searchResult(keyword, city, town);

}

}
