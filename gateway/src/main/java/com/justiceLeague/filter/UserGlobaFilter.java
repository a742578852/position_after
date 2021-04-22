package com.justiceLeague.filter;

import com.justiceLeague.util.JwtUtils;
import com.justiceLeague.util.Result;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserGlobaFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //判断当前的请求是否为登录，如果是，直接放行
        if(exchange.getRequest().getURI().getPath().contains("/api/system/goLogin")){
            //放行
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (token == null){
            token  = exchange.getRequest().getQueryParams().getFirst("token");
        }
        if(token != null){
            Claims claims = JwtUtils.checkJWT(token);
            if (claims != null) {
                int id = (Integer) claims.get("id");

                // TODO 将用户信息存放在请求header中传递给下游业务
                ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
                mutate.header("id", String.valueOf(id));
                ServerHttpRequest buildReuqest = mutate.build();

                return chain.filter(exchange);
            }
        }

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
