package com.npaw.dataservice.repository;

import com.npaw.dataservice.domain.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DataRepository extends CrudRepository<Data,String> {
    List<Data> findDataByAccountCode(String accountCode);
    List<Data> findDataByAccountCodeAndTargetDevice(String accountCode, String targetDevice);
}
