package by.bsu.easytutor.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Audited
@Table(name = "Students")
public class Student extends User{

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourses;

    public Student() {}

    public Student(String login, String password, String name, String email, int age) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}