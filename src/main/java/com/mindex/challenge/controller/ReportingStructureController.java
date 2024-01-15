package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {
    @Autowired
    private ReportingStructureService reportingStructureService;
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);
    @GetMapping("/employee/reporting/{id}")
    public ResponseEntity<String> read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return new ResponseEntity<>(reportingStructureService.read(id), HttpStatus.OK);
    }
}
