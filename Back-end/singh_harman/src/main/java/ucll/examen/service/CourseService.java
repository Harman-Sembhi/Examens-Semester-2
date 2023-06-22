package ucll.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import ucll.examen.exception.ServiceException;
import ucll.examen.model.Course;
import ucll.examen.repo.CourseRepository;
import ucll.examen.repo.ModuleRepository;
import ucll.examen.model.Module;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) throws ServiceException {
        int i = 2;
        for (Course c : courseRepository.findAll()) {
            if (c.getName().equals(course.getName())) {
                i--;
            }
            if (i <= 0) {
                throw new ServiceException("name", "There can be maximum 2 courses with the same name");
            }
        }
        return courseRepository.save(course);
    }

    public List<Course> orderCourseByCredits(int minCredits) {
        List<Course> coursesOrderedWithoutMinCredits = courseRepository.findCourseByOrderByCreditsDesc();
        List<Course> coursesOrdered = new ArrayList<>();
        for (Course course : coursesOrderedWithoutMinCredits) {
            if (course.getCredits() > minCredits) {
                coursesOrdered.add(course);
            }
        }
        return coursesOrdered;
    }

    public Course addModuleToCourse(int courseId, int moduleId) throws ServiceException {
        Course course = courseRepository.findById(courseId);
        Module module = moduleRepository.findById(moduleId);
        Validate(course, module);
        course.addModule(module);
        moduleRepository.save(module);
        return courseRepository.save(course);
    }

    public void Validate(Course course, Module module) throws ServiceException {
        if (course == null)
            throw new ServiceException("id", "No course with this course-id exists");
        if (module == null)
            throw new ServiceException("id", "No module with this module-id exists");
        if (course.getModules().size() >= 4)
            throw new ServiceException("course", "A course can have max 4 modules");
        if (module.getCourse() != null)
            throw new ServiceException("module", "This module already belongs to a course");
    }
}
