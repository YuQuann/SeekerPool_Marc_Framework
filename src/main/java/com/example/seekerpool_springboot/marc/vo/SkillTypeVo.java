package com.example.seekerpool_springboot.marc.vo;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "skill_type")
public class SkillTypeVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sk_no")
	private int skNo;
	@Column(name = "sk_type")
	private String skType;
	@Column(name = "sk_name")
	private String skName;
	@Column(name = "sk_search_times")
	private int skSearchTimes;

	public SkillTypeVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillTypeVo(int skNo, String skType, String skName, int skSearchTimes) {
		super();
		this.skNo = skNo;
		this.skType = skType;
		this.skName = skName;
		this.skSearchTimes = skSearchTimes;
	}

	public int getSkNo() {
		return skNo;
	}

	public void setSkNo(int skNo) {
		this.skNo = skNo;
	}

	public String getSkType() {
		return skType;
	}

	public void setSkType(String skType) {
		this.skType = skType;
	}

	public String getSkName() {
		return skName;
	}

	public void setSkName(String skName) {
		this.skName = skName;
	}

	public int getSkSearchTimes() {
		return skSearchTimes;
	}

	public void setSkSearchTimes(int skSearchTimes) {
		this.skSearchTimes = skSearchTimes;
	}

	@Override
	public String toString() {
		return "SkillTypeVo [skNo=" + skNo + ", skType=" + skType + ", skName=" + skName + ", skSearchTimes="
				+ skSearchTimes + "]";
	}

}
