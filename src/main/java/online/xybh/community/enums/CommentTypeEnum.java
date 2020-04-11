package online.xybh.community.enums;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/18 0018 19:58
 * @Modified:
 */

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type){
        for (CommentTypeEnum commentTypeEnum :
                CommentTypeEnum.values()) {
            if (commentTypeEnum.getType().equals(type)){
                return true;
            }
        }
        return false;
    }

}
