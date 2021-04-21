package com.justiceLeague.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * system_role
 * @author 
 */
@Data
public class SystemRole implements Serializable {
    private Long id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 备注
     */
    private String comments;

    /**
     * 状态 1启用 2禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}