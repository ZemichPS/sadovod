package by.zemich.textprocessormicroservice.application.internal.commandservices;

import by.zemich.textprocessormicroservice.application.internal.outboundservices.alc.ExternalAiMistralService;
import by.zemich.textprocessormicroservice.domain.model.ProductInfo;
import org.springframework.stereotype.Service;

@Service
public class AiPostCommandService {
    private final ExternalAiMistralService externalAiMistralService;

    public AiPostCommandService(ExternalAiMistralService externalAiMistralService) {
        this.externalAiMistralService = externalAiMistralService;
    }

    public ProductInfo parseProductDescriptionFormPostText(PostParseCommand command){
        return externalAiMistralService.getProductInfoFromPostText(command.getText());
    }
}
