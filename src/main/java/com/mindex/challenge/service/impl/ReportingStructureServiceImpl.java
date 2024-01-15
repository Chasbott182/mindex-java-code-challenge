package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    public String read(String id) {
        LOG.debug("Reading ReportingStructure with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure(employee, employeeRepository);
        return String.format(
                "Employee: "+ reportingStructure.getEmployeeFullName() +'\n'+
                "Number Of Direct Reports: "+ reportingStructure.getNumberOfReports()+'\n'+
                "Direct Reports: "+ reportingStructure.getDirectReports());
    }
}
