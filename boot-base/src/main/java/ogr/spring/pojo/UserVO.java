package ogr.spring.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户类，用来测试")
public class UserVO {
    @ApiModelProperty(value = "用户唯一标识", example = "1" )
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("爱好")
    private String[] hobby;

    public UserVO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    //  @ApiModel("用户类，用来测试") 注解于实体类
    //  @ApiModelProperty("用户名") 注解于类中属性，表示对model属性的说明或者数据操作更改
}
