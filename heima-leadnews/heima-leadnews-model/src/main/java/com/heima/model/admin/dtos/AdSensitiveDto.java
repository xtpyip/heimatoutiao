package com.heima.model.admin.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class AdSensitiveDto {

    private Date createdTime;

    private Integer id;

    private String sensitives;

}