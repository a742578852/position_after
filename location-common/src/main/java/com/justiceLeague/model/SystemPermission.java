package com.justiceLeague.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * system_permission
 * @author 
 */
@Data
public class SystemPermission implements Serializable {
    private Long id;

    private Long modeId;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String authorityName;

    /**
     * 权限标识
     */
    private String authority;

    /**
     * 路由地址
     */
    private String routeName;

    /**
     *  图标地址
     */
    private String icon;

    /**
     * 权限类型
     */
    private Byte authorityType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否是系统菜单 1是 2不是
     */
    private Integer ifSystem;

    /**
     * 状态 1启用 2禁用
     */
    private Integer status;

    /**
     * 展开
     */
    private Integer extended;

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