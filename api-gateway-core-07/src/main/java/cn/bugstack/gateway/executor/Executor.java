package cn.bugstack.gateway.executor;

import cn.bugstack.gateway.executor.result.GatewayResult;
import cn.bugstack.gateway.mapping.HttpStatement;

import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description 执行器
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface Executor {

    GatewayResult exec(HttpStatement httpStatement, Map<String, Object> params) throws Exception;

}