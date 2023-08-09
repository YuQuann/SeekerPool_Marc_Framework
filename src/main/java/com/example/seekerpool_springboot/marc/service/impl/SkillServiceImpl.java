package com.example.seekerpool_springboot.marc.service.impl;

import com.example.seekerpool_springboot.marc.dao.SkillRepository;
import com.example.seekerpool_springboot.marc.service.SkillService;
import com.example.seekerpool_springboot.marc.vo.SkillTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;
    @Override
    public List<SkillTypeVo> getSkills() {
        return skillRepository.findAll();
    }
    @Override
    public List<SkillTypeVo> selectSkillKeyword(String keyword) {
        return skillRepository.selectSkillByKeyword(keyword);
    }
    @Override
    public String addSkill(SkillTypeVo vo) {

        skillRepository.save(vo);
        return "true";
    }
    @Override
    public Optional<SkillTypeVo> selectSkillBySkno(Integer skNo) {
        return  skillRepository.findById(skNo);
    }
    @Override
    public String updateSkillBySkno(SkillTypeVo vo) {
        skillRepository.save(vo);
        return "true";
    }
    @Override
    public String deleteSkillBySkno(Integer skNo) {
        skillRepository.deleteById(skNo);
        return "true";
    }
}
