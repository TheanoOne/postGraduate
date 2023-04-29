package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int ID;//	Id	用户id

	private String account;//	Account	用户账户

	private String password;//	Password	用户密码

	private String email;//	Email	用户邮箱

	private String phone;//	Phone	用户手机

	private Date birthday;//	Birthday	用户生日

	private String nickname;//	Name	用户昵称

	private String sex;//	Sex	用户性别
	
	private Date date;//	Data	创建时间

	private int status;//	Status	用户状态
	
	private String image;//	Image	用户头像
	
	private MultipartFile file;

	private int basicCourse1Grade;

	private int basicCourse2Grade;

	private int professionalCourse1Grade;

	private int professionalCourse2Grade;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getBasicCourse1Grade() {
		return basicCourse1Grade;
	}

	public void setBasicCourse1Grade(int basicCourse1Grade) {
		this.basicCourse1Grade = basicCourse1Grade;
	}

	public int getBasicCourse2Grade() {
		return basicCourse2Grade;
	}

	public void setBasicCourse2Grade(int basicCourse2Grade) {
		this.basicCourse2Grade = basicCourse2Grade;
	}

	public int getProfessionalCourse1Grade() {
		return professionalCourse1Grade;
	}

	public void setProfessionalCourse1Grade(int professionalCourse1Grade) {
		this.professionalCourse1Grade = professionalCourse1Grade;
	}

	public int getProfessionalCourse2Grade() {
		return professionalCourse2Grade;
	}

	public void setProfessionalCourse2Grade(int professionalCourse2Grade) {
		this.professionalCourse2Grade = professionalCourse2Grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    public String getLocalCreateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(this.date);
        return date;
    }

	@Override
	public String toString() {
		return account+password+email+image+phone+sex+status;
	}

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
