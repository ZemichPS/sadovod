package by.zemich.vkms.application.outboundservices.alc;

import by.zemich.vkms.application.outboundservices.alc.model.VKPostQuery;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.wall.GetFilter;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExternalVKService {
    private final VkApiClient vkApiClient;
    private final com.vk.api.sdk.client.actors.UserActor userActor;


    public ExternalVKService(VkApiClient vkApiClient, UserActor userActor) {
        this.vkApiClient = vkApiClient;
        this.userActor = userActor;
    }

    public GetResponse doPostRequest(VKPostQuery query) {
        try {
            return getVkPostResponse(query);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
    }

    private GetResponse getVkPostResponse(VKPostQuery query) throws ClientException, ApiException {

        return vkApiClient.wall().get(userActor)
                .domain(query.getSupplierVkId())
                .count(query.getCount())
                .offset(query.getOffset())
                .filter(GetFilter.valueOf("OWNER"))
                .execute();
    }


}

