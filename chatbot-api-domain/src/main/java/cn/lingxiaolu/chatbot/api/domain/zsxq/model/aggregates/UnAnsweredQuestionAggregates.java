package cn.lingxiaolu.chatbot.api.domain.zsxq.model.aggregates;

import cn.lingxiaolu.chatbot.api.domain.zsxq.model.res.RespData;

/**
 * @author 绫小路
 * @description 未回答问题的聚合信息
 * @github https://github.com/lingxiaolu-glitch
 **/
public class UnAnsweredQuestionAggregates {
    private boolean succeeded;
    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }
}
