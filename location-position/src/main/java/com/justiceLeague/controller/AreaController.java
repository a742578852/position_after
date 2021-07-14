//package com.justiceLeague.controller;
//
//import com.justiceLeague.service.SystemService;
//import com.justiceLeague.util.GetCoordinate;
//import com.justiceLeague.util.Result;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("api/v1/role")
//public class AreaController {
//    @Autowired
//    SystemService systemService;
//
//    GetCoordinate coordinate = new GetCoordinate();
//
//    @GetMapping("findById")
//    public Result findById(long id){
//
//        double ju = coordinate.getDistanceByRiss(-20);
//        System.out.println(ju);
//        return new Result(200,true,"获取成功",systemService.findById(id).getData());
//    }
//}
