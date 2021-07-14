package com.justiceLeague.controller;

//import com.justiceLeague.listener.LocationMQListener;
import com.justiceLeague.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
public class Test {
//    LocationMQListener locationMQListener;

    @RequestMapping("getLocation")
    public Result getLocation(){

        Map map = new HashMap();

        map.put("mac","121212");
        map.put("name","冯荣宇");
        map.put("x",Math.random()*100);
        map.put("z",Math.random()*100);
        map.put("height",1);

        Map map1 = new HashMap();
        map1.put("mac","3435432");
        map1.put("name","马溪");
        map1.put("x",Math.random()*100);
        map1.put("z",Math.random()*100);
        map1.put("height",2);

        List list = new ArrayList();
        list.add(map);
        list.add(map1);
        return new Result(200,true,list);


    }
}
