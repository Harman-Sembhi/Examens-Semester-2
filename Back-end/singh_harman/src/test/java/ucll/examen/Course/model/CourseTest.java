package ucll.examen.Course.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import ucll.examen.model.Course;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    // constructor happy cases
    @Test
    public void givenValidNameCredits_whenCreatingCourse_thenCourseIsCreatedWithThatNameCredits() {
        // given
        String validName = "Back-End";
        int validCredits = 6;

        // when
        Course course_backEnd = new Course(validName, validCredits);

        // then
        assertNotNull(course_backEnd);
        assertEquals(validName, course_backEnd.getName());
        assertEquals(validCredits, course_backEnd.getCredits());
        Set<ConstraintViolation<Course>> violations = validator.validate(course_backEnd);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void givenValidNameCredits3_whenCreatingCourse_thenCourseIsCreatedWithThatNameCredits() {
        // given
        String validName = "Back-End";
        int validCredits = 3;

        // when
        Course course_backEnd = new Course(validName, validCredits);

        // then
        assertNotNull(course_backEnd);
        assertEquals(validName, course_backEnd.getName());
        assertEquals(validCredits, course_backEnd.getCredits());
        Set<ConstraintViolation<Course>> violations = validator.validate(course_backEnd);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void givenValidNameCredits20_whenCreatingCourse_thenCourseIsCreatedWithThatNameCredits() {
        // given
        String validName = "Back-End";
        int validCredits = 20;

        // when
        Course course_backEnd = new Course(validName, validCredits);

        // then
        assertNotNull(course_backEnd);
        assertEquals(validName, course_backEnd.getName());
        assertEquals(validCredits, course_backEnd.getCredits());
        Set<ConstraintViolation<Course>> violations = validator.validate(course_backEnd);
        assertTrue(violations.isEmpty());
    }

    // constructor unhappy cases
    // credits out of range
    @Test
    public void givenCreditsLessThan3_whenCreatingCourse_thenCreditsViolationMessageIsThrown() {
        // given
        String validName = "Back-End";
        int invalidCredits = 2;

        // when
        Course course = new Course(validName, invalidCredits);

        // then
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        assertEquals(1, violations.size());
        ConstraintViolation<Course> violation = violations.iterator().next();
        assertEquals("Number of credits must be between 3 and 20", violation.getMessage());
        assertEquals("credits", violation.getPropertyPath().toString());
        assertEquals(invalidCredits, violation.getInvalidValue());
    }

    @Test
    public void givenCreditsMoreThan20_whenCreatingCourse_thenCreditsViolationMessageIsThrown() {
        // given
        String validName = "Back-End";
        int invalidCredits = 21;

        // when
        Course course = new Course(validName, invalidCredits);

        // then
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        assertEquals(1, violations.size());
        ConstraintViolation<Course> violation = violations.iterator().next();
        assertEquals("Number of credits must be between 3 and 20", violation.getMessage());
        assertEquals("credits", violation.getPropertyPath().toString());
        assertEquals(invalidCredits, violation.getInvalidValue());
    }

}