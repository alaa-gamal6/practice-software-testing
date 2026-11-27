package com.toolshop.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class TestListener implements ITestListener {

    private static final Path REPORTS = Paths.get("reports");
    private static final Path BUG_REPORT = REPORTS.resolve("bug-report.txt");
    private static final Path SUMMARY_REPORT = REPORTS.resolve("summary-report.txt");
    private static final String NL = System.lineSeparator();

    private static final AtomicInteger PASSED = new AtomicInteger();
    private static final AtomicInteger FAILED = new AtomicInteger();
    private static final AtomicInteger SKIPPED = new AtomicInteger();
    private static boolean initialized = false;

    @Override
    public synchronized void onStart(ITestContext context) {
        if (initialized) {
            return;
        }
        initialized = true;
        try {
            Files.createDirectories(REPORTS);
            Files.writeString(BUG_REPORT, "BUG REPORT - " + LocalDateTime.now() + NL + NL,
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Could not create reports: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        PASSED.incrementAndGet();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        SKIPPED.incrementAndGet();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        FAILED.incrementAndGet();
        Throwable t = result.getThrowable();
        String reason = t == null ? "N/A"
                : t.getClass().getSimpleName() + ": " + String.valueOf(t.getMessage()).split("\n")[0];
        String entry = "Test       : " + result.getTestClass().getRealClass().getSimpleName()
                + "." + result.getMethod().getMethodName() + NL
                + "Description: " + result.getMethod().getDescription() + NL
                + "Time       : " + LocalDateTime.now() + NL
                + "Reason     : " + reason + NL
                + "-----------------------------------------" + NL;
        try {
            Files.writeString(BUG_REPORT, entry, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Could not write bug report: " + e.getMessage());
        }
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        int total = PASSED.get() + FAILED.get() + SKIPPED.get();
        double rate = total == 0 ? 0 : PASSED.get() * 100.0 / total;
        String summary = "SUMMARY REPORT - " + LocalDateTime.now() + NL
                + "Total  : " + total + NL
                + "Passed : " + PASSED.get() + NL
                + "Failed : " + FAILED.get() + NL
                + "Skipped: " + SKIPPED.get() + NL
                + String.format("Pass rate: %.1f%%", rate) + NL;
        try {
            Files.createDirectories(REPORTS);
            Files.writeString(SUMMARY_REPORT, summary, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Could not write summary: " + e.getMessage());
        }
        System.out.println(summary);
    }
}