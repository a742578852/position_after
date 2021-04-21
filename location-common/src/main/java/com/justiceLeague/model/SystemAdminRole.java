package com.justiceLeague.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * system_admin_role
 * @author 
 */
@Data
public class SystemAdminRole implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账号id
     */
    private Long adminId;

    /**
     * 权限id
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}