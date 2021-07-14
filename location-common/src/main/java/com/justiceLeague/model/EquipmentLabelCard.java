package com.justiceLeague.model;

import lombok.Data;

@Data
public class EquipmentLabelCard {

    private Long id;

    private String mac;

    /**
     * 是否绑定人员 0未绑定 1已绑定
     */
    private int ifBind;

    private String userName;

    private Long userId;

    /**
     * 电量
     */
    private double electric;


}
