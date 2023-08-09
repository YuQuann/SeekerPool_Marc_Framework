package com.example.seekerpool_springboot.marc.dao;

import com.example.seekerpool_springboot.marc.vo.JobVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobResultSearchDao {

    public List<JobVo> searchResult(String keyword, String city, String town);

}
