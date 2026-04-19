package cn.lingxiaolu.chatbot.api.domain.zsxq.service;

import cn.lingxiaolu.chatbot.api.domain.zsxq.IZsxqApi;
import cn.lingxiaolu.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import cn.lingxiaolu.chatbot.api.domain.zsxq.model.req.AnswerReq;
import cn.lingxiaolu.chatbot.api.domain.zsxq.model.req.ReqData;
import cn.lingxiaolu.chatbot.api.domain.zsxq.model.res.AnswerRes;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author 绫小路
 * @description
 * @github https://github.com/lingxiaolu-glitch
 **/
@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);


    @Override
    public UnAnsweredQuestionAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=all&count=1");
        httpGet.addHeader("cookie", cookie);
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(response);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("拉取提问数据。groupId：{} jsonStr：{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Err Code is" + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/comments");
        httpPost.addHeader("cookie", cookie);
        httpPost.addHeader("Content-Type", "application/json;charset=utf8");
        httpPost.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.0.0 Safari/537.36");


        /*测试数据
        String paramJson = "{\n" +
                "    \"req_data\": {\n" +
                "        \"text\": \"我也不会\\n\",\n" +
                "        \"image_ids\": [],\n" +
                "        \"mentioned_user_ids\": []\n" +
                "    }\n" +
                "}";
         */

        AnswerReq answerReq = new AnswerReq(new ReqData(text));
        String paramJson = JSONObject.toJSONString(answerReq);
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer Err Code is" +response.getStatusLine().getStatusCode());
        }
    }
}
