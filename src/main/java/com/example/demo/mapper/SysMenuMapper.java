package com.example.demo.mapper;

import com.example.demo.model.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenuEntity record);

    int insertOrUpdate(SysMenuEntity record);

    int insertOrUpdateSelective(SysMenuEntity record);

    int insertSelective(SysMenuEntity record);

    SysMenuEntity selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenuEntity record);

    int updateByPrimaryKey(SysMenuEntity record);

    int updateBatch(List<SysMenuEntity> list);

    int updateBatchSelective(List<SysMenuEntity> list);

    int batchInsert(@Param("list") List<SysMenuEntity> list);

    /**
     * 查询所有
     *
     * @return
     */
    List<SysMenuEntity> selectAll();
}