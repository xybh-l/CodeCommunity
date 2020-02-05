package online.xybh.community.dto;

import lombok.Data;

/**
 * @Author: XYBH
 * @Description: Data Transfer Object 数据传输对象
 * @Date: Created in 2020/2/5 0005 23:33
 * @Modified:
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;
}
