package com.npaw.dataservice.dto;

import com.npaw.dataservice.domain.TargetDevice;

import java.util.List;
import java.util.UUID;

public class DataServiceMapper {

    public static DataServiceDto transform(TargetDevice targetDevice){
        DataServiceDto ds = new DataServiceDto();

        ds.setPingTime(targetDevice.getPingTime());

        ds.setHost(targetDevice.getBalancedHost().getHostDns());

        ds.setUniqueCode(UUID.randomUUID()
                .toString()
                .replace("-",""));

        return ds;
    }



}
