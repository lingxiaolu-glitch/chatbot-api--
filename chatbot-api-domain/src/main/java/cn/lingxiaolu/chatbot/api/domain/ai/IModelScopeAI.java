package cn.lingxiaolu.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @author 绫小路
 * @description ModelScope ai 接口 https://www.modelscope.cn/docs/model-service/API-Inference/intro
 * @github https://github.com/lingxiaolu-glitch
 **/
public interface IModelScopeAI {

    String doModelScope(String question) throws IOException;
}
