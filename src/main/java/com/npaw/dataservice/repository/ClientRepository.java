package com.npaw.dataservice.repository;

import com.npaw.dataservice.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client,String> {
    Optional<Client> findDataByAccountCode(String accountCode);
}
