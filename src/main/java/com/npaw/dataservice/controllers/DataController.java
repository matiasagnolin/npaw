package com.npaw.dataservice.controllers;

import com.npaw.dataservice.domain.Data;
import com.npaw.dataservice.services.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/getData")
public class DataController {
    @Autowired
    Finder finder;

    @GetMapping("/")
    public List<Data> index(){
        return (List<Data>) finder.findAll();
    }
    @GetMapping(value="/",  produces= MediaType.APPLICATION_XML_VALUE)
    public List<Data> getConfigurationByClientAndDevice(@PathVariable String accountCode,
                                                        @PathVariable String targetDevice,
                                                        @PathVariable String pluginVersion){
        return
                finder.findDataByAccountCodeAndTagetDevice(accountCode,targetDevice);
    }

}
