package model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "addressbook")

public class ContactData {

    @Id
    @Column(name = "id")
    private int id;
    @Expose
    @Column(name = "firstname")
    private String first_name;
    @Transient
    private  String middle_name;
    @Expose
    @Column(name = "lastname")
    private  String last_name;
    @Transient
    private  String nickname;
    @Transient
    private  String title;
    @Transient
    private  String company;
    @Transient
    private  String address;
    @Column(name = "home")
    @Type(type = "text")
    private  String home;
    @Column(name = "mobile")
    @Type(type = "text")
    private  String mobile;
    @Column(name = "work")
    @Type(type = "text")
    private  String work;
   // @Expose
    //@Transient  //пропускаем поле и не читаем из базы данных
   // private String group;
   @ManyToMany(fetch = FetchType.EAGER)  //пропускаем поле и не читаем из базы данных // извлекаем как можно больше информации за один заход
   @JoinTable(name="address_in_groups",
           joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="group_id"))
   private Set<GroupData> groups = new HashSet<GroupData>();

    @Transient
    private String email;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String allEmails;
    @Transient
    private String allPhones;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
 /*   public Groups getGroups() {
        return new Groups(groups);
    }*/


    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        return last_name != null ? last_name.equals(that.last_name) : that.last_name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" + "id=" + id + ", first_name='" + first_name + '\'' + ", last_name='" + last_name + '\'' + '}';
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public ContactData withMiddle_name(String middle_name) {
        this.middle_name = middle_name;
        return this;
    }

    public ContactData withLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
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


    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAllPhones() {
        return allPhones;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
