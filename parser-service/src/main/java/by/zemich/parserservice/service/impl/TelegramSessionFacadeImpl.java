package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.exeption.NotFoundSessionException;
import by.zemich.parserservice.core.model.TelegramSessionDto;
import by.zemich.parserservice.dao.entity.TelegramSessionHash;
import by.zemich.parserservice.service.api.SessionService;
import by.zemich.parserservice.service.api.TelegramSessionFacade;
import by.zemich.parserservice.service.api.UserFacade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramSessionFacadeImpl implements TelegramSessionFacade {

    private final SessionService sessionService;
    private final ModelMapper modelMapper;
    private final UserFacade userFacade;

    public TelegramSessionFacadeImpl(SessionService sessionService, ModelMapper modelMapper, UserFacade userFacade) {
        this.sessionService = sessionService;
        this.modelMapper = modelMapper;
        this.userFacade = userFacade;
    }

    @Override
    public boolean checkForRegistration(String username) {
        if(sessionService.existsById(username)){
                return true;
        }
        return userFacade.existsByUsername(username);
    }

    @Override
    public TelegramSessionDto create(Update telegramUpdate) {
        TelegramSessionDto sessionDto = new TelegramSessionDto();
        sessionDto.setUserName(telegramUpdate.getMessage().getFrom().getUserName());
        sessionDto.setChatId(telegramUpdate.getMessage().getChatId());
        TelegramSessionHash sessionHash = sessionService.save(sessionDto);
        return modelMapper.map(sessionHash, TelegramSessionDto.class);
    }

    @Override
    public TelegramSessionDto create(TelegramSessionDto telegramSessionDto) {
        TelegramSessionHash sessionHash = sessionService.save(telegramSessionDto);
        return modelMapper.map(sessionHash, TelegramSessionDto.class);
    }

    @Override
    public TelegramSessionDto create(Long telegramChatId, String username) {
        TelegramSessionDto sessionDto = new TelegramSessionDto();
        sessionDto.setUserName(username);
        sessionDto.setChatId(telegramChatId);
        TelegramSessionHash sessionHash = sessionService.save(sessionDto);
        return modelMapper.map(sessionHash, TelegramSessionDto.class);
    }

    @Override
    public Optional<TelegramSessionDto> getById(String username) {
        return sessionService.getById(username)
                .map(telegramSessionHash -> modelMapper.map(telegramSessionHash, TelegramSessionDto.class))
                .or(Optional::empty);
    }

    @Override
    public List<TelegramSessionDto> getAll(String username) {
        return sessionService.getAll().stream()
                .map(hash -> modelMapper.map(hash, TelegramSessionDto.class))
                .toList();
    }


    @Override
    public TelegramSessionDto update(TelegramSessionDto telegramSessionDto) {
        TelegramSessionHash updatedSessionHash = sessionService.save(telegramSessionDto);
        return modelMapper.map(updatedSessionHash, TelegramSessionDto.class);
    }

    @Override
    public TelegramSessionDto delete(TelegramSessionDto telegramSessionDto) {
        sessionService.delete(telegramSessionDto);
        return telegramSessionDto;
    }


}
