package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Data;

import java.util.List;

public interface IFinderService {
     List<Data> findDataByAccountCode( String accountCode);
    List<Data> findDataByAccountCodeAndTagetDevice(String accountCode,String device);
    Iterable<Data>findAll();
}
