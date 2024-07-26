package by.zemich.gatewayservice.util;

import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

public class ParamsUtil {
    public static Integer getIntParamByName(ServerWebExchange exchange, String paramName) {
        Map<String, String> queryParams = exchange
                .getRequest()
                .getQueryParams()
                .toSingleValueMap();
        return Integer.parseInt(queryParams.get(paramName));
    }

    public static String getStringParamByName(ServerWebExchange exchange, String paramName) {
        Map<String, String> queryParams = exchange
                .getRequest()
                .getQueryParams()
                .toSingleValueMap();
        return queryParams.get(paramName);
    }
}
