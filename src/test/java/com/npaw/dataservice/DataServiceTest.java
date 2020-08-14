package com.npaw.dataservice;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.domain.TargetDevice;
import com.npaw.dataservice.dto.DataServiceDto;
import com.npaw.dataservice.dto.DataServiceMapper;
import com.npaw.dataservice.services.Finder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.stream.Collectors;

@Sql(scripts = "classpath:data.sql")
public class DataServiceTest extends RequestHelperTest {

    @Autowired
    private Finder finder;

    @Test
    public void it_should_find_an_existing_account(){
        Optional<Client> client =
                finder.findClientByAccountCode(param.getFirst("accountCode"));
        Assert.assertTrue(client.isPresent());
    }
    @Test
    public void it_should_find_an_existing_account_and_device(){
        Optional<Client> client =
                finder.findClientByAccountCodeAndTargetDevice(
                param.getFirst("accountCode"),param.getFirst("targetDevice"));

        Assert.assertTrue(client.isPresent());
    }
    @Test
    public void it_should_not_find_account(){
        Optional<Client> client = finder.findClientByAccountCodeAndTargetDevice(
                param.getFirst("fakeAccount"),param.getFirst("fakeDevice"));

        Optional<Client> client2 =
                finder.findClientByAccountCode(param.getFirst("fakeAccount"));

        Assert.assertNull(client);
        Assert.assertTrue(!client2.isPresent());
    }

    @Test
    public void it_should_transform_from_entity_to_dto(){
        DataServiceDto dto =
                DataServiceMapper.transform(client.getTargetDevice()
                        ,param.getFirst("targetDevice"));

        TargetDevice td = targetDevices.stream()
                .filter(t -> t.getName().equals(param.getFirst("targetDevice")))
                .findFirst()
                .get();

        Assert.assertEquals(dto.getPingTime(),td.getPingTime());

        Assert.assertEquals(dto.getHost(), td.getHosts()
                .stream()
                .map( h -> h.getHostDns())
                .collect(Collectors.toList()));
    }

    @Test
    public void it_should_get_a_200() throws Exception {
        assertResponse("/getData",200,"GET",param);
    }

}
