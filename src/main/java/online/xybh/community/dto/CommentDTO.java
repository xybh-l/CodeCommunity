package online.xybh.community.dto;

import lombok.Data;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/18 0018 19:17
 * @Modified:
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
