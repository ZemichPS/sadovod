package by.zemich.cataloguems.catalogueservice.domain.policy.parse.shared;

import java.util.Map;

public interface ParsePolicy {
    Map<String, Object> parse(String aiResponse);
}
