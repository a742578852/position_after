package com.justiceLeague.service;

import com.justiceLeague.model.SystemAdmin;
import com.justiceLeague.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="location-manage")
public interface SystemService {
    @GetMapping("api/v1/system/findById")
    Result findById(@RequestParam("id") long id);
}
