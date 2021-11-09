package com.justiceLeague.controller;

import com.justiceLeague.model.LocationLog;
import com.justiceLeague.service.LocationLogService;
import com.justiceLeague.service.SystemService;
import com.justiceLeague.util.GetCoordinate;
import com.justiceLeague.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {
    @Autowired
    SystemService systemService;
    @Autowired
    LocationLogService locationLogService;

    GetCoordinate coordinate = new GetCoordinate();

    @GetMapping("realTimeLocation")
    public Result location() {
        // 获取当前5分钟内的所有人员位置
        List<LocationLog> locationLogs = locationLogService.getLocationByFive();
        //去重 留时间最近的
        locationLogs = locationLogs.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(LocationLog :: getMac))), ArrayList::new));



        return new Result(200, true, "获取成功", locationLogs);
    }
}
