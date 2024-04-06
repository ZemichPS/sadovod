package by.zemich.parserservice.service.api;


import by.zemich.parserservice.core.model.viber.ViberMessage;

public interface ViberService {
       String send(ViberMessage message);

}
