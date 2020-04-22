package by.bsu.easytutor.entity;

public class Teacher extends User {
    private String education;
    private String experience;

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