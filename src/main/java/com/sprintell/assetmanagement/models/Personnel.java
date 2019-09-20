package com.sprintell.assetmanagement.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personnelId;

    private String personnelNumber;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String title;
    private String status;
    private LocalDate regTime;
    private String address;
    private String personnelGroup;

    @OneToMany(mappedBy = "personnel", cascade=CascadeType.ALL)
    private List<PersonnelHistory> personnelHistories;

    @OneToMany(mappedBy="personnel",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<AssetCheckedOut> checkedOuts;

    public Personnel() {
    }

    public Long getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Long personnelId) {
        this.personnelId = personnelId;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDate regTime) {
        this.regTime = regTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonnelGroup() {
        return personnelGroup;
    }

    public void setPersonnelGroup(String personnelGroup) {
        this.personnelGroup = personnelGroup;
    }

    public List<AssetCheckedOut> getCheckedOuts() {
        return checkedOuts;
    }

    public void setCheckedOuts(List<AssetCheckedOut> checkedOuts) {
        this.checkedOuts = checkedOuts;
    }

    public List<PersonnelHistory> getPersonnelHistories() {
        return personnelHistories;
    }

    public void setPersonnelHistories(List<PersonnelHistory> personnelHistories) {
        this.personnelHistories = personnelHistories;
    }

}
