package ogr.spring.service.impl;

import ogr.spring.dao.TestUserDao;
import ogr.spring.pojo.po.TestUser;
import ogr.spring.service.TestUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TestUser)表服务实现类
 *
 * @author makejava
 * @since 2020-06-03 16:47:57
 */
@Service("testUserService")
public class TestUserServiceImpl implements TestUserService {
    @Resource
    private TestUserDao testUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param testUserK 主键
     * @return 实例对象
     */
    @Override
    public TestUser queryById(Long testUserK) {
        return this.testUserDao.queryById(testUserK);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TestUser> queryAllByLimit(int offset, int limit) {
        return this.testUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param testUser 实例对象
     * @return 实例对象
     */
    @Override
    public TestUser insert(TestUser testUser) {
        this.testUserDao.insert(testUser);
        return testUser;
    }

    /**
     * 修改数据
     *
     * @param testUser 实例对象
     * @return 实例对象
     */
    @Override
    public TestUser update(TestUser testUser) {
        this.testUserDao.update(testUser);
        return this.queryById(testUser.getTestUserK());
    }

    /**
     * 通过主键删除数据
     *
     * @param testUserK 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long testUserK) {
        return this.testUserDao.deleteById(testUserK) > 0;
    }

    /**
     * 嵌套查询
     *
     * @param testUserK 主键
     * @return 实例对象
     */
    @Override
    public TestUser queryAssociationById(Long testUserK) {
        return testUserDao.queryAssociationById(testUserK);
    }

    /**
     * 嵌套结果
     *
     * @param testUserK 主键
     * @return 实例对象
     */
    @Override
    public TestUser queryResultEmbedById(Long testUserK) {
        return testUserDao.queryResultEmbedById(testUserK);
    }
}