package com.justiceLeague.controller;

import com.justiceLeague.service.SystemAdminService;
import com.justiceLeague.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统人员,权限信息
 */
@RestController
@RequestMapping("api/system")
public class SystemController {
    @Autowired
    SystemAdminService systemAdminService;

    /**
     * 用户登录
     * @param loginName
     * @param passWord
     * @return
     */
    @GetMapping("golLogin")
    public Result golLogin(String loginName,String passWord){



        return null;
    }


    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Result findById(long id){

        return new Result(200,true,"获取成功","12121");
    }



    /**
     * 添加普通绑定账号
     * @return
     */
    @PostMapping("addAccount")
    public Result addAccount(Long personId,String loginName,String passWord){

        return systemAdminService.addAccount(personId,loginName,passWord);
    }


}
