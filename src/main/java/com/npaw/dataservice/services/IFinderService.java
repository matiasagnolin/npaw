package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Client;
import java.util.List;
import java.util.Optional;

public interface IFinderService {

    Optional<Client> findClientByAccountCode(String accountCode);
    Optional<Client> findClientByAccountCodeAndTargetDevice(String accountCode, String device);
    Iterable<Client>findAll();
}
