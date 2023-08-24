package com.example.seekerpool_springboot.marc.controller;

import com.example.seekerpool_springboot.marc.service.MemberService;
import com.example.seekerpool_springboot.marc.vo.MemberVo;
import com.example.seekerpool_springboot.marc.vo.ResultInfo;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Gson gson;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/register")
    public ResultInfo registerMember(@RequestParam Map<String, Object> params,
                                     @RequestParam("memPic") Part part) throws IOException {

        MemberVo member = new MemberVo();
        try {
            BeanUtils.populate(member, params); // 表單name要跟成員名稱一樣
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*---------------處理圖片檔案---------------*/

        String fileName = part.getSubmittedFileName();

        String saveDirectory = "image_uploaded"; //接收檔案的存放的目標資料夾名稱
        String userPath = System.getProperty("user.dir");
        String realPath = userPath + "/target/classes/static/webapp" + "/" + saveDirectory;

        File fileDir = new File(realPath); //確認資料夾存在否
        if (!fileDir.exists() || !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }

        File file = new File(realPath, fileName);
        part.write(file.toString());

        /*---------------處理圖片檔案---------------*/

        member.setMemAddress(request.getParameter("city") + request.getParameter("district"));
        member.setMemPic("/" + saveDirectory + "/" + fileName);

        ResultInfo resultInfo = new ResultInfo();

        if (memberService.registerMember(member)) {
            session.setAttribute("memAccount", member.getMemAccount());
            session.setAttribute("memName", member.getMemName());
            resultInfo.setFlag(true);
            resultInfo.setMsg("註冊成功！");
        } else {
            resultInfo.setFlag(false);
            resultInfo.setMsg("註冊失敗！ 帳號已有人使用");
        }
        return resultInfo;
    }

    @PostMapping("/memberLogin")
    public Boolean memberLogin(@RequestParam String memAccount,
                               @RequestParam String memPassword) {
        return memberService.memberLogin(memAccount, memPassword);
    }

    @PostMapping("/memberLogout")
    public ResponseEntity<String> memberLogout(@RequestParam String logout) {

        if (logout.equals("logout")) {
            session.removeAttribute("memName");
            session.removeAttribute("memId");
            return ResponseEntity.ok("Logout Successful");
        }
        return ResponseEntity.badRequest().body("Logout Fail,request is not correct");
    }

    @GetMapping("/displayMemberLoginName")
    public String displayMemberLoginName() {

        Object memName = session.getAttribute("memName");

        if (memName != null) {
            return gson.toJson(memName);
        } else {
            return gson.toJson("null");
        }
    }

    @GetMapping("/loginCheck")
    public Map<String, Boolean> loginCheck() {

        Map<String, Boolean> map = new HashMap<>();

        if (session.getAttribute("memId") == null) {
            map.put("loginInfo", false);
            return map;
        } else {
            map.put("loginInfo", true);
            return map;
        }
    }

    @PostMapping("/checkMemberCode")
    public ResultInfo checkMemberCode(@RequestParam String checkCode) {

        ResultInfo resultInfo = new ResultInfo();
        String memAccount = (String) session.getAttribute("memAccount");

        String redisCheckCode = redisTemplate.opsForValue().get(memAccount + "-checkCode");

        if (redisCheckCode.trim().equals(checkCode)) {
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setMsg("驗證失敗，請重新驗證");
        }
        return resultInfo;
    }

    @PostMapping("/sendMail")
    public void sendMail() {

        String memAccount = (String) session.getAttribute("memAccount");
        List<MemberVo> list = memberService.getMemberByAccount(memAccount);

        String memName = list.get(0).getMemName();
        String email = list.get(0).getMemEmail();

        memberService.sendMail(email, memName, memAccount);
    }


}
