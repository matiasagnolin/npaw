package com.npaw.dataservice.repository;


import com.npaw.dataservice.domain.Host;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends CrudRepository<Host,Integer> {
}
