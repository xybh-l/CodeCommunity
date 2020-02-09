package online.xybh.community.model;

import lombok.Data;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/6 0006 22:33
 * @Modified:
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private String creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
