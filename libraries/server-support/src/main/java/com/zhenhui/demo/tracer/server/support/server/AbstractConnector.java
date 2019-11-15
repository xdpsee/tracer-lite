package com.zhenhui.demo.tracer.server.support.server;


import com.zhenhui.demo.tracer.common.utils.JsonUtils;
import com.zhenhui.demo.tracer.domain.server.Configs;
import com.zhenhui.demo.tracer.domain.server.ServerConnector;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public abstract class AbstractConnector implements ServerConnector {

    private static final Logger logger = LoggerFactory.getLogger(AbstractConnector.class);

    protected final ServerContext context;

    protected final Configs configs;

    protected AbstractConnector(ServerContext context) {
        this.context = context;
        this.configs = loadConfigs();
    }

    @Override
    public Configs configs() {
        return configs;
    }

    private Configs loadConfigs() {

        InputStream inputStream = null;
        try {
            final String path = configFilePath();

            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

            StringBuilder sb = new StringBuilder();
            List<String> lines = IOUtils.readLines(inputStream, "UTF-8");
            lines.forEach(sb::append);

            final String content = sb.toString();

            try {
                Configs configs = new Configs();
                configs.putAll(JsonUtils.toMap(content));
                return configs;
            } finally {
                logger.info("loading configs file: " + path + "\r\n" + content);
            }

        } catch (Throwable e) {
            throw new IllegalStateException("Load configs file: " + configFilePath() + " failed!", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }

    private String configFilePath() {
        return String.format("conf/%s.json", protocol().getName());
    }
}
