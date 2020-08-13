package com.npaw.dataservice.dto;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "q")
@Data
public class DataServiceDto {
    @XmlElement
    private String h;
    @XmlElement
    private String pt;
    @XmlElement
    private String c;

}
