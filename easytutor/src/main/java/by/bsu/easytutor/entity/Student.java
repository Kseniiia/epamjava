package by.bsu.easytutor.entity;

public class Student extends User {
    private int age;

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