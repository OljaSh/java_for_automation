package model;

public class ContactData {
    private final String first_name;

    private final String middle_name;

    private final String last_name;

    private final String nickname;

    private final String title;

    private final String company;

    private final String address;

    private final String home;

    private final String mobile;

    private final String work;

    private String group;

    public ContactData(String first_name, String middle_name, String last_name, String nickname, String title, String company, String address, String home, String mobile, String work, String group) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.group = group;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
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
