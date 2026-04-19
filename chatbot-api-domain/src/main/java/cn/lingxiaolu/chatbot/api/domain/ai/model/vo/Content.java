package cn.lingxiaolu.chatbot.api.domain.ai.model.vo;

import java.util.List;

/**
 * @author 绫小路
 * @description
 * @github https://github.com/lingxiaolu-glitch
 **/
public class Content {
    private List<String> annotations;

    private String text;

    private String type;

    private List<Object> logprobs;

    public void setAnnotations(List<String> annotations){
        this.annotations = annotations;
    }
    public List<String> getAnnotations(){
        return this.annotations;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setLogprobs(List<Object> logprobs){
        this.logprobs = logprobs;
    }
    public List<Object> getLogprobs(){
        return this.logprobs;
    }
}
