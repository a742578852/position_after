package com.justiceLeague.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * system_operation_log
 * @author 
 */
@Data
public class SystemOperationLog implements Serializable {
    private Long id;

    /**
     * 用户账户名
     */
    private String loginName;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 操作描述
     */
    private String operationContent;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}