package cn.bugstack.gateway.session.defaults;

import cn.bugstack.gateway.bind.IGenericReference;
import cn.bugstack.gateway.datasource.Connection;
import cn.bugstack.gateway.datasource.DataSource;
import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;
import cn.bugstack.gateway.session.GatewaySession;
import cn.bugstack.gateway.type.SimpleTypeRegistry;

import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description 默认网关会话实现类
 * @github github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DefaultGatewaySession implements GatewaySession {

    private Configuration configuration;
    private String uri;
    private DataSource dataSource;

    public DefaultGatewaySession(Configuration configuration, String uri, DataSource dataSource) {
        this.configuration = configuration;
        this.uri = uri;
        this.dataSource = dataSource;
    }

    @Override
    public Object get(String methodName, Map<String, Object> params) {
        Connection connection = dataSource.getConnection();
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        String parameterType = httpStatement.getParameterType();

        /*
         * 调用服务
         * 封装参数 PS：为什么这样构建参数，可以参考测试案例；cn.bugstack.gateway.test.RPCTest
         * 01(允许)：java.lang.String
         * 02(允许)：cn.bugstack.gateway.rpc.dto.XReq
         * 03(拒绝)：java.lang.String, cn.bugstack.gateway.rpc.dto.XReq —— 不提供多参数方法的处理
         * */
        return connection.execute(methodName,
                new String[]{parameterType},
                new String[]{"ignore"},
                SimpleTypeRegistry.isSimpleType(parameterType) ? params.values().toArray() : new Object[]{params});
    }

    @Override
    public Object post(String methodName, Map<String, Object> params) {
        return get(methodName, params);
    }

    @Override
    public IGenericReference getMapper() {
        return configuration.getMapper(uri, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}
