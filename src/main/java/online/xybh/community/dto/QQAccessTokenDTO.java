package online.xybh.community.dto;

import lombok.Data;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/13 0013 9:45
 * @Modified:
 */
@Data
public class QQAccessTokenDTO {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;
}
