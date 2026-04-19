package cn.lingxiaolu.chatbot.api.domain.zsxq;

import cn.lingxiaolu.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;

import java.io.IOException;

/**
 * @author 绫小路
 * @description 知识星球 API 接口
 * @github https://github.com/lingxiaolu-glitch
 **/
public interface IZsxqApi {

    UnAnsweredQuestionAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text) throws IOException;
}
