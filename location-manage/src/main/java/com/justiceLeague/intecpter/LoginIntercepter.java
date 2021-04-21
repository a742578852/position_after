package com.justiceLeague.intecpter;

import com.google.gson.Gson;

import com.justiceLeague.service.SystemAdminService;
import com.justiceLeague.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginIntercepter implements HandlerInterceptor {

    private static final Gson gson = new Gson();
    @Autowired
    SystemAdminService systemAdminService;


    //进入方法前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map map = new HashMap(4);
        String token = request.getHeader("token");
        if (token == null){
            token  = request.getParameter("token");
        }
        if(token != null){
            Claims claims = JwtUtils.checkJWT(token);
            if (claims != null) {
                int id = (Integer) claims.get("id");
                request.setAttribute("id",id);
                return true;
            }
        }
        map.put("code",201);
        map.put("message","请登录");
        sendMessage(response,map);
        return false;
    }

    /*
    数据返回
     */
    public static void sendMessage(HttpServletResponse response, Object object)throws Exception{
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(gson.toJson(object));
        writer.close();
        response.flushBuffer();

    }


    //进入方法后，视图渲染前

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    //所有完成之后

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
