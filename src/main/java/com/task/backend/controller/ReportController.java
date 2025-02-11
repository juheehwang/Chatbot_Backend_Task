package com.task.backend.controller;

import com.task.backend.service.ReportService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() throws IOException {
        byte[] csvReport = reportService.generateCsvReport();
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"activity_report.csv\"")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(csvReport);
    }
}
