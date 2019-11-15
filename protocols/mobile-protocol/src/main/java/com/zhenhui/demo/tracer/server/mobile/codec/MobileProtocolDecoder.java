package com.zhenhui.demo.tracer.server.mobile.codec;

import com.zhenhui.demo.tracer.domain.DeviceID;
import com.zhenhui.demo.tracer.domain.Location;
import com.zhenhui.demo.tracer.domain.server.Message;
import com.zhenhui.demo.tracer.server.mobile.message.RegistryMessage;
import com.zhenhui.demo.tracer.server.support.codec.AbstractProtocolDecoder;
import com.zhenhui.demo.tracer.server.support.exception.MessageException;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MobileProtocolDecoder extends AbstractProtocolDecoder<String, Message> {

    @Override
    public List<Message> decode(ChannelHandlerContext ctx, String message) throws Exception {

        try {
            if (message.startsWith("##1,")) {
                return Collections.singletonList(decodeRegistry(message));
            }

            if (message.startsWith("##2,")) {
                return Collections.singletonList(decodeLocation(message));
            }

            return new ArrayList<>();
        } catch (Throwable e) {
            throw new MessageException.DecodeFailure(String.format("decode message: %s exception", message), e);
        }
    }

    /**
     * ##1,IMEI#
     *
     * @param message frame message
     * @return RegistryMessage
     */
    public RegistryMessage decodeRegistry(String message) {

        String[] components = message.split("[#|,]");
        String imei = components[3];

        return new RegistryMessage(message, new DeviceID(DeviceID.Type.IMEI, imei));

    }

    /**
     * ##2,IMEI,latitude,longitude,altitude,course,speed,time,outdated,alarms=[sos|low_battery]#
     *
     * @param message frame message
     * @return Position
     */
    private Location decodeLocation(String message) throws MessageException.DecodeFailure {

        Location location = new Location();

        String[] components = message.split("[#,]");
        if (components.length != 11 && components.length != 12) {
            throw new MessageException.DecodeFailure("invalid message: " + message);
        }

        location.setDeviceId(new DeviceID(DeviceID.Type.IMEI, components[3]));
        location.setLocated(true);
        location.setLatitude(Double.parseDouble(components[4]));
        location.setLongitude(Double.parseDouble(components[5]));
        location.setAltitude(Double.parseDouble(components[6]));
        location.setCourse(Double.parseDouble(components[7]));
        location.setSpeed(Double.parseDouble(components[8]));
        location.setTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(components[9])),
                ZoneId.systemDefault()));
        location.getAttributes().put(Location.Attributes.KEY_BOOL_OUTDATED, "1".equals(components[10]));

        if (components.length == 12) {
            final String alarms = components[11];
            if (!StringUtils.isEmpty(alarms)) {
                final List<String> alerts = new ArrayList<>();
                Arrays.stream(alarms.split("\\|")).forEach(alarm -> {
                    if ("sos".equalsIgnoreCase(alarm)) {
                        alerts.add(Location.Attributes.ALARM_SOS);
                    }
                    if ("low_battery".equalsIgnoreCase(alarm)) {
                        alerts.add(Location.Attributes.ALARM_LOW_BATTERY);
                    }
                });

                if (!alerts.isEmpty()) {
                    location.getAttributes().put(Location.Attributes.KEY_ALARM, String.join(",", alerts));
                }
            }
        }

        return location;
    }
}






