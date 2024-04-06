package by.zemich.userservice.service.api;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public interface UserWebService {
    ServerResponse save(ServerRequest request);

    ServerResponse update(ServerRequest request);

    ServerResponse getByUuid(ServerRequest request);

    ServerResponse getByEmailAddress(ServerRequest request);

    ServerResponse getAll(ServerRequest request);

    ServerResponse delete(ServerRequest request);
}
