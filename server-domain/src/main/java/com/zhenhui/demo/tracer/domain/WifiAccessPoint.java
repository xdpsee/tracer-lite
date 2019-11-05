package com.zhenhui.demo.tracer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class WifiAccessPoint implements Serializable {

    private static final long serialVersionUID = 436184760096180703L;

    private String macAddress;

    private Integer signalStrength;

    private Integer channel;

    public static WifiAccessPoint from(String macAddress, int signalStrength) {
        WifiAccessPoint wifiAccessPoint = new WifiAccessPoint();
        wifiAccessPoint.setMacAddress(macAddress);
        wifiAccessPoint.setSignalStrength(signalStrength);
        return wifiAccessPoint;
    }

    public static WifiAccessPoint from(String macAddress, int signalStrength, int channel) {
        WifiAccessPoint wifiAccessPoint = from(macAddress, signalStrength);
        wifiAccessPoint.setChannel(channel);
        return wifiAccessPoint;
    }
}
