package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Client;
import java.util.List;
import java.util.Optional;

public interface IFinderService {

    Optional<Client> findDataByAccountCode( String accountCode);
    Optional<Client> findDataByAccountCodeAndTargetDevice(String accountCode, String device);
    Iterable<Client>findAll();
}
