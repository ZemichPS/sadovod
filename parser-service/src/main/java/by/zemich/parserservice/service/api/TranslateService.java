package by.zemich.parserservice.service.api;


import by.zemich.parserservice.core.enums.Language;

public interface TranslateService {
    String translate(String sourceText, Language SourceLang, Language TargetLang);
    String translateRuToEn(String sourceText);
    String translateEnToRu(String sourceText);
    String translateRuToEnAndThenBack(String sourceText);
}
