package com.zhenhui.demo.tracer.server.support.codec;



import com.zhenhui.demo.tracer.domain.Command;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class StringProtocolEncoder extends AbstractProtocolEncoder {

    public interface ValueFormatter {
        String formatValue(String key, Object value);
    }

    protected byte[] formatCommand(Command command, String format, ValueFormatter valueFormatter, String... keys) throws Exception {

        List<Object> values = new ArrayList<>();
        for (String key : keys) {
            if (valueFormatter != null) {
                values.add(valueFormatter.formatValue(key, command.getAttributes().get(key)));
            } else {
                values.add(command.getAttributes().get(key));
            }
        }

        return String.format(format, values.toArray()).getBytes(StandardCharsets.UTF_8);
    }

    protected byte[] formatCommand(Command command, String format, String... keys) throws Exception {
        return formatCommand(command, format, null, keys);
    }

}
