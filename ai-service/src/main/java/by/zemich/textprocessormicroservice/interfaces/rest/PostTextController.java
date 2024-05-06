package by.zemich.textprocessormicroservice.interfaces.rest;

import by.zemich.textprocessormicroservice.application.internal.commandservices.AiPostCommandService;
import by.zemich.textprocessormicroservice.application.internal.commandservices.PostParseCommand;
import by.zemich.textprocessormicroservice.domain.model.ProductInfo;
import by.zemich.textprocessormicroservice.interfaces.rest.request.Request;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai")
public class PostTextController {

  private final ModelMapper modelMapper;
  private final AiPostCommandService commandService;


  public PostTextController(ModelMapper modelMapper, AiPostCommandService commandService) {
        this.modelMapper = modelMapper;
        this.commandService = commandService;
    }

    @PostMapping("/v1/api/post")
    public ResponseEntity<ProductInfo> getProductInfo(@RequestBody Request request){
        final ProductInfo productInfo = commandService.parseProductDescriptionFormPostText(modelMapper.map(request, PostParseCommand.class));
        return ResponseEntity.ok(productInfo);
    }
}
