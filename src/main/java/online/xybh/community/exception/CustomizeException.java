package online.xybh.community.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/13 0013 17:37
 * @Modified:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
