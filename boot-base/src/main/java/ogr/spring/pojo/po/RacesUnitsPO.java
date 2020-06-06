package ogr.spring.pojo.po;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * (RacesUnits)实体类
 *
 * @author makejava
 * @since 2020-06-04 09:57:26
 */
@Data
@ToString
public class RacesUnitsPO implements Serializable {
    private static final long serialVersionUID = -30871578203601037L;
    
    private Long id;
    /**
    * 种族
    */
    private Long race;
    /**
    * 兵种
    */
    private String soldierKind;
    /**
    * 战斗方式 近战 远程 魔法  物攻 战斗 给养
    */
    private String combatStyle;
    /**
    * 血量
    */
    private Integer hp;
    /**
    * 魔法值
    */
    private Integer mp;
    /**
    * 更新时间时间戳
    */
    private Long updateTimestamp;
    /**
    * 更新时间
    */
    private Date updateTime;


}