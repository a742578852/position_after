package generator;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * system_admin
 * @author 
 */
@Data
public class SystemAdmin implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIp;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 是否启用 0禁用 1启用
     */
    private Integer status;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}