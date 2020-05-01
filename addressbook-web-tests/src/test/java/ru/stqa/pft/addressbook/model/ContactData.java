package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String email;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String address;
    private final String homephone;
    private final String mobile;
    private final String work;
    private String group;

    public ContactData(String email, String firstname, String middlename, String lastname, String nickname, String company, String address, String homephone, String mobile, String work, String group) {
        this.email = email;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.mobile = mobile;
        this.work = work;
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getGroup() {
        return group;
    }
}
