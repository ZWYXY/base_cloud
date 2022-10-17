package com.zr.pojo.po;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * (TestUser)实体类
 *
 * @author makejava
 * @since 2020-06-03 16:47:57
 */
@Data
public class TestUser implements Serializable {
    private static final long serialVersionUID = 170049852760450226L;
    /**
    * 主键
    */
    private Long testUserK;
    /**
    * 用户名
    */
    private String testUsername;
    
    private String testPassword;

    private List<RacesUnitsPO> racesUnitsPOList;

}