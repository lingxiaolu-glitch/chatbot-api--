package cn.lingxiaolu.chatbot.api.domain.ai.model.vo;

import java.util.List;

/**
 * @author 绫小路
 * @description AI响应输出项（包含reasoning和message两种类型）
 * @github https://github.com/lingxiaolu-glitch
 **/
public class Output {
    private String id;

    private List<Summary> summary;

    private String type;

    private List<Content> content;

    private String role;

    private String status;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setSummary(List<Summary> summary){
        this.summary = summary;
    }
    public List<Summary> getSummary(){
        return this.summary;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setContent(List<Content> content){
        this.content = content;
    }
    public List<Content> getContent(){
        return this.content;
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
}
