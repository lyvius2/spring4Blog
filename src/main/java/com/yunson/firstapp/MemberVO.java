package com.yunson.firstapp;

/**
 * Created by yhwang131 on 2016-08-10.
 */
public class MemberVO {

	private int AME_SEQ;
	private String AME_MemID;
	private String AME_Name;
	private String AME_Email;
	private String AME_Tel;
	private String AME_Phone;
	private String AME_Position;
	private String AME_LastIP;

	public MemberVO() {}

	public MemberVO(int AME_SEQ) {
		this.setAME_SEQ(AME_SEQ);
	}

	public int getAME_SEQ() {
		return AME_SEQ;
	}

	public void setAME_SEQ(int AME_SEQ) {
		this.AME_SEQ = AME_SEQ;
	}

	public String getAME_MemID() {
		return AME_MemID;
	}

	public void setAME_MemID(String AME_MemID) {
		this.AME_MemID = AME_MemID;
	}

	public String getAME_Name() {
		return AME_Name;
	}

	public void setAME_Name(String AME_Name) {
		this.AME_Name = AME_Name;
	}

	public String getAME_Email() {
		return AME_Email;
	}

	public void setAME_Email(String AME_Email) {
		this.AME_Email = AME_Email;
	}

	public String getAME_Tel() {
		return AME_Tel;
	}

	public void setAME_Tel(String AME_Tel) {
		this.AME_Tel = AME_Tel;
	}

	public String getAME_Phone() {
		return AME_Phone;
	}

	public void setAME_Phone(String AME_Phone) {
		this.AME_Phone = AME_Phone;
	}

	public String getAME_Position() {
		return AME_Position;
	}

	public void setAME_Position(String AME_Position) {
		this.AME_Position = AME_Position;
	}

	public String getAME_LastIP() {
		return AME_LastIP;
	}

	public void setAME_LastIP(String AME_LastIP) {
		this.AME_LastIP = AME_LastIP;
	}
}
