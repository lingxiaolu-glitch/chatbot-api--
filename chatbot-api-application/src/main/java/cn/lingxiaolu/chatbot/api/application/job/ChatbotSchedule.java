package cn.lingxiaolu.chatbot.api.application.job;

import cn.lingxiaolu.chatbot.api.domain.ai.IEveryThingAI;
import cn.lingxiaolu.chatbot.api.domain.zsxq.IZsxqApi;
import cn.lingxiaolu.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import cn.lingxiaolu.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @author 绫小路
 * @description 问题任务
 * @github https://github.com/lingxiaolu-glitch
 **/
@EnableScheduling
@Configuration
public class ChatbotSchedule {
    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    private static final int CONSTANT_0 = 0;

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IEveryThingAI modelScopeAI;

    // 表达式网站：cron.qqe2.com
    @Scheduled(cron = "0 0/5 * * * ?")
    public void run() {
        try {
            //防止风控
            if(new Random().nextBoolean()) {
                logger.info("随机打样中...");
                return;
            }

            //半夜不回消息
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if(hour > 22 || hour <7) {
                logger.info("打烊时间不工作，AI下班了");
                return;
            }


            // 1. 检索问题
            UnAnsweredQuestionAggregates unAnsweredQuestionAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("检索结果:{}", JSON.toJSONString(unAnsweredQuestionAggregates));
            if(unAnsweredQuestionAggregates == null){
                logger.info("本次检索未查询到知识星球");
            }

            List<Topics> topics = unAnsweredQuestionAggregates.getResp_data().getTopics();
            if(topics == null || topics.isEmpty()) {
                logger.info("本次检索未查询到待回答问题");
                return;
            }

            Topics topic = topics.get(CONSTANT_0);
            // 2. AI回答
            String answer = modelScopeAI.doOpenRouter(topic.getTalk().getText());

            // 3. 问题回复
            boolean response = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer);
            logger.info("编号:{};问题:{};回答:{};状态:{}",topic.getTopic_id(),topic.getTalk().getText(),answer,response);

        } catch (Exception e) {
            logger.error("自动回答问题异常",e);
        }
    }
}
