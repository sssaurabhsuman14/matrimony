package com.hcl.matrimony.model;

public class SearchModel {

	private int age;
	private String height;
	private String religion;
	private String city;
	private String maritalStatus;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public SearchModel(int age, String height, String religion, String city, String maritalStatus) {
		super();
		this.age = age;
		this.height = height;
		this.religion = religion;
		this.city = city;
		this.maritalStatus = maritalStatus;
	}
	public SearchModel() {
		super();
	}
	
	
}