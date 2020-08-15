package com.npaw.dataservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Host implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String hostDns;

    @Column
    private Integer originalStockDns;

    @Column
    private Integer currentStockDns;

    public Host() {
    }
    public Host(String hostDns,Integer stockDns) {
        this.hostDns = hostDns;
        this.originalStockDns = stockDns;
        this.originalStockDns = stockDns;
    }

}
