package ogr.spring.dao;

import ogr.spring.pojo.po.TestUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TestUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-03 16:47:57
 */
@Mapper
public interface TestUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param testUserK 主键
     * @return 实例对象
     */
    TestUser queryById(Long testUserK);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TestUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param testUser 实例对象
     * @return 对象列表
     */
    List<TestUser> queryAll(TestUser testUser);

    /**
     * 新增数据
     *
     * @param testUser 实例对象
     * @return 影响行数
     */
    int insert(TestUser testUser);

    /**
     * 修改数据
     *
     * @param testUser 实例对象
     * @return 影响行数
     */
    int update(TestUser testUser);

    /**
     * 通过主键删除数据
     *
     * @param testUserK 主键
     * @return 影响行数
     */
    int deleteById(Long testUserK);

}