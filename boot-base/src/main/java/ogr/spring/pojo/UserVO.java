package ogr.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long id;
    private String username;
    private String password;
    private String[] hobby;

    public UserVO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
