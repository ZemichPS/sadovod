package by.zemich.cataloguems.catalogueservice.domain.policy.parse;


import by.zemich.cataloguems.catalogueservice.domain.policy.parse.shared.ParsePolicy;

import java.util.*;
import java.util.function.Predicate;

public class SimpleParsePolicy implements ParsePolicy {

    private final Map<String, Object> parseContent = new HashMap<>();

    Predicate<String> specified = s -> !s.contains("not specified");
    Predicate<String> unclear  = s -> !s.contains("unclear");
    Predicate<String> notProvided  = s -> !s.contains("not provided");

    public Map<String, Object> parse(String aiResponse) {
        Arrays.stream(aiResponse.split("\n"))
                .map(String::toLowerCase)
                .filter(specified.and(unclear).and(notProvided))
                .forEach(line -> {
                    String[] keyValue = line.split(":");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim().toLowerCase().replace(" ", "_");
                        String value = keyValue[1].trim();
                        parseContent.put(key, value);
                    }
                });
        return parseContent;
    }


}
