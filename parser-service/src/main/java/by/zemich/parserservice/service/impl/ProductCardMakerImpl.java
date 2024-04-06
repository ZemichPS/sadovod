package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.model.ProductCardDto;
import by.zemich.parserservice.core.model.VkPostDto;
import by.zemich.parserservice.core.enums.ETokenType;
import by.zemich.parserservice.service.api.ProductCardMaker;
import by.zemich.parserservice.service.api.TranslateService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ProductCardMakerImpl implements ProductCardMaker<VkPostDto> {

    private Matcher matcher;
    private Pattern pattern;
    private final TranslateService translateService;

    public ProductCardMakerImpl(TranslateService translateService) {
        this.translateService = translateService;
    }


    @Override
    public ProductCardDto create(VkPostDto postDto) {
        String content = postDto.getText().toLowerCase();

        content = Arrays.stream(translateService.translateRuToEnAndThenBack(content).split("\n"))
                .map(translateService::translateRuToEnAndThenBack)
                .collect(Collectors.joining("\n"));

        //content = translateService.translateRuToEnAndThenBack(content);

        ProductCardDto cardDto = new ProductCardDto();
        cardDto.setUuid(UUID.randomUUID());
        cardDto.setImageLinks(postDto.getImagesLinkList());
        cardDto.setProductType(postDto.getProductType());
        cardDto.setProductCategory(getClothName(content));
        cardDto.setColor(getColors(content));
        cardDto.setSize(getSize(content));
        cardDto.setFabric(getFabric(content));
        cardDto.setSeason(getSeason(content));
        cardDto.setSale(isSale(content));
        cardDto.setFleece(isFleece(content));
        cardDto.setInSize(isInSize(content));
        cardDto.setNotInSize(isNotInSize(content));
        cardDto.setPublishedAt(postDto.getPublishedAt());
        cardDto.setLink(postDto.getLink());

        Map<String, BigDecimal> priceMap = getPrices(content);

        if (priceMap.isEmpty()) {
            return cardDto;
        }

        if (priceMap.get("costPerSet") != null) {

            cardDto.setCostPerSet(priceMap.get("costPerSet"));
            cardDto.setOnlySetSale(true);

        } else if (priceMap.get("costPerPeace") != null) {
            cardDto.setCostPerPeace(priceMap.get("costPerPeace"));
            cardDto.setOnlySetSale(false);
        }



        return cardDto;
    }

    private String getClothName(String content) {
        return createToken(ETokenType.CLOTHES, content).orElse("");
    }

    private String getColors(String content) {

        List<String> colorList = createColorList(content).get();


        if (!colorList.isEmpty()) {
            String colors = colorList.stream().collect(Collectors.joining(", ")).trim();
            if (!colors.equals("")) {
                colors = colors.substring(0, colors.length() - 1);
            }
            return colors;
        }
        return null;

    }


    private String getSize(String content) {
        String size = createToken(ETokenType.SIZE, content).orElse("");
        // TODO КОСТЫЛЬ, ИСПРАВАВИТЬ ДЛЯ ПРОДА
        size = size.replace("ы", "");
        size = size.replace("л", "l");
        size = size.toUpperCase();
        return size.trim();
    }

    private String getFabric(String content) {
        return createToken(ETokenType.FABRIC, content).orElse("");
    }

    private String getSeason(String content) {
        return createToken(ETokenType.SEASON, content).orElse("");
    }

    private boolean isFleece(String content) {
        return createToken(ETokenType.FLEECE, content).isPresent();
    }

    private boolean isInSize(String content) {
        return createToken(ETokenType.IN_SIZE, content).isPresent();
    }

    private boolean isNotInSize(String content) {
        return createToken(ETokenType.NOT_IN_SIZE, content).isPresent();
    }

    private boolean isSale(String content) {
        return createToken(ETokenType.SALE, content).isPresent();
    }

    private Optional<String> createToken(ETokenType tokenType, String content) {

        String[] keyWords = getKeyWords(tokenType);

        for (String tok : keyWords) {
            tok = tok.replace("\n", "").trim();
            pattern = Pattern.compile(tok, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(content);
            if (matcher.find()) {
                return Optional.of(content.substring(matcher.start(), matcher.end()).trim());
            }
        }
        return Optional.empty();
    }

    private Optional<String> createTokenPrice(ETokenType tokenType, String line) {

        String[] keyWords = getKeyWords(tokenType);

        for (String tok : keyWords) {
            tok = tok.replace("\n", "").trim();
            pattern = Pattern.compile(tok, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(line);
            if (matcher.find()) {
                return Optional.of(line.substring(matcher.start(), matcher.end()).trim());
            }
        }
        return Optional.empty();
    }

    private String[] getKeyWords(ETokenType tokenType) {
        return tokenType.getRegex().split("\\|");
    }


    private Optional<List<String>> createColorList(String content) {
        List<String> colorList = new ArrayList<>();
        String[] keyWords = ETokenType.COLOR.getRegex().split("\\|");
        for (String color : keyWords) {
            color = color.replace("\n", "").trim();
            pattern = Pattern.compile(color, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(content);
            if (matcher.find()) {
                String foundColor = content.substring(matcher.start(), matcher.end()).trim();
                colorList.add(foundColor);
            }
        }

        return Optional.of(colorList);
    }

    private Map<String, BigDecimal> getPrices(String content) {
        content = content.toLowerCase();

        Map<String, BigDecimal> pricesMap = new HashMap<>();
        List<String> pricesList = new ArrayList<>();

        List<ETokenType> tokenTypeList = new ArrayList<>();
        tokenTypeList.add(ETokenType.COST_PER_SET);
        tokenTypeList.add(ETokenType.COST_PER_PEACE);


        String[] keyWords = ETokenType.PRICE_KEY_WORDS.getRegex().split("\\|");
        String[] lines = content.split("\\n");

        // добавляем все строки, где есть упоминание про цену
        for (String line : lines) {
            for (String keyWord : keyWords) {
                Pattern pattern = Pattern.compile(keyWord);
                matcher = pattern.matcher(line);

                if (matcher.find()) {
                    pricesList.add(line);
                }
            }
        }

        for (int i = 0; i < pricesList.size(); i++) {
            String line = pricesList.get(i);
            // проверяем на возможность покупки набора
            String token = createToken(ETokenType.COST_PER_SET, line).orElse("");
            if (!token.equals("")) {
                pricesMap.put("costPerSet", tokenToNum(token));
                pricesList.remove(i);
                break;
            }
        }


        for (int i = 0; i < pricesList.size(); i++) {
            String line = pricesList.get(i);
            // проверяем на возможность покупки поштучно
            String token = createToken(ETokenType.COST_PER_PEACE, line).orElse("");
            if (!token.equals("")) {
                pricesMap.put("costPerPeace", tokenToNum(token));
                pricesList.remove(i);
                break;
            }
        }

        return pricesMap;
    }

    private BigDecimal tokenToNum(String token) {
        return new BigDecimal(token.trim().replaceAll(",", ""));
    }
}



