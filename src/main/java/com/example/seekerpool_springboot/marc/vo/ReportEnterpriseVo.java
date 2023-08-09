package com.example.seekerpool_springboot.marc.vo;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Entity
@Table(name = "report_enterprise")
public class ReportEnterpriseVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RE_NO")
	private int reNo;
	@Column(name = "RJT_NO")
	private int rjtNo;
	@Column(name = "MEM_ID")
	private int memId;
	@Column(name = "COM_MEM_ID")
	private int comMemId;
	@Column(name = "JOB_NO")
	private int jobNo;
	@Column(name = "RE_START_TIME")
	private Date reStartTime;
	@Column(name = "RE_CONTENT")
	private String reContent;
	@Column(name = "RE_END_TIME")
	private Date reEndTime;
	@Column(name = "RE_STATUS")
	private int reStatus;
	@Column(name = "RE_RESULT")
	private int reResult;
	@Column(name = "RE_NOTE")
	private String reNote;
	@Column(name = "RE_UPLOAD")
	private String reUpload;

	public ReportEnterpriseVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportEnterpriseVo(int reNo, int reStatus, int reResult) {
		super();
		this.reNo = reNo;
		this.reStatus = reStatus;
		this.reResult = reResult;
	}

	public ReportEnterpriseVo(int reNo, int rjtNo, int memId, int comMemId, int jobNo, Date reStartTime,
			String reContent, Date reEndTime, int reStatus, int reResult, String reNote, String reUpload) {
		super();
		this.reNo = reNo;
		this.rjtNo = rjtNo;
		this.memId = memId;
		this.comMemId = comMemId;
		this.jobNo = jobNo;
		this.reStartTime = reStartTime;
		this.reContent = reContent;
		this.reEndTime = reEndTime;
		this.reStatus = reStatus;
		this.reResult = reResult;
		this.reNote = reNote;
		this.reUpload = reUpload;
	}

	public int getReNo() {
		return reNo;
	}

	public void setReNo(int reNo) {
		this.reNo = reNo;
	}

	public int getRjtNo() {
		return rjtNo;
	}

	public void setRjtNo(int rjtNo) {
		this.rjtNo = rjtNo;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	public int getComMemId() {
		return comMemId;
	}

	public void setComMemId(int comMemId) {
		this.comMemId = comMemId;
	}

	public int getJobNo() {
		return jobNo;
	}

	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}

	public Date getReStartTime() {
		return reStartTime;
	}

	public void setReStartTime(Date reStartTime) {
		this.reStartTime = reStartTime;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public Date getReEndTime() {
		return reEndTime;
	}

	public void setReEndTime(Date reEndTime) {
		this.reEndTime = reEndTime;
	}

	public int getReStatus() {
		return reStatus;
	}

	public void setReStatus(int reStatus) {
		this.reStatus = reStatus;
	}

	public int getReResult() {
		return reResult;
	}

	public void setReResult(int reResult) {
		this.reResult = reResult;
	}

	public String getReNote() {
		return reNote;
	}

	public void setReNote(String reNote) {
		this.reNote = reNote;
	}

	public String getReUpload() {
		return reUpload;
	}

	public void setReUpload(String reUpload) {
		this.reUpload = reUpload;
	}

}
