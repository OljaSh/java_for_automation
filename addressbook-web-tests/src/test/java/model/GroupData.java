package model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("group") //что бы в xml выводил tag groups
//Объявляет что сласс GroupData привязан к базе
@Entity
//указания на что мапятца таблицы т.к. названия не совпадают
@Table(name= "group_list")
public class GroupData {

    @Expose  //отмечаем поля которые должны попасть в json
    @Column(name = "group_name")
    private  String name;
    @Expose
    @Type(type = "text") //подсказка для преобразования типа
    @Column(name = "group_header")
    private  String header;
    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private  String footer;
    @XStreamOmitField //не сохранять это поле в формате xml
    @Id
    @Column(name = "group_id")
    private int id = Integer.MAX_VALUE;

    @Override
    public String toString() {
        return "GroupData{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        if (name != null ? !name.equals(groupData.name) : groupData.name != null) return false;
        if (header != null ? !header.equals(groupData.header) : groupData.header != null) return false;
        return footer != null ? footer.equals(groupData.footer) : groupData.footer == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (footer != null ? footer.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
