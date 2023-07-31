package com.example.demo.easycodedemo.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (CaPriceDangerWarning)实体类
 *
 * @author makejava
 * @since 2022-12-12 14:45:17
 */
@Data
public class CaPriceDangerWarning implements Serializable {
    private static final long serialVersionUID = 154649595456142728L;
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 预警时间
     */
    private Date warningDate;
    /**
     * 预警产品
     */
    private String warningProduct;
    /**
     * 预警级别
     */
    private Integer warningLevel;
    /**
     * 预警原因
     */
    private String warningReason;
    
    private Integer deleteFlag;

}

