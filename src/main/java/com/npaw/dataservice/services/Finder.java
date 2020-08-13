package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Finder implements IFinderService{

    @Autowired
    public ClientRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findDataByAccountCode(String accountCode){
        return repository.findDataByAccountCode(accountCode);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findDataByAccountCodeAndTargetDevice(String accountCode, String device) {
        return repository.findDataByAccountCodeAndTargetDevice(accountCode,device);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Client> findAll() {
        return repository.findAll();
    }
}
