package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report for student information.
 * Implements the variable steps of the Template Method.
 */
public class StudentReport extends Report {

    /** The student's name. */
    private String studentName;

    /** The student's GPA. */
    private double gpa;

    /**
     * Loads student-specific data.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Formats the header for the student report.
     */
    @Override
    protected void formatHeader() {
        System.out.println("Student Report");
    }

    /**
     * Formats the body for the student report.
     */
    @Override
    protected void formatBody() {
        System.out.println("Student Name: " + studentName);
        System.out.println("GPA: " + gpa);
    }

    /**
     * Formats the footer for the student report.
     */
    @Override
    protected void formatFooter() {
        System.out.println("End of Student Report");
    }
}