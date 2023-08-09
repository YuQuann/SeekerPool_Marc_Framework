package com.example.seekerpool_springboot.marc.service;

import com.example.seekerpool_springboot.marc.vo.SkillTypeVo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SkillService {

    List<SkillTypeVo> getSkills();

    List<SkillTypeVo> selectSkillKeyword(String keyword);

    String addSkill(SkillTypeVo vo);

    Optional<SkillTypeVo> selectSkillBySkno(Integer skNo);

    String updateSkillBySkno(SkillTypeVo vo);

    String deleteSkillBySkno(Integer skNo);

}
