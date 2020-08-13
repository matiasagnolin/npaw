package com.npaw.dataservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Client implements Serializable {
    @Id
    private String accountCode;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TargetDevice> targetDevice;

    @Column
    private String pluginVersion;

    @Column
    private String pingTime;

}
