package com.example.demo.model.entity;

import com.example.demo.annotation.TreeElement;
import lombok.Data;

import java.util.List;

/**
 * 菜单权限表
 */
@Data
public class SysMenuEntity {
    /**
     * 菜单ID
     */
    @TreeElement(name = "id")
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    @TreeElement(name = "fid")
    private Long parentId;

    /**
     * 备注
     */
    private String remark;

    @TreeElement(name = "children")
    private List<SysMenuEntity> children;

}