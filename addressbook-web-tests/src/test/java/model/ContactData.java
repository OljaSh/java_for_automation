package model;

public class ContactData {
    private int id;

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
        this.id = Integer.MAX_VALUE;
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

    public ContactData(int id, String first_name, String middle_name, String last_name, String nickname, String title, String company, String address, String home, String mobile, String work) {
        this.id = id;
        this.first_name = first_name;
        this.middle_name = null;
        this.last_name = last_name;
        this.nickname = null;
        this.title = null;
        this.company = null;
        this.address = null;
        this.home = null;
        this.mobile = null;
        this.work = null;
        this.group = null;
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

    @Override
    public String toString() {
        return "ContactData{" + "first_name='" + first_name + '\'' + ", last_name='" + last_name + '\'' + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        return last_name != null ? last_name.equals(that.last_name) : that.last_name == null;
    }

    @Override
    public int hashCode() {
        int result = first_name != null ? first_name.hashCode() : 0;
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        return result;
    }
}
