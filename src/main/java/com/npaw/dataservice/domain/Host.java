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
    private Integer percentage;

    public Host() {
    }
    public Host(String hostDns,Integer percentage) {
        this.hostDns=hostDns;
        this.percentage=percentage;
    }

}
