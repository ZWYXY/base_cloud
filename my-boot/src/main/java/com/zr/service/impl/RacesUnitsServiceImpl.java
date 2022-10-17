package com.zr.service.impl;

import com.zr.dao.RacesUnitsDao;
import com.zr.pojo.po.RacesUnitsPO;
import com.zr.service.RacesUnitsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RacesUnits)表服务实现类
 *
 * @author makejava
 * @since 2020-06-09 16:13:20
 */
@Service("racesUnitsService")
public class RacesUnitsServiceImpl implements RacesUnitsService {
    @Resource
    private RacesUnitsDao racesUnitsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RacesUnitsPO queryById(Long id) {
        return this.racesUnitsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RacesUnitsPO> queryAllByLimit(int offset, int limit) {
        return this.racesUnitsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param racesUnits 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(propagation = Propagation.NESTED)
    public RacesUnitsPO insert(RacesUnitsPO racesUnits) {
        this.racesUnitsDao.insert(racesUnits);
        return racesUnits;
    }

    /**
     * 修改数据
     *
     * @param racesUnits 实例对象
     * @return 实例对象
     */
    @Override
    public RacesUnitsPO update(RacesUnitsPO racesUnits) {
        this.racesUnitsDao.update(racesUnits);
        return this.queryById(racesUnits.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public boolean deleteById(Long id) {
        return this.racesUnitsDao.deleteById(id) > 0;
    }
}