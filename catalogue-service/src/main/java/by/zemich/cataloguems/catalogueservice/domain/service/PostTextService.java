package by.zemich.cataloguems.catalogueservice.domain.service;

import com.vdurmont.emoji.EmojiParser;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PostTextService {
    public static String removeEmojis(String originalText) {
        String firstLevel = originalText.replaceAll("[\\p{So}\\p{Cn}]", "");
        return EmojiParser.removeAllEmojis(firstLevel);
    }

    //TODO добавить подгрузку листа с излишними сообшениями
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