package cn.lingxiaolu.chatbot.api.domain.zsxq.model.req;

import java.util.List;

/**
 * @author 绫小路
 * @description
 * @github https://github.com/lingxiaolu-glitch
 **/
public class ReqData {
    private String text;

    private List<String> image_ids;

    private List<String> mentioned_user_ids;

    public ReqData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(List<String> image_ids) {
        this.image_ids = image_ids;
    }

    public List<String> getMentioned_user_ids() {
        return mentioned_user_ids;
    }

    public void setMentioned_user_ids(List<String> mentioned_user_ids) {
        this.mentioned_user_ids = mentioned_user_ids;
    }
}
