package ogr.spring.service;

import ogr.spring.pojo.po.RacesUnitsPO;

import java.util.List;

/**
 * (RacesUnits)表服务接口
 *
 * @author makejava
 * @since 2020-06-09 16:13:20
 */
public interface RacesUnitsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RacesUnitsPO queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RacesUnitsPO> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param racesUnits 实例对象
     * @return 实例对象
     */
    RacesUnitsPO insert(RacesUnitsPO racesUnits);

    /**
     * 修改数据
     *
     * @param racesUnits 实例对象
     * @return 实例对象
     */
    RacesUnitsPO update(RacesUnitsPO racesUnits);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}