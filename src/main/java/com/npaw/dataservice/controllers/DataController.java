package com.npaw.dataservice.controllers;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.domain.TargetDevice;
import com.npaw.dataservice.dto.DataServiceDto;
import com.npaw.dataservice.dto.DataServiceMapper;
import com.npaw.dataservice.services.ClientFinder;

import com.npaw.dataservice.services.HostCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class DataController {
    @Autowired
    private ClientFinder clientFinder;
    @Autowired
    private HostCreator hostCreator;

    @GetMapping("/all")
    public List<Client> index(){
        return ((List<Client>) clientFinder.findAll());
    }

    @GetMapping(value="/getData",  produces= MediaType.APPLICATION_XML_VALUE)
    public DataServiceDto getConfigurationByClientAndDevice(@RequestParam String accountCode,
                                                            @RequestParam String targetDevice,
                                                            @RequestParam String pluginVersion){
        DataServiceDto respond = null;
        Optional<TargetDevice> td = Optional.ofNullable(null);


        Optional<Client> client =
                clientFinder.findClientByAccountCode(accountCode);

        if(client.isPresent()){
            td = client.get()
                    .getTargetDevice()
                    .stream()
                    .filter(e->e.getName().equals(targetDevice))
                    .findFirst();
        }

        if(td.isPresent()){
            if(!td.get().checkIfHostsAvailable()){
                hostCreator.restartHostCurrentStock(td.get());
            }
            respond =
                    convertToDto(td.get());
            hostCreator.updateCurrentStock(td.get().getBalancedHost());
        }

        return respond;
    }

    private DataServiceDto convertToDto(TargetDevice targetDevice) {
        DataServiceDto serviceDto =
                DataServiceMapper.transform(targetDevice);
        return serviceDto;
    }

}
