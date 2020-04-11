package online.xybh.community.service;

import online.xybh.community.enums.CommentTypeEnum;
import online.xybh.community.exception.CustomizeErrorCode;
import online.xybh.community.exception.CustomizeException;
import online.xybh.community.mapper.CommentMapper;
import online.xybh.community.mapper.QuestionExtMapper;
import online.xybh.community.mapper.QuestionMapper;
import online.xybh.community.model.Comment;
import online.xybh.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/18 0018 20:01
 * @Modified:
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM__NOT_FOUND);
        }
        if(comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question ==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionExtMapper.incCommentCount(question);
            //回复问题
        }

    }
}
