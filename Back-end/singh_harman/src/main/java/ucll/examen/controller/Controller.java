package ucll.examen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ucll.examen.exception.ServiceException;
import ucll.examen.model.Course;
import ucll.examen.model.Module;
import ucll.examen.service.CourseService;
import ucll.examen.service.ModuleService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("api")
public class Controller {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getAll();
    }

    @PostMapping("/modules/add")
    public Module addModule(@RequestBody Module module) {
        return moduleService.addModule(module);
    }

    @GetMapping("/modules")
    public List<Module> getModules() {
        return moduleService.getAll();
    }

    @PostMapping("/courses/add")
    public Course addCourse(@Valid @RequestBody Course course) throws ServiceException {
        return courseService.addCourse(course);
    }

    @GetMapping("/courses/filter") // ?credits={credits}
    public List<Course> orderCourseByCredits(@Valid @RequestParam int credits) {
        return courseService.orderCourseByCredits(credits);
    }

    @DeleteMapping("/modules/{moduleId}")
    public Module deleteModuleById(@PathVariable int moduleId) throws ServiceException {
        return moduleService.removeModuleById(moduleId);
    }

    @PostMapping("/courses/{courseId}/addmodule/{moduleId}")
    public Course addModuleToCourse(@PathVariable int courseId, @PathVariable int moduleId) throws ServiceException {
        return courseService.addModuleToCourse(courseId, moduleId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class })
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleServiceException(ServiceException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getField(), ex.getMessage());
        return errors;
    }

}
