package by.zemich.parserservice.endpoint.telegram.impl;


import by.zemich.parserservice.config.property.GTranslateProperty;
import by.zemich.parserservice.core.enums.Language;
import by.zemich.parserservice.core.enums.TranslateException;
import by.zemich.parserservice.service.api.TranslateService;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


@Service
public class TranslateServiceImpl implements TranslateService {

    private final RestTemplate restTemplate;
    private final GTranslateProperty translateProperty;

    public TranslateServiceImpl(RestTemplate restTemplate, GTranslateProperty translateProperty) {
        this.restTemplate = restTemplate;
        this.translateProperty = translateProperty;
    }

    @Override
    public String translate(String sourceText, Language sourceLang, Language targetLang) {
        URI uri = null;
        try {
            uri = buildApplicationRequest(sourceText, sourceLang, targetLang);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String response = restTemplate.getForObject(uri, String.class);
        assert response != null;
        if (response.contains("Exception")) {
            //TODO обработать текс и оставить только текст exception
            String exception = response;
            throw new TranslateException(response);
        }
        return response;
    }

    @Override
    public String translateRuToEn(String sourceText) {
        URI uri = null;
        try {
            uri = buildApplicationRequest(sourceText, Language.RU, Language.EN);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String response = restTemplate.getForObject(uri, String.class);
        assert response != null;
        if (response.contains("Exception")) {
            //TODO обработать текс и оставить только текст exception
            String exception = response;
            throw new TranslateException(response);
        }
        return response;
    }

    @Override
    public String translateEnToRu(String sourceText) {
        URI uri = null;
        try {
            uri = buildApplicationRequest(sourceText, Language.EN, Language.RU);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String response = restTemplate.getForObject(uri, String.class);
        assert response != null;
        if (response.contains("Exception")) {
            //TODO обработать текс и оставить только текст exception
            String exception = response;
            throw new TranslateException(response);
        }
        return response;
    }

    @Override
    public String translateRuToEnAndThenBack(String sourceText) {
        String translatedToEn = translateRuToEn(sourceText);
        return translateEnToRu(translatedToEn);
    }

    URI buildApplicationRequest(String sourceText, Language sourceLang, Language targetLang) throws URISyntaxException {
        return new URIBuilder().setScheme("https")
                .setHost("script.google.com")
                .setPath("/macros/s/" + translateProperty.getAppid()+"/exec")
                .addParameter("q", sourceText)
                .addParameter("source", sourceLang.name().toLowerCase())
                .addParameter("target", targetLang.name().toLowerCase())
                .build();
    }
}
