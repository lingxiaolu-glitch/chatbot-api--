package cn.lingxiaolu.chatbot.api.domain.ai.model.vo;

/**
 * @author 绫小路
 * @description
 * @github https://github.com/lingxiaolu-glitch
 **/
public class Summary {
    private String text;

    private String type;

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
}
