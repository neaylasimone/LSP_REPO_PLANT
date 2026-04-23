package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class demonstrating polymorphism via the Template Method pattern.
 */
public class Driver {

    /**
     * Main method to run all reports.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
        }
    }
}