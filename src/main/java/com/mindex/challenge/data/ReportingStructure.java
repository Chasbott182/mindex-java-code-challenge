package com.mindex.challenge.data;

import com.mindex.challenge.dao.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ReportingStructure {

    private final Employee employee;
    private final int numberOfReports;
    private EmployeeRepository employeeRepository;
    private List<String> directReports = new ArrayList<>();
    public ReportingStructure(Employee employee, EmployeeRepository employeeRepository) {
        this.employee = employee;
        this.employeeRepository = employeeRepository;
        this.numberOfReports = getNumberOfDirects();
    }

    public String getEmployeeFullName() {

        return String.format(employee.getFirstName()+" "+employee.getLastName()+" (employeeId: "+employee.getEmployeeId()+")");
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public String getDirectReports(){
        return directReports.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    private int getNumberOfDirects() {
        if(Optional.ofNullable(employee).isPresent()){
            directReports(employee.getDirectReports());
        }
        return directReports.size();
    }

    private void directReports(List<Employee> employees){
        if(!Optional.ofNullable(employees).isPresent()) {
            return;
        }
        for (Employee e : employees) {
            String empId = e.getEmployeeId();
            Employee employeeDirectReport = employeeRepository.findByEmployeeId(empId);
            directReports.add(employeeDirectReport.getFirstName() + " " + employeeDirectReport.getLastName());
            directReports(employeeDirectReport.getDirectReports());
        }

    }

}
