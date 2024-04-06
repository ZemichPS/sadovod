package by.zemich.authorizationserver.service.api;

import by.zemich.authorizationserver.core.model.UserCreateDto;
import by.zemich.authorizationserver.core.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface FeignClientUserService {
    @RequestMapping(method = RequestMethod.POST, value = "/api/users/new")
    User create(UserCreateDto newUser);

    @RequestMapping(method = RequestMethod.GET, value = "/api/users/get_by_email")
    User getByEmail(@RequestParam String email);

}
