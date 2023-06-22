package ucll.examen.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ucll.examen.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public List<Course> findCourseByOrderByCreditsDesc();

    public Course findById(int id);

}
