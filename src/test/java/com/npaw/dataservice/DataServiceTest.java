package com.npaw.dataservice;

import com.npaw.dataservice.domain.Data;
import com.npaw.dataservice.services.Finder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;


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
        List<Data> list =finder.findDataByAccountCode(param.getFirst("accountCode"));
        Assert.assertTrue(!list.isEmpty());
    }
    @Test
    public void it_should_find_an_existing_account_and_device(){
        List<Data> list = finder.findDataByAccountCodeAndTagetDevice(
                param.getFirst("accountCode"),param.getFirst("targetDevice"));

        Assert.assertTrue(!list.isEmpty());
    }
    @Test
    public void it_should_not_find_account(){
        List<Data> list = finder.findDataByAccountCodeAndTagetDevice(
                param.getFirst("fakeAccount"),param.getFirst("fakeDevice"));
        List<Data> list2 =finder.findDataByAccountCode(param.getFirst("fakeAccount"));
        Assert.assertTrue(list.isEmpty());
        Assert.assertTrue(list2.isEmpty());
    }

    @Test
    public void it_should_get_a_xml_configuration() throws Exception {
        assertResponse("/getData",200,"get",param);
    }

}
