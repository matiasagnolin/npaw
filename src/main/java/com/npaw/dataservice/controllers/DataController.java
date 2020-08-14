package com.npaw.dataservice.controllers;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.dto.DataServiceDto;
import com.npaw.dataservice.dto.DataServiceMapper;
import com.npaw.dataservice.services.Finder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DataController {
    @Autowired
    Finder finder;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<Client> index(){
        return ((List<Client>) finder.findAll());
    }

    @GetMapping(value="/getData",  produces= MediaType.APPLICATION_XML_VALUE)
    public DataServiceDto getConfigurationByClientAndDevice(@RequestParam String accountCode,
                                                            @RequestParam String targetDevice,
                                                            @RequestParam String pluginVersion){
        Optional<Client> client =
                finder.findClientByAccountCodeAndTargetDevice(accountCode,targetDevice);

        if(client.isPresent()) {
            return convertToDto(client.get(),targetDevice);
        }

        return null;

    }

    private DataServiceDto convertToDto(Client client, String targetDevice) {
        DataServiceDto serviceDto =
                DataServiceMapper.transform(client.getTargetDevice(),targetDevice);
        return serviceDto;
    }

}
