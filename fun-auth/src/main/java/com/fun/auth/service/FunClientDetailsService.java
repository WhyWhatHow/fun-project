package com.fun.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-05-24 17:01
 **/
@Slf4j
public class FunClientDetailsService extends JdbcClientDetailsService {

    final static String DEFAULT_TABLE_NAME = "oauth_client_details";

    private static final String CLIENT_FIELDS_FOR_UPDATE = "resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    private static final String CLIENT_FIELDS = "client_secret, " + CLIENT_FIELDS_FOR_UPDATE;

    private static final String BASE_FIND_STATEMENT = "select client_id, " + CLIENT_FIELDS
            + " from ";
    /**
     * oauth_client_details 表名
     */
    private String selectSql;
    String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.selectSql =BASE_FIND_STATEMENT+tableName + " where client_id = ?";;
        super.setSelectClientDetailsSql(this.selectSql);
    }

    /**
     * @param dataSource 数据源
     * @param tableName  oauth_client_details 表名
     */
    public FunClientDetailsService(DataSource dataSource, String tableName) {
        super(dataSource);
        this.setTableName(tableName);
    }

    /**
     * 默认构造函数 ,
     * oauth_client_details 为默认表名
     *
     * @param dataSource 数据源
     */
    public FunClientDetailsService(DataSource dataSource) {
        super(dataSource);
        this.tableName = tableName;
        this.selectSql = BASE_FIND_STATEMENT + DEFAULT_TABLE_NAME;
        super.setSelectClientDetailsSql(this.selectSql);
    }

    /**
     * OPTIMIZE [whywhathow] [2022/3/15]  [opt]  自己实现 数据库查询 -> 结果转换,
     *   添加缓存, 避免client 结果重复
     *  cache [must] : oauth/token 过程中会频繁调用 此方法
     *  [warn]
     * <p>
     * 通过clientID 获取client 信息
     * @param clientId The client id.
     * @return clientDetails
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.warn("[select oauth_client_details info ]: ==>{}", this.selectSql);
        return super.loadClientByClientId(clientId);
    }

}
