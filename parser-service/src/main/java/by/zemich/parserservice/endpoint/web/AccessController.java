package by.zemich.parserservice.endpoint.web;

import by.zemich.parserservice.core.model.VkPostDto;
import by.zemich.parserservice.service.impl.VKServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class AccessController {

private final VKServiceImpl vkService;

    public AccessController(VKServiceImpl vkService) {
        this.vkService = vkService;
    }


    @GetMapping("/some")
    public ResponseEntity<List<VkPostDto>> getPost(){
        return null;

    }
}
