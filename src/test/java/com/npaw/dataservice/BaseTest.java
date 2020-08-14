package com.npaw.dataservice;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.domain.Host;
import com.npaw.dataservice.domain.TargetDevice;
import org.junit.Before;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest {
    protected Client client;
    protected MultiValueMap<String,String> param;
    protected List<TargetDevice> targetDevices;
    protected List<Host> hosts;

    @Before
    public void setUp(){
        param = new LinkedMultiValueMap<>();
        param.add("accountCode","ClienteA");
        param.add("targetDevice","XBox");
        param.add("pluginVersion","3.1.1");

        client = new Client();
        client.setAccountCode(param.getFirst("accountCode"));
        targetDevices = new ArrayList<>();
        hosts = new ArrayList<>();
        hosts.add(new Host(
                "clienteA.com",
                70));
        hosts.add(new Host(
                "clienteB.com",
                30));
        TargetDevice targetDevice = new TargetDevice(
                param.getFirst("targetDevice"),
                param.getFirst("pluginVersion"),
                "10");
        targetDevice.setHosts(hosts);
        targetDevices.add(targetDevice);
        client.setTargetDevice(targetDevices);
    }
}
