package com.wxf.wordexport.entity.resume;

import com.deepoove.poi.data.NumberingRenderData;
import com.deepoove.poi.data.PictureRenderData;

import java.util.List;

public class ResumeDataIterable {

    private PictureRenderData portrait;
    private String name;
    private String job;
    private String sex;
    private String phone;
    private String birthday;
    private String province;
    private String email;
    private String address;
    private String english;
    private String University;
    private String rank;
    private String education;
    private String profession;
    private NumberingRenderData stack;
    private String hobbies;

    public List<ExperienceData> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceData> experiences) {
        this.experiences = experiences;
    }

    private List<ExperienceData> experiences;

    public PictureRenderData getPortrait() {
        return portrait;
    }

    public void setPortrait(PictureRenderData portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public NumberingRenderData getStack() {
        return stack;
    }

    public void setStack(NumberingRenderData stack) {
        this.stack = stack;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

}
