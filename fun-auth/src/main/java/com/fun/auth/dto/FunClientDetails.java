package com.fun.auth.dto;

import com.fun.system.api.entity.OauthClientDetails;

/**
 * learn: 非必要,不需要继承接口(自己的接口可能内容较少,服务范围低), 继承基类,或者抽象类应该会是最优选.
 * @program: fun-project
 * @description: 弃用, 原因, 不方便对clientDetails进行构造, more important, oauth2 sql 解决集映射 所做的对象处理 优于自己diy的.
 * @author: WhyWhatHow
 * @create: 2022-05-24 16:48
 **/
@Deprecated
public class FunClientDetails extends OauthClientDetails {

}
