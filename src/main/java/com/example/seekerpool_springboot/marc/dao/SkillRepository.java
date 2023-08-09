package com.example.seekerpool_springboot.marc.dao;

import com.example.seekerpool_springboot.marc.vo.SkillTypeVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillTypeVo,Object> {

    @Query(value = "SELECT * FROM skill_type WHERE CONCAT(sk_name,' ',sk_type) LIKE CONCAT('%', ?1, '%');",nativeQuery = true)
    List<SkillTypeVo> selectSkillByKeyword(String keyword);

}
