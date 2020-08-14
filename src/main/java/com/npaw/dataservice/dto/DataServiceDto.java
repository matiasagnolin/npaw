package com.npaw.dataservice.dto;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "q")
@Data
public class DataServiceDto {
    @XmlElement
    private List<String> host;
    @XmlElement
    private String pingTime;
    @XmlElement
    private String uniqueHash;

}
