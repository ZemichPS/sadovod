package by.zemich.cataloguems.catalogueservice.domain.service;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PostTextService {
    public static String removeEmojis(String originalText) {
        String firstLevel = originalText.replaceAll("[\\p{So}\\p{Cn}]", "");
        return firstLevel.replaceAll("[\\p{InEmoticons}\\p{InMiscellaneousSymbolsAndPictographs}\\p{InTransportAndMapSymbols}\\p{InSupplementalSymbolsAndPictographs}\\p{InMiscellaneousSymbols}\\p{InDingbats}\\p{InSymbolsAndPictographsExtendedA}]", "");
    }

    public static String removeLinesAccordingToBlackList(String originalText, String[] blackList) {
        return Arrays.stream(originalText.split("\n"))
                .filter(line-> !containsAny(line, blackList))
                .collect(Collectors.joining(""));
    }



    private static boolean containsAny(String line, String[] blackList) {
        for (String blackListItem : blackList) {
            if (line.contains(blackListItem)) {
                return true;
            }
        }
        return false;
    }

}
