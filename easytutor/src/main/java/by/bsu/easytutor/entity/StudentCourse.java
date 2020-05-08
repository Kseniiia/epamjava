package by.bsu.easytutor.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "StudentsCourses")
public class StudentCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    public Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    public Course course;

    public StudentCourse() {}

    public StudentCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourse that = (StudentCourse) o;
        return student.equals(that.student) &&
                course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}