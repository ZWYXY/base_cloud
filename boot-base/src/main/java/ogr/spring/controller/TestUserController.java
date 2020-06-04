package ogr.spring.controller;

import ogr.spring.pojo.po.TestUser;
import ogr.spring.result.Result;
import ogr.spring.result.ResultUtil;
import ogr.spring.service.TestUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TestUser)表控制层
 *
 * @author makejava
 * @since 2020-06-03 16:47:57
 */
@RestController
@RequestMapping("testUser")
public class TestUserController {
    /**
     * 服务对象
     */
    @Resource
    private TestUserService testUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Result<TestUser> selectOne(Long id) {
        return ResultUtil.success(this.testUserService.queryById(id));
    }

    @GetMapping("selectOneAs")
    public Result<TestUser> selectOneAs(Long id) {
        return ResultUtil.success(this.testUserService.queryAssociationById(id));
    }

    @GetMapping("selectOneEmbed")
    public Result<TestUser> selectOneEmbed(Long id) {
        return ResultUtil.success(this.testUserService.queryResultEmbedById(id));
    }
}