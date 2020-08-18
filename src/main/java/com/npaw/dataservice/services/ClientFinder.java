package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientFinder implements IFinderService{

    @Autowired
    public ClientRepository repository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "Client",key = "#p0")
    public Optional<Client> findClientByAccountCode(String accountCode){
        return repository.findDataByAccountCode(accountCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Client> findAll() {
        return repository.findAll();
    }


}
