package cn.bugstack.gateway.session;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.mapping.HttpStatement;

import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description 用户处理网关 HTTP 请求
 * @github github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface GatewaySession {

    Object get(String methodName, Map<String, Object> params);

    Object post(String methodName, Map<String, Object> params);

    IGenericReference getMapper();

    Configuration getConfiguration();
}