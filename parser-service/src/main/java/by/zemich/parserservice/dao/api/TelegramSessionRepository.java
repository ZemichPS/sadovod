package by.zemich.parserservice.dao.api;


import by.zemich.parserservice.dao.entity.TelegramSessionHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramSessionRepository extends CrudRepository<TelegramSessionHash, String> {
}
