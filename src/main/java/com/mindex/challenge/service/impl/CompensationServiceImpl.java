package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    @Autowired
    CompensationRepository compensationRepository;
    @Autowired
    EmployeeService employeeService;
    @Override
    public Compensation create(Compensation compensation) {

        compensationRepository.insert(compensation);
        return compensation;
    }

    @Override
    public Compensation read(String id) {

        Compensation compensation = compensationRepository.findByEmployee(id);
        if(compensation == null){
            throw new RuntimeException("Invalid compensation id: " + id);
        }
            return compensation;



    }
}
