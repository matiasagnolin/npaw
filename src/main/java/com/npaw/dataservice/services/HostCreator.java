package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Host;
import com.npaw.dataservice.domain.TargetDevice;
import com.npaw.dataservice.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class HostCreator implements IHostCreator {

    @Autowired
    HostRepository hostRepository;

    @Override
    @Transactional
    public void restartHostCurrentStock(TargetDevice targetDevice) {
        for(Host host : targetDevice.getHosts()){
            host.setCurrentStockDns(host.getOriginalStockDns());
            hostRepository.save(host);
        }
    }

    @Override
    @Transactional
    public void updateCurrentStock(Host host) {
        host.setCurrentStockDns(host.getCurrentStockDns()-1);
        hostRepository.save(host);
    }
}
