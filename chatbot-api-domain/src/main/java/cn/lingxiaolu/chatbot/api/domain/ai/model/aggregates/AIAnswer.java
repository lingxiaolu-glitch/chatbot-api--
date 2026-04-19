package cn.lingxiaolu.chatbot.api.domain.ai.model.aggregates;

import cn.lingxiaolu.chatbot.api.domain.ai.model.vo.Output;
import cn.lingxiaolu.chatbot.api.domain.ai.model.vo.Usage;

import java.util.List;

/**
 * @author 绫小路
 * @description AI响应聚合根
 * @github https://github.com/lingxiaolu-glitch
 **/
public class AIAnswer {
    private long created_at;

    private String id;

    private String model;

    private String object;

    private List<Output> output;

    private boolean parallel_tool_calls;

    private String status;

    private String tool_choice;

    private List<Object> tools;

    private Usage usage;

    public void setCreated_at(long created_at){
        this.created_at = created_at;
    }
    public long getCreated_at(){
        return this.created_at;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getModel(){
        return this.model;
    }
    public void setObject(String object){
        this.object = object;
    }
    public String getObject(){
        return this.object;
    }
    public void setOutput(List<Output> output){
        this.output = output;
    }
    public List<Output> getOutput(){
        return this.output;
    }
    public boolean isParallel_tool_calls(){
        return this.parallel_tool_calls;
    }
    public void setParallel_tool_calls(boolean parallel_tool_calls){
        this.parallel_tool_calls = parallel_tool_calls;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setTool_choice(String tool_choice){
        this.tool_choice = tool_choice;
    }
    public String getTool_choice(){
        return this.tool_choice;
    }
    public void setTools(List<Object> tools){
        this.tools = tools;
    }
    public List<Object> getTools(){
        return this.tools;
    }
    public void setUsage(Usage usage){
        this.usage = usage;
    }
    public Usage getUsage(){
        return this.usage;
    }
}
