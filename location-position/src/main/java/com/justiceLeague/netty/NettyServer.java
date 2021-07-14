package com.justiceLeague.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class NettyServer {

    /**
     * 用于处理连接的线程组
     */
    private EventLoopGroup boss = new NioEventLoopGroup();

    /**
     * 用于数据处理的线程组
     */
    private EventLoopGroup worker = new NioEventLoopGroup();

    @Value("${netty.port}")
    private Integer port;

    /**
     * 启动nettyServer
     *
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                //指定channel
                .channel(NioServerSocketChannel.class)
                //使用指定的端口 设置套接字地址
                .localAddress(new InetSocketAddress(port))
                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                //设置TCP长连接，一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //将小的数据包包装成更大的帧进行传输,提高网络负载,
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ServerHandler());

        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            System.out.println("启动 nettyServer");

        }

    }

    @PreDestroy
    public void destory() throws InterruptedException {
        boss.shutdownGracefully().sync();
        worker.shutdownGracefully().sync();
        System.out.println("关闭 nettyServer");


    }
}
