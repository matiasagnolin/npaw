package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class Finder implements IFinderService{

    @Autowired
    public ClientRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findClientByAccountCode(String accountCode){
        return repository.findDataByAccountCode(accountCode);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findClientByAccountCodeAndTargetDevice(String accountCode
            , String targetDevice) {
        Optional<Client> client =
                repository.findDataByAccountCode(accountCode);

        //check if client and target device exist
        return client.filter(c -> c.getTargetDevice()
                        .stream()
                        .anyMatch(e->e.getName().equals(targetDevice)))
                        .isPresent() ? client : null;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Client> findAll() {
        return repository.findAll();
    }
}
