package com.npaw.dataservice;

import com.npaw.dataservice.domain.Client;
import com.npaw.dataservice.domain.TargetDevice;
import com.npaw.dataservice.dto.DataServiceDto;
import com.npaw.dataservice.dto.DataServiceMapper;
import com.npaw.dataservice.services.ClientFinder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import java.util.Optional;

@Sql(scripts = "classpath:data.sql")
@Slf4j
public class DataServiceTest extends RequestHelperTest {

    @Autowired
    private ClientFinder clientFinder;

    @Test
    public void it_should_find_an_existing_account(){
        Optional<Client> client =
                clientFinder.findClientByAccountCode(param.getFirst("accountCode"));
        Assert.assertTrue(client.isPresent());
    }
    @Test
    public void it_should_find_an_existing_account_and_device(){
        Optional<Client> client =
                clientFinder.findClientByAccountCode(
                param.getFirst("accountCode"));

        Assert.assertTrue(client.isPresent());

        Optional<TargetDevice> td =
                client.get().getTargetDevice().stream()
                        .filter(e->e.getName().equals(
                                param.getFirst("targetDevice")))
                        .findFirst();

        Assert.assertTrue(td.isPresent());
    }
    @Test
    public void it_should_not_find_account(){
        Optional<Client> client =
                clientFinder.findClientByAccountCode("fakeAccount");

        Assert.assertTrue(!client.isPresent());

        Optional<Client> client2 = clientFinder.findClientByAccountCode(
                param.getFirst("accountCode"));

        Optional<TargetDevice> td =
                client2.get().getTargetDevice().stream()
                        .filter(e->e.getName().equals(
                                param.getFirst("fakeTarget")))
                        .findFirst();

        Assert.assertTrue(!td.isPresent());
    }

    @Test
    public void it_should_transform_from_entity_to_dto(){

        Optional<Client> client =
                clientFinder.findClientByAccountCode(
                        param.getFirst("accountCode"));

        TargetDevice td = client.get().getTargetDevice().stream()
                .filter(t -> t.getName().equals(param.getFirst("targetDevice")))
                .findFirst()
                .get();

        DataServiceDto dto =
                DataServiceMapper.transform(td);

        Assert.assertEquals(dto.getPingTime(),td.getPingTime());
        Assert.assertEquals(dto.getHost(), td.getBalancedHost().getHostDns());
    }

    @Test
    public void it_should_get_a_200() throws Exception {
        it_should_response_expected_status("/getData",200,"GET",param);
    }
    @Test
    public void it_should_get_an_empty_body() throws Exception {
        it_should_response_empty_body("/getData",200,"GET",paramWrong);
    }
    @Test
    public void it_should_balance_cluster_dns_integration_test() throws Exception {
        Optional<Client> client = null;
        int counterA=0;
        int counterB=0;
        for(int i=0;i<100;i++){

            client = clientFinder.findClientByAccountCode(
                            param.getFirst("accountCode"));
            TargetDevice td = client.get()
                    .getTargetDevice()
                    .stream()
                    .filter(t-> t.getName()
                            .equals(param.getFirst("targetDevice")))
                    .findFirst()
                    .get();

            String host = td.getBalancedHost().getHostDns();
            Integer currentStock = td.getBalancedHost().getCurrentStockDns();

            it_should_response_expected_status_and_xml_body("/getData",200,"GET",param);


            if(host.equals(dnsClusterA)){
                counterA= percentageClusterA- currentStock;
            }else{
                counterB = percentageClusterB- currentStock;
            }
        }
        Assert.assertEquals(percentageClusterA,counterA+1);
        Assert.assertEquals(percentageClusterB,counterB+1);

    }
}
