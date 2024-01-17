package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

public class ReportingStructureServiceImplTest extends TestCase {
    private String reportingStructureUrl;
    private String reportingStructureIdUrl;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Autowired
    EmployeeRepository employeeRepository;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure";
        reportingStructureIdUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    public void testReportingStructure_Read() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        ReportingStructure testReportingStructure = new ReportingStructure(testEmployee);
        testReportingStructure.setNumberOfReports(4);

        ReportingStructure createdReportingStructure = restTemplate.postForEntity(reportingStructureUrl,
                testReportingStructure, ReportingStructure.class).getBody();


        ReportingStructure readCompensation = restTemplate.getForEntity(reportingStructureIdUrl,
                ReportingStructure.class,
                createdReportingStructure.getEmployee().getEmployeeId()).getBody();

        assertEquals(createdReportingStructure.getEmployee().getEmployeeId(), readCompensation.getEmployee().getEmployeeId());

    }
}