package online.xybh.community.mapper;

import online.xybh.community.model.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}