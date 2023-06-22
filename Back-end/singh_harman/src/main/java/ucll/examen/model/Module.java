package ucll.examen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    public Module() {
    }

    public Module(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void removeCourse() {
        course = null;
    }

    public void setCourse(Course c) {
        course = c;
    }

    public void setName(String name) {
        this.name = name;
    }

}
