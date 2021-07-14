package com.justiceLeague.netty;

import io.netty.channel.ChannelHandlerContext;

public class DataHandle {

    /**
     * 数据解析
     */
    public void analysis(String data,ChannelHandlerContext ctx){
        //获取命令
        String command = data.substring(18,20);
        //网关mac
        String gatewayMac = data.substring(6,18);
        //包序号
        String dataSerial = data.substring(4,6);
        switch (command){
            //心跳包
            case "01":
                String answer = "DDDD"+dataSerial+gatewayMac+"81"+"000955";
                ctx.writeAndFlush(answer);
                break;
        }

    }


}
