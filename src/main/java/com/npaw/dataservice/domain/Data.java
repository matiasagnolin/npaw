package com.npaw.dataservice.domain;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@lombok.Data
@XmlRootElement(name = "q")
@Entity
public class Data implements Serializable {
    @Id
    private String messageId;
    private String accountCode;
    private String targetDevice;
    private String pluginVersion;
    @XmlElement(name = "pt")
    private String pingTime;
    @XmlElement(name = "h")
    private String hostCluster;

}
