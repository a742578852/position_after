package com.justiceLeague.netty;

import com.justiceLeague.config.DicConfig;
import com.justiceLeague.model.*;
import com.justiceLeague.service.*;
import com.justiceLeague.util.CheckSum;
import com.justiceLeague.util.LocationAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@ChannelHandler.Sharable
@Component
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    EquipmentGateWayService equipmentGateWayService;
    @Autowired
    EquipmentLabelCardService equipmentLabelCardService;
    @Autowired
    EquipmentBeaconService equipmentBeaconService;
    @Autowired
    GatewayReportRecordService gatewayReportRecordService;
    @Autowired
    UserService userService;
    @Autowired
    LocationLogService locationLogService;

    /**
     * 解决无法自动注入问题
     */
    private static ServerHandler serverHandler;
    @PostConstruct
    public void init() {
        serverHandler = this;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收到的数据解码成16进制
        ByteBuf data = (ByteBuf) msg;
        byte[] receiveMsgBytes = new byte[data.readableBytes()];
        data.readBytes(receiveMsgBytes);
        System.out.println("服务器接收到数据" + Hex.encodeHexString(receiveMsgBytes));

        String dataMsg = Hex.encodeHexString(receiveMsgBytes);
        //获取命令
        String command = dataMsg.substring(18, 20);
        //网关mac
        String gatewayMac = dataMsg.substring(6, 18);
        //包序号
        String dataSerial = dataMsg.substring(4, 6);

        int type = 0;

        String typeInfo = "";

        //校验和判断数据是否合规
        String sumStr = dataMsg.substring(0, dataMsg.length() - 6);

        String sum = CheckSum.makeSum(sumStr);

        if (dataMsg.substring(dataMsg.length() - 6, dataMsg.length() - 4).equals(sum.substring(sum.length() - 2))) {
            switch (command) {
                //心跳包
                case "01":
                    //校验和
                    String addSum = CheckSum.makeSum(("DDDD" + dataSerial + gatewayMac + "8100"));
                    //心跳应答
                    String answer = "DDDD" + dataSerial + gatewayMac + "8100" + addSum + "5555";

                    //netty需要用ByteBuf传输
                    ByteBuf bufff = Unpooled.buffer();
                    //对接需要16进制
                    bufff.writeBytes(CheckSum.getHexBytes(answer));
                    ctx.writeAndFlush(bufff);
                    type = 1;
                    typeInfo = "心跳包";

                    break;
                //数据采集信息
                case "02":
                    Map locationMap = new HashMap();
                    type = 2;
                    typeInfo = "位置采集信息";
                    //标签卡信息
                    EquipmentLabelCard equipmentLabelCard;
                    //位置信息
                    LocationLog locationLog = new LocationLog();

                    //信标集合列表
                    List<EquipmentBeacon> beaconList = new ArrayList<EquipmentBeacon>();

                    //解析坐标
                    //获取定位卡信息
                    //获取有效数据长度
                    String effectiveDataNumberStr = dataMsg.substring(20, 22);
                    //转换为10进制
                    Integer effctiveDataNumber = Integer.parseInt(effectiveDataNumberStr, 16);
                    if (0 == effctiveDataNumber) {
                        return;
                    }
                    //读取有效数据
                    String effectiveData = dataMsg.substring(22, (effctiveDataNumber * 2) + 23);

                    String labelCard = effectiveData.substring(0, 12);
                    //查询出定位卡的详细信息
                    equipmentLabelCard = serverHandler.equipmentLabelCardService.getLabekCard(labelCard);

                    //修改定位卡电量
                    Integer ele = Integer.parseInt(effectiveData.substring(12, 14), 16);
                    serverHandler.equipmentLabelCardService.updateEle(effectiveData.substring(0, 12), ele);

                    //没有信标
                    if (effctiveDataNumber == 7) {
                        return;
                        //一个信标
                    } else if (effctiveDataNumber == 15) {
                        //获取第一个mac
                        String mac1 = effectiveData.substring(15, 27);
                        //获取第一个信标电量
                        double ele1 = Integer.parseInt(effectiveData.substring(27, 29), 16);
                        //获取第一个信标rssi
                        int rssi1 = Integer.parseInt(effectiveData.substring(29, 31), 16);
                        //待优化
                        //查询当前信标的详细信息
                        EquipmentBeacon equipmentBeacon1 = serverHandler.equipmentBeaconService.getBeacon(mac1);
                        equipmentBeacon1.setElectric(ele1);
                        equipmentBeacon1.setRssi(rssi1);
                        beaconList.add(equipmentBeacon1);
                        //

                        //两个信标
                    } else if (effctiveDataNumber == 23) {
                        //获取第一个mac
                        String mac1 = effectiveData.substring(15, 27);
                        //获取第一个信标电量
                        double ele1 = Integer.parseInt(effectiveData.substring(27, 29), 16);
                        //获取第一个信标rssi
                        int rssi1 = Integer.parseInt(effectiveData.substring(29, 31), 16);
                        //待优化
                        //查询当前信标的详细信息
                        EquipmentBeacon equipmentBeacon1 = serverHandler.equipmentBeaconService.getBeacon(mac1);
                        equipmentBeacon1.setElectric(ele1);
                        equipmentBeacon1.setRssi(rssi1);

                        //获取第二个信标
                        //获取第二个mac
                        String mac2 = effectiveData.substring(31, 43);
                        //获取第二个电量
                        double ele2 = Integer.parseInt(effectiveData.substring(43, 45), 16);
                        //获取第二个信号强度
                        int rssi2 = Integer.parseInt(effectiveData.substring(45, 47), 16);
                        EquipmentBeacon equipmentBeacon2 = serverHandler.equipmentBeaconService.getBeacon(mac2);
                        equipmentBeacon2.setElectric(ele2);
                        equipmentBeacon2.setRssi(rssi2);
                        beaconList.add(equipmentBeacon1);
                        beaconList.add(equipmentBeacon2);

                        //三个信标
                    } else if (effctiveDataNumber == 31) {

                        //获取第一个mac
                        String mac1 = effectiveData.substring(14, 26);
                        //获取第一个信标电量
                        double ele1 = Integer.parseInt(effectiveData.substring(26, 28), 16);
                        //获取第一个信标rssi
                        int rssi1 = Integer.parseInt(effectiveData.substring(28, 30), 16);
                        //待优化
                        //查询当前信标的详细信息
                        EquipmentBeacon equipmentBeacon1 = serverHandler.equipmentBeaconService.getBeacon(mac1);
                        equipmentBeacon1.setElectric(ele1);
                        equipmentBeacon1.setRssi(rssi1);

                        //获取第二个信标
                        //获取第二个mac
                        String mac2 = effectiveData.substring(30, 42);
                        //获取第二个电量
                        double ele2 = Integer.parseInt(effectiveData.substring(42, 44), 16);
                        //获取第二个信号强度
                        int rssi2 = Integer.parseInt(effectiveData.substring(44, 46), 16);
                        EquipmentBeacon equipmentBeacon2 = serverHandler.equipmentBeaconService.getBeacon(mac2);
                        equipmentBeacon2.setElectric(ele2);
                        equipmentBeacon2.setRssi(rssi2);

                        //获取第三个信标
                        String mac3 = effectiveData.substring(46, 58);
                        //获取第三个电量
                        double ele3 = Double.parseDouble(effectiveData.substring(58, 60));
                        //获取第三个信号强度
                        int rssi3 = Integer.parseInt(effectiveData.substring(60, 62), 16);
                        EquipmentBeacon equipmentBeacon3 = serverHandler.equipmentBeaconService.getBeacon(mac3);
                        equipmentBeacon3.setElectric(ele3);
                        equipmentBeacon3.setRssi(rssi3);

                        beaconList.add(equipmentBeacon1);
                        beaconList.add(equipmentBeacon2);
                        beaconList.add(equipmentBeacon3);

                    }

                    //算法求坐标
                    locationMap = LocationAlgorithm.evaluateCoordinates(beaconList);
                    locationLog.setX((Double) locationMap.get("x"));
                    locationLog.setY((Double) locationMap.get("y"));
                    locationLog.setFloor((Integer) locationMap.get("floor"));
                    //根据定位卡mac查询人员详情
                    User user = serverHandler.userService.getUser(equipmentLabelCard.getMac());
                    locationLog.setUserId(user.getId());
                    locationLog.setUserName(user.getName());
                    locationLog.setMac(equipmentLabelCard.getMac());
                    locationLog.setCreateTime(new Date());
                    //持久化
                    serverHandler.locationLogService.addLocationLog(locationLog);
                    break;

                //网关上电
                case "03":

                    type = 3;
                    typeInfo = "网关上电";
                    //修改数据库基站状态 写一个定时查询网关是否在线
                    serverHandler.equipmentGateWayService.updateOnline(DicConfig.ONLINE, gatewayMac);
                    break;

                //人员求救报警
                case "04":

                    type = 4;
                    typeInfo = "人员求救报警";
                    break;

                //取消报警
                case "05":

                    type = 5;
                    typeInfo = "人员主动取消报警";
                    break;

                //睡岗报警
                case "06":

                    type = 6;
                    typeInfo = "睡岗报警";
                    break;
            }

            GateWayReportRecord gateWayReportRecord = new GateWayReportRecord();
            gateWayReportRecord.setMac(gatewayMac);
            gateWayReportRecord.setData(dataMsg);
            gateWayReportRecord.setDate(new Date());
            gateWayReportRecord.setType(type);
            gateWayReportRecord.setTypeInfo(typeInfo);
            //将数据保存
            serverHandler.gatewayReportRecordService.insertReportRecord(gateWayReportRecord);
        } else {
            System.out.println("数据错误");
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("EchoServerHandle channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }


}
