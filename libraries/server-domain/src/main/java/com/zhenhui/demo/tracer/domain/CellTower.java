package com.zhenhui.demo.tracer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CellTower implements Serializable {

    private static final long serialVersionUID = 1758268454928348674L;

    private String radioType;

    private Long cellId;

    private Integer locationAreaCode;

    private Integer mobileCountryCode;

    private Integer mobileNetworkCode;

    private Integer signalStrength;

    public static CellTower from(int mcc, int mnc, int lac, long cid) {
        CellTower cellTower = new CellTower();
        cellTower.setMobileCountryCode(mcc);
        cellTower.setMobileNetworkCode(mnc);
        cellTower.setLocationAreaCode(lac);
        cellTower.setCellId(cid);
        return cellTower;
    }

    public static CellTower from(int mcc, int mnc, int lac, long cid, int rssi) {
        CellTower cellTower = CellTower.from(mcc, mnc, lac, cid);
        cellTower.setSignalStrength(rssi);
        return cellTower;
    }
}

