package cn.lingxiaolu.chatbot.api.domain.zsxq.model.vo;

/**
 * @author 绫小路
 * @description
 * @github https://github.com/lingxiaolu-glitch
 **/
public class Talk {
    private Owner owner;

    private String text;

    public void setOwner(Owner owner){
        this.owner = owner;
    }
    public Owner getOwner(){
        return this.owner;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
}
