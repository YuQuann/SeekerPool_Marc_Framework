package com.example.seekerpool_springboot.marc.controller;

import com.example.seekerpool_springboot.marc.service.SkillService;
import com.example.seekerpool_springboot.marc.vo.SkillTypeVo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;


@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/skills")
    public List<SkillTypeVo> displaySKill(){
        return skillService.getSkills();
    }

    @GetMapping("/skill")
    public List<SkillTypeVo> selectSkillKeyword(@RequestParam String keyword){
        return skillService.selectSkillKeyword(keyword);
    }

    @PostMapping("/skill")
    public RedirectView addSkill(@RequestParam String skType, @RequestParam String skName){

        SkillTypeVo vo = new SkillTypeVo();
        vo.setSkName(skName);
        vo.setSkType(skType);
        vo.setSkSearchTimes(0);
        skillService.addSkill(vo);

        return new RedirectView("/back-end/job/JobSkill.html");
    }

    @GetMapping("/skill/edit")
    public Optional<SkillTypeVo> displaySkillBySkno(@RequestParam Integer skNo){
        return skillService.selectSkillBySkno(skNo);
    }

    @PostMapping("/skill/update")
    public RedirectView updateSkillBySkno(@RequestParam String skType, @RequestParam String skName,@RequestParam Integer skNo){

        SkillTypeVo vo = new SkillTypeVo();
        vo.setSkType(skType);
        vo.setSkName(skName);
        vo.setSkNo(skNo);
        skillService.updateSkillBySkno(vo);

        return new RedirectView("/back-end/job/JobSkill.html");
    }

    @DeleteMapping("/skill/delete")
    public String deleteSkillBySkno(@RequestParam Integer skNo){
        skillService.deleteSkillBySkno(skNo);
        Gson gson = new Gson();
        return gson.toJson("資料刪除");
    }
}
