package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

import java.util.Date;

public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String id);
}
