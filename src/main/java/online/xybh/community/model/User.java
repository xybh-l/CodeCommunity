package online.xybh.community.model;

import lombok.Data;

/**
 * @Author: XYBH
 * @Description: User实体类
 * @Date: Created in 2020/2/6 0006 1:35
 * @Modified:
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private String bio;
    private Long gmtCreate;
    private Long gmtModified;
}
