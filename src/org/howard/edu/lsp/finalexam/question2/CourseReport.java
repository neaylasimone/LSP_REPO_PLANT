package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report for course information.
 * Implements the variable steps of the Template Method.
 */
public class CourseReport extends Report {

    /** The course name. */
    private String courseName;

    /** The course enrollment count. */
    private int enrollment;

    /**
     * Loads course-specific data.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Formats the header for the course report.
     */
    @Override
    protected void formatHeader() {
        System.out.println("Course Report");
    }

    /**
     * Formats the body for the course report.
     */
    @Override
    protected void formatBody() {
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
    }

    /**
     * Formats the footer for the course report.
     */
    @Override
    protected void formatFooter() {
        System.out.println("End of Course Report");
    }
}