package com.example.demo.util;

import cn.hutool.core.util.ReflectUtil;
import com.example.demo.annotation.TreeEntity;
import com.example.demo.annotation.TreeField;
import com.example.demo.enums.FieldType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 树结构实体通用工具类
 * 注意pid和id的类型必须一致
 * 引入hutool工具类
 *
 * @author meixin
 * @date 2021-06-30
 */
public final class TreeUtil {

    /**
     * 注解属性容量
     */
    private final static Integer FIELD_SIZE = 6 >> 1;

    /**
     * Map<FieldType String> ---> Map<[注解属性类型,[字段名]>
     */
    private static Map<FieldType, String> fieldsMap = new HashMap<>(8);

    /**
     * @param list      所有列表
     * @param rootNode  根节点
     * @param treeClazz 树状元素类型
     * @param <E>       传入元素
     * @return 树状结构实体
     */
    public static <E> E toTree(List<E> list, E rootNode, Class<E> treeClazz) {
        if (fieldsMap.size() != FIELD_SIZE) {
            fieldsMap = getFieldsMap(treeClazz);
        }
        Map<Object, E> map = new HashMap<>(2);
        Object id = ReflectUtil.getFieldValue(rootNode, fieldsMap.get(FieldType.ID));
        Object pid = ReflectUtil.getFieldValue(rootNode, fieldsMap.get(FieldType.PID));
        map.put(id, rootNode);
        for (E childNode : list) {
            compose(pid, map, childNode);
        }
        return map.get(id);
    }

    /**
     * 引用写法 绕过递归
     *
     * @param list      所有列表
     * @param pid       父级id
     * @param pidClazz  父级id类型
     * @param treeClazz 树状元素类型
     * @param <E>       传入元素
     * @return 树状结构集合
     */
    public static <E> List<E> toTreeList(List<E> list, Object pid, Class<?> pidClazz, Class<E> treeClazz) {
        if (!pid.getClass().getTypeName().equals(pidClazz.getTypeName())) {
            throw new RuntimeException(String.format("pid类型不一致: %s and %s", pid.getClass().getName(), pidClazz.getName()));
        }
        if (pid instanceof String || pid instanceof Integer || pid instanceof Long) {
            if (fieldsMap.size() != FIELD_SIZE) {
                fieldsMap = getFieldsMap(treeClazz);
            }
            Map<Object, E> map = new HashMap<>(2);
            // 用来存放根节点
            List<E> rootNodes = new ArrayList<>();
            for (E o : list) {
                Object tPid = ReflectUtil.getFieldValue(o, fieldsMap.get(FieldType.PID));
                // 如果是父
                if (tPid.equals(pid)) {
                    rootNodes.add(o);
                    map.put(pid, o);
                }
            }
            for (E childNode : list) {
                if (childNode != null) {
                    compose(pid, map, childNode);
                }
            }
            return rootNodes;
        }
        throw new RuntimeException("父Id必须是其中之一:[String,Long,Integer]");
    }

    private static <E> void compose(Object pid, Map<Object, E> map, E childNode) {
        Object tId = ReflectUtil.getFieldValue(childNode, fieldsMap.get(FieldType.ID));
        map.put(tId, childNode);
        Object tPid = ReflectUtil.getFieldValue(childNode, fieldsMap.get(FieldType.PID));
        if (!tPid.equals(pid)) {
            //父节点
            E parentNode = map.get(tPid);
            //给父节点的child属性赋当前节点
            List<E> tChild = (List<E>) ReflectUtil.getFieldValue(parentNode, fieldsMap.get(FieldType.CHILD));
            if (tChild == null) {
                tChild = new ArrayList<>();
            }
            tChild.add(childNode);
            if (parentNode != null) {
                ReflectUtil.setFieldValue(parentNode, fieldsMap.get(FieldType.CHILD), tChild);
            }
        }
    }

    /**
     * 获取 Field Map
     *
     * @param clazz 类名
     * @return Map<FieldType String> ---> Map<[注解属性类型,[字段名]>
     */
    private static <E> Map<FieldType, String> getFieldsMap(Class<E> clazz) {
        // 获取树状结构实体
        TreeEntity treeEntity = clazz.getAnnotation(TreeEntity.class);
        // 判断注解是否为空
        if (treeEntity == null) {
            throw new RuntimeException("此实体类不是树实体:请加上注解@TreeEntity");
        }
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            TreeField treeField = field.getAnnotation(TreeField.class);
            if (treeField != null) {
                if (treeField.fieldType().equals(FieldType.ID)) {
                    fieldsMap.put(FieldType.ID, field.getName());
                }
                if (treeField.fieldType().equals(FieldType.PID)) {
                    fieldsMap.put(FieldType.PID, field.getName());
                }
                if (treeField.fieldType().equals(FieldType.CHILD)) {
                    fieldsMap.put(FieldType.CHILD, field.getName());
                }
            }
        });
        if (fieldsMap.size() < FIELD_SIZE) {
            throw new RuntimeException("缺少某一个属性: FieldType.ID | FieldType.PID | FieldType.CHILD)");
        }
        return fieldsMap;
    }
}
