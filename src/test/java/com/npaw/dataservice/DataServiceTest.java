package com.npaw.dataservice;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.services.Finder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;


public class DataServiceTest extends ResquestHelperTest{

    @Autowired
    public Finder finder;
    public MultiValueMap<String,String> param;

    @Before
    public void setUp(){
        param = new LinkedMultiValueMap<>();
        param.add("accountCode","clienteA");
        param.add("targetDevice","XBox");
        param.add("pluginVersion","3.1.1");
    }

    @Test
    public void it_should_find_an_existing_account(){
        List<Client> list = finder.findDataByAccountCode(param.getFirst("accountCode"));
        Assert.assertTrue(!list.isEmpty());
    }
    @Test
    public void it_should_find_an_existing_account_and_device(){
        Optional<Client> client = finder.findDataByAccountCodeAndTargetDevice(
                param.getFirst("accountCode"),param.getFirst("targetDevice"));

        Assert.assertTrue(client.isPresent());
    }
    @Test
    public void it_should_not_find_account(){
        Optional<Client> client = finder.findDataByAccountCodeAndTargetDevice(
                param.getFirst("fakeAccount"),param.getFirst("fakeDevice"));

        List<Client> list2 =
                finder.findDataByAccountCode(param.getFirst("fakeAccount"));

        Assert.assertTrue(!client.isPresent());
        Assert.assertTrue(list2.isEmpty());
    }

    @Test
    public void it_should_get_a_xml_configuration() throws Exception {
        assertResponse("/getData",200,"GET",param);
    }

}
