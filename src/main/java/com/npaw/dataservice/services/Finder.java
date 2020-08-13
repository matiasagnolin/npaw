package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Data;
import com.npaw.dataservice.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Finder implements IFinderService{

    @Autowired
    public DataRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Data> findDataByAccountCode(String accountCode){
        return repository.findDataByAccountCode(accountCode);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Data> findDataByAccountCodeAndTagetDevice(String accountCode,String device) {
        return repository.findDataByAccountCodeAndTargetDevice(accountCode,device);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Data> findAll() {
        return repository.findAll();
    }
}
