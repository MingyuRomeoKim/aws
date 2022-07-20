package com.kimmingyu.aws.dto;

public class MemberDTO {

    private String id;
    private Long idx;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String regDate;

    public MemberDTO() {

    }

    public MemberDTO(String id, Long idx, String name, String email, String password, String phone, String regDate) {
        this.id = id;
        this.idx = idx;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.regDate = regDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
