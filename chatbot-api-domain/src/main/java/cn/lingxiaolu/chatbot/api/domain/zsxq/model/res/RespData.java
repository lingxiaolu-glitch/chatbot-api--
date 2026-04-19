package cn.lingxiaolu.chatbot.api.domain.zsxq.model.res;

import cn.lingxiaolu.chatbot.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @author 绫小路
 * @github https://github.com/lingxiaolu-glitch
 **/
public class RespData {
    private List<Topics> topics;

    public RespData(List<Topics> topics) {
        this.topics = topics;
    }

    public void setTopics(List<Topics> topics){
        this.topics = topics;
    }
    public List<Topics> getTopics(){
        return this.topics;
    }
}
