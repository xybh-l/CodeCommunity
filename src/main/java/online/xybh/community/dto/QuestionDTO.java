package online.xybh.community.dto;

import lombok.Data;
import online.xybh.community.model.User;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/7 0007 16:54
 * @Modified:
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
