package com.zhenhui.demo.tracer.storage.api.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Network implements Serializable {

    private static final long serialVersionUID = 6463361247973962010L;

    public Network(CellTower cellTower) {
        cellTowers.add(cellTower);
    }

    // 属性
    private Integer mobileCountryCode;

    private Integer mobileNetworkCode;

    private String radioType = "gsm";

    private String carrier;

    private Boolean considerIp = false;

    private List<CellTower> cellTowers = new ArrayList<>();

    private List<WifiAccessPoint> wifiAccessPoints = new ArrayList<>();
}
