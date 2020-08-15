package com.npaw.dataservice.domain;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    public TargetDevice() {
    }

    public TargetDevice(String name, String pluginVersion, String pingTime) {
        this.name = name;
        this.pingTime = pingTime;
        this.pluginVersion = pluginVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetDevice that = (TargetDevice) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean checkIfHostsAvailable(){
        for(Host host : hosts){
            if(host.getCurrentStockDns()>0){
                return true;
            }
        }
        return false;
    }

    public Host getBalancedHost() {
        Host balanced = null;
        for (Host host : hosts) {
            if (host.getCurrentStockDns() > 0) {
                balanced= host;
            }
        }
        return balanced;
    }
}
