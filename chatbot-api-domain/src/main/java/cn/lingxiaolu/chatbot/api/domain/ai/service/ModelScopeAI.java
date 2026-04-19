package cn.lingxiaolu.chatbot.api.domain.ai.service;

import cn.lingxiaolu.chatbot.api.domain.ai.IModelScopeAI;
import cn.lingxiaolu.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
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

/**
 * @author 绫小路
 * @description
 * @github https://github.com/lingxiaolu-glitch
 **/
@Service
public class ModelScopeAI implements IModelScopeAI {

    private Logger logger = LoggerFactory.getLogger(IModelScopeAI.class);

    private static  final int  CONSTENT_0 = 0;
    private static  final int  CONSTENT_1 = 1;

    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Override
    public String doModelScope(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://api-inference.modelscope.cn/v1/responses");
        httpPost.setHeader("content-type","application/json");
        httpPost.setHeader("Authorization","Bearer " + openAiKey);

        String paramJson = "{\n" +
                "    \"model\": \"Qwen/Qwen3.5-27B\",\n" +
                "    \"input\": \""+ question + "\"\n" +
                "  }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity(),"UTF-8");
            AIAnswer aiAnswer = JSONObject.parseObject(jsonStr, AIAnswer.class);
            return aiAnswer.getOutput().get(CONSTENT_1).getContent().get(CONSTENT_0).getText();

        } else {
            throw new RuntimeException("www.modelscope.cn Err Code is" + response.getStatusLine().getStatusCode());
        }
    }
}
