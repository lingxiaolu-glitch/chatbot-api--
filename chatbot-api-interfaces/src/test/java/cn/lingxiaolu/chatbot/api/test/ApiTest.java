package cn.lingxiaolu.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author 绫小路
 * @description 单元测试
 * @github https://github.com/lingxiaolu-glitch
 **/
public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=1");
        httpGet.addHeader("cookie","zsxq_access_token=EFFD7A8C-8CD8-4A69-8000-933104A13EEF_065D2A877510D2E5; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22814885515558842%22%2C%22first_id%22%3A%2219d811f78fad3-06852982006be0c-26061f51-1350728-19d811f78fb85d%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTlkODExZjc4ZmFkMy0wNjg1Mjk4MjAwNmJlMGMtMjYwNjFmNTEtMTM1MDcyOC0xOWQ4MTFmNzhmYjg1ZCIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjgxNDg4NTUxNTU1ODg0MiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22814885515558842%22%7D%7D");
        httpGet.addHeader("Content-Type","application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(response);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/14588112552452582/comments");
        httpPost.addHeader("cookie","zsxq_access_token=EFFD7A8C-8CD8-4A69-8000-933104A13EEF_065D2A877510D2E5; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22814885515558842%22%2C%22first_id%22%3A%2219d811f78fad3-06852982006be0c-26061f51-1350728-19d811f78fb85d%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTlkODExZjc4ZmFkMy0wNjg1Mjk4MjAwNmJlMGMtMjYwNjFmNTEtMTM1MDcyOC0xOWQ4MTFmNzhmYjg1ZCIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjgxNDg4NTUxNTU1ODg0MiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22814885515558842%22%7D%7D");
        httpPost.addHeader("Content-Type","application/json;charset=utf8");

        String paramJson = "{\n" +
                "    \"req_data\": {\n" +
                "        \"text\": \"我也不会\\n\",\n" +
                "        \"image_ids\": [],\n" +
                "        \"mentioned_user_ids\": []\n" +
                "    }\n" +
                "}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_modelScope() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://api-inference.modelscope.cn/v1/responses");
        httpPost.setHeader("content-type","application/json");
        httpPost.setHeader("Authorization","Bearer ms-34750a15-3966-4ba6-b840-85cfe3ea7449");

        String paramJson = "{\n" +
                "    \"model\": \"Qwen/Qwen3.5-27B\",\n" +
                "    \"input\": \"用java程序写一个冒泡排序算法\"\n" +
                "  }";

        StringEntity stringEntity = new StringEntity(paramJson,ContentType.create("text/json","UTF-8"));
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(res);
        } else {
            String res = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println("服务器返回错误代码：{},错误信息：{}" + response.getStatusLine().getStatusCode() + res);
        }
    }

    @Test
    public void test_openRouter() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://openrouter.ai/api/v1/responses");
        httpPost.setHeader("content-type","application/json");
        httpPost.setHeader("Authorization","Bearer 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");

        String paramJson = "{\n" +
                "    \"model\": \"nvidia/nemotron-3-super-120b-a12b:free\",\n" +
                "    \"input\": \"用java程序写一个冒泡排序算法\"\n" +
                "  }";

        StringEntity stringEntity = new StringEntity(paramJson,ContentType.create("text/json","UTF-8"));
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(res);
        } else {
            String res = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println("服务器返回错误代码：{},错误信息：{}" + response.getStatusLine().getStatusCode() + res);
        }
    }
}
