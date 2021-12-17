package com.example.demo.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.NumberUtil;
import com.example.demo.mapper.SysMenuMapper;
import com.example.demo.model.entity.SysMenuEntity;
import com.example.demo.util.DicMap;
import com.example.demo.util.TreeStructureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public List<Tree<String>> getTrees1() {
        List<SysMenuEntity> entityList = sysMenuMapper.selectAll();
        String parentId = "0";
        // 配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setIdKey("menuId");
        treeNodeConfig.setParentIdKey("parentId");
        treeNodeConfig.setNameKey("menuName");
        treeNodeConfig.setWeightKey("orderNum");
        treeNodeConfig.setDeep(5);
        // 转换器
        return TreeUtil.build(entityList, parentId, treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getMenuId().toString());
                    tree.setParentId(treeNode.getParentId().toString());
                    tree.setName(treeNode.getMenuName());
//                    tree.setWeight(treeNode.getOrderNum());
//                    tree.putExtra("path", treeNode.getPath());
//                    tree.putExtra("component", treeNode.getComponent());
//                    tree.putExtra("isFrame", treeNode.getIsFrame());
//                    tree.putExtra("isCache", treeNode.getIsCache());
//                    tree.putExtra("menuType", treeNode.getMenuType());
//                    tree.putExtra("visible", treeNode.getVisible());
//                    tree.putExtra("status", treeNode.getStatus());
//                    tree.putExtra("perms", treeNode.getPerms());
//                    tree.putExtra("icon", treeNode.getIcon());
//                    tree.putExtra("createBy", treeNode.getCreateBy());
//                    tree.putExtra("createTime", treeNode.getCreateTime());
//                    tree.putExtra("updateBy", treeNode.getUpdateBy());
//                    tree.putExtra("updateTime", treeNode.getUpdateTime());
                    tree.putExtra("remark", treeNode.getRemark());
                });
    }

    public Object getTrees2() {
        List<SysMenuEntity> entityList = sysMenuMapper.selectAll();
        try {
            List<SysMenuEntity> sysMenuEntities = TreeStructureUtil.listToTree(entityList);
            return sysMenuEntities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getTrees3() {
        List<SysMenuEntity> entityList = sysMenuMapper.selectAll();
        try {
            List<SysMenuEntity> sysMenuEntities = com.example.demo.util.TreeUtil.toTreeList(entityList, 0L, Long.class, SysMenuEntity.class);
            return sysMenuEntities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getDir() {
        String fieldDetail = DicMap.getFieldDetail("product", "type", "1");
        System.out.println("fieldDetail = " + fieldDetail);
        return fieldDetail;
    }
}
