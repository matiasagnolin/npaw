package com.npaw.dataservice.dto;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "q")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class DataServiceDto {

    @XmlElement(name="h")
    private String host;

    @XmlElement(name = "pt")
    private String pingTime;

    @XmlElement(name="c")
    private String uniqueCode;



}
