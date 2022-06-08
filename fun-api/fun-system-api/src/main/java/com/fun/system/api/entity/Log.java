package com.fun.system.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Log)表实体类
 *
 * @author whywhathow
 * @since 2022-06-07 07:03:47
 */
@EqualsAndHashCode
@Data
@TableName("sys_log")
@Schema(description = "Log实体类")
@SuppressWarnings("serial")
public class Log extends BaseEntity {

    /**
     * log_id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "log_id")
    private Long id;
    /**
     * log日志类型, 0表示正常日志, 1 表示错误日志
     */
    @Schema(description = "log日志类型, 0表示正常日志, 1 表示错误日志")
    private String type;
    /**
     * 日志标题
     */
    @Schema(description = "日志标题")
    private String title;
    /**
     * 微服务id,名称
     */
    @Schema(description = "微服务id,名称")
    private String serviceId;
    /**
     * 操作ip
     */
    @Schema(description = "操作ip")
    private String remoteAddr;
    /**
     * 客户端代理
     */
    @Schema(description = "客户端代理")
    private String userAgent;
    /**
     * 请求uri
     */
    @Schema(description = "请求uri")
    private String requestUri;
    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    private String method;
    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String params;
    /**
     * 异常信息
     */
    @Schema(description = "异常信息")
    private String exception;
    /**
     * 执行时间
     */
    @Schema(description = "执行时间")
    private Long execTime;
}
