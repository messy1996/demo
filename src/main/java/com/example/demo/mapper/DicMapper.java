package com.example.demo.mapper;

import com.example.demo.model.entity.DicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DicMapper {
    int deleteByPrimaryKey(String id);

    int insert(DicEntity record);

    int insertOrUpdate(DicEntity record);

    int insertOrUpdateSelective(DicEntity record);

    int insertSelective(DicEntity record);

    DicEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DicEntity record);

    int updateByPrimaryKey(DicEntity record);

    int updateBatch(List<DicEntity> list);

    int updateBatchSelective(List<DicEntity> list);

    int batchInsert(@Param("list") List<DicEntity> list);

    /**
     * 查询所有
     * @return
     */
    List<DicEntity> selectAll();
}