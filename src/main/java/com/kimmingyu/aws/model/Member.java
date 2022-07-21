package com.kimmingyu.aws.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Document(collection = "members")
public class Member {
    @Transient
    public static final String SEQUENCE_NAME = "members_sequence";

    @Id
    private String id;

    private Long idx;

    @NotBlank
    @Size(max = 10)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String email;

    @NotBlank
    @Size(min = 10)
    private String password;

    private String phone;

    private String regDate;

    private boolean enabled;

    @DBRef
    private Set<Role> roles;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", idx='" + idx + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password + '\'' +
                ", phone=" + phone + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
