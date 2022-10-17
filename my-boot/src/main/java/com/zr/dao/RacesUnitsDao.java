package com.zr.dao;

import com.zr.pojo.po.RacesUnitsPO;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (RacesUnits)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-04 09:57:26
 */
@Mapper
public interface RacesUnitsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RacesUnitsPO queryById(Long id);

    List<RacesUnitsPO> queryAllByRace(Long race);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RacesUnitsPO> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param racesUnitsPO 实例对象
     * @return 对象列表
     */
    List<RacesUnitsPO> queryAll(RacesUnitsPO racesUnitsPO);

    /**
     * 新增数据
     *
     * @param racesUnitsPO 实例对象
     * @return 影响行数
     */
    int insert(RacesUnitsPO racesUnitsPO);

    /**
     * 修改数据
     *
     * @param racesUnitsPO 实例对象
     * @return 影响行数
     */
    int update(RacesUnitsPO racesUnitsPO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}