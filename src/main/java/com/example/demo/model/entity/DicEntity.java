package com.example.demo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典表
 */
@Data
public class DicEntity implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 表名
     */
    @ApiModelProperty(value = "表名")
    private String tableName;

    /**
     * 字段名
     */
    @ApiModelProperty(value = "字段名")
    private String fieldName;

    /**
     * 字段值
     */
    @ApiModelProperty(value = "字段值")
    private String fieldValue;

    /**
     * 字段明细
     */
    @ApiModelProperty(value = "字段明细")
    private String fieldDetail;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String describe;

    private static final long serialVersionUID = 1L;
}