package com.npaw.dataservice.dto;

import com.npaw.dataservice.domain.TargetDevice;

import java.util.List;
import java.util.stream.Collectors;

public class DataServiceMapper {

    public static DataServiceDto transform(List<TargetDevice> targetDevices, String requestDevice){
        DataServiceDto ds = new DataServiceDto();

        TargetDevice td = targetDevices.stream()
                .filter(t -> t.getName().equals(requestDevice))
                .findFirst()
                .get();

        ds.setPingTime(td.getPingTime());

        ds.setHost(td.getHosts().stream()
                .map( h-> h.getHostDns())
                .collect(Collectors.toList()));

        return ds;
    }


}
