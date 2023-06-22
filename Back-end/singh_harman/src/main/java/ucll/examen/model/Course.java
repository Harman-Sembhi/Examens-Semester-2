package ucll.examen.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Course {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @Range(min = 3, max = 20, message = "Number of credits must be between 3 and 20")
    private int credits;

    @OneToMany
    @JsonManagedReference
    private List<Module> modules = new ArrayList<>();

    public Course() {

    }

    public Course(String name, int credits) {
        setName(name);
        setCredits(credits);
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public Long getId() {
        return id;
    }

    public void setName(String n) {
        name = n;
    }

    public void setCredits(int a) {
        credits = a;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void addModule(Module m) {
        m.setCourse(this);
        modules.add(m);
    }

    public void removeModule(Module m) {
        m.removeCourse();
        modules.remove(m);
    }

}
