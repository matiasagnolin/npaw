package com.npaw.dataservice.services;

import com.npaw.dataservice.domain.Host;
import com.npaw.dataservice.domain.TargetDevice;

public interface IHostCreator {
    void restartHostCurrentStock(TargetDevice targetDevice);
    void updateCurrentStock(Host host);

}
