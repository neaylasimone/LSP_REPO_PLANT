package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class defining the Template Method for report generation.
 * Defines the fixed workflow for all reports.
 */
public abstract class Report {

    /**
     * Template method that defines the fixed report generation workflow.
     * Subclasses may not override this method.
     */
    public final void generateReport() {
        loadData();
        System.out.println("=== HEADER ===");
        formatHeader();
        System.out.println("\n=== BODY ===");
        formatBody();
        System.out.println("\n=== FOOTER ===");
        formatFooter();
        System.out.println();
    }

    /**
     * Loads report-specific data.
     */
    protected abstract void loadData();

    /**
     * Formats the header section of the report.
     */
    protected abstract void formatHeader();

    /**
     * Formats the body section of the report.
     */
    protected abstract void formatBody();

    /**
     * Formats the footer section of the report.
     */
    protected abstract void formatFooter();
}