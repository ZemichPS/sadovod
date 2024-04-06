package by.zemich.parserservice.service.impl;

import by.zemich.parserservice.core.model.TelegramSessionDto;
import by.zemich.parserservice.dao.entity.TelegramSessionHash;
import by.zemich.parserservice.dao.api.TelegramSessionRepository;
import by.zemich.parserservice.service.api.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    private final TelegramSessionRepository sessionDao;
    private final ModelMapper modelMapper;

    public SessionServiceImpl(TelegramSessionRepository sessionDao,
                              ModelMapper modelMapper) {
        this.sessionDao = sessionDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public TelegramSessionHash save(TelegramSessionDto session) {
        TelegramSessionHash newTelegramSessionHash = modelMapper.map(session, TelegramSessionHash.class);
        return sessionDao.save(newTelegramSessionHash);
    }

    @Override
    public List<TelegramSessionHash> getAll() {
        return Streamable.of(sessionDao.findAll()).stream().toList();
    }

    @Override
    public Optional<TelegramSessionHash> getById(String username) {
        return sessionDao.findById(username);
    }

    @Override
    public TelegramSessionHash update(TelegramSessionDto session) {
        TelegramSessionHash telegramSessionHashForUpdate = modelMapper.map(session, TelegramSessionHash.class);
        return sessionDao.save(telegramSessionHashForUpdate);

    }

    @Override
    public void delete(TelegramSessionDto session) {
        TelegramSessionHash telegramSessionForDelete = modelMapper.map(session, TelegramSessionHash.class);
        sessionDao.delete(telegramSessionForDelete);
    }

    @Override
    public boolean existsById(String username) {
        return sessionDao.existsById(username);
    }
}
