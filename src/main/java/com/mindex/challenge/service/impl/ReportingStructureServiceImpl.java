package com.mindex.challenge.service.impl;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private List<String> listOfReports = new ArrayList<>();
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    public ReportingStructure read(String id) {
        listOfReports.clear();
        LOG.debug("Reading ReportingStructure with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure(employee);

        directReports(reportingStructure.getEmployee().getDirectReports());
        reportingStructure.setNumberOfReports(listOfReports.size());
        return reportingStructure;
    }

    /**
     * This method calculates the number an employee has.  This is done recursively.
     * @param employees
     * @return void
     *
    * */
    private void directReports(List<Employee> employees){

        if(!Optional.ofNullable(employees).isPresent()) {
            return;
        }
        for (Employee e : employees) {
            String empId = e.getEmployeeId();
            Employee employeeDirectReport = employeeRepository.findByEmployeeId(empId);
            listOfReports.add(employeeDirectReport.getFirstName() + " " + employeeDirectReport.getLastName());
            directReports(employeeDirectReport.getDirectReports());
        }
    }
}
