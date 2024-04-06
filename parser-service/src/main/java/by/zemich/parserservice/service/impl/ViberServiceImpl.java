package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.config.property.ViberProperty;
import by.zemich.parserservice.core.model.viber.ViberMessage;
import by.zemich.parserservice.service.api.ViberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViberServiceImpl implements ViberService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper;

    public ViberServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public String send(ViberMessage message) {

        String jsonMessage = null;
        try {
            jsonMessage = messageToJsonToString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String urlTo = "https://chatapi.viber.com/pa/post";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> messageHttpEntityEntity = new HttpEntity<>(jsonMessage, headers);

        return restTemplate.postForObject(urlTo, messageHttpEntityEntity, String.class);
    }

    private String messageToJsonToString(ViberMessage message) throws JsonProcessingException {
        return mapper.writeValueAsString(message);
    }


}
