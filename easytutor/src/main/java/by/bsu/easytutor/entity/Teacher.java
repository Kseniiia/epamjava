package by.bsu.easytutor.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teachers")
public class Teacher extends User {

    @Column(name = "education")
    private String education;

    @Column(name = "experience")
    private String experience;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private List<Course> courses;

    public Teacher() {}

    public Teacher(String login, String password, String name, String email, String education, String experience) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.education = education;
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", education='" + education + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}