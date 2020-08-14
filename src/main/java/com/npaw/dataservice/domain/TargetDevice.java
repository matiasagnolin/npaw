package com.npaw.dataservice.domain;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class TargetDevice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column
    public String name;

    @Column
    private String pluginVersion;

    @Column
    private String pingTime;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Host> hosts;
}
