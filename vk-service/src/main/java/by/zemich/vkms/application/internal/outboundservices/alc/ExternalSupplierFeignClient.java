package by.zemich.vkms.application.internal.outboundservices.alc;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@FeignClient("SUPPLIER-SERVICE")
public interface ExternalSupplierFeignClient  {
    @RequestMapping(method = RequestMethod.GET, value = "/supplier/v1/api")
    List<Supplier> getAll();
    @RequestMapping(method = RequestMethod.GET, value = "/supplier/v1/api/{uuid}")
    Supplier getByUuid(UUID supplierUuid);


    @AllArgsConstructor @NoArgsConstructor @Builder @Getter  @Setter
    class Supplier {
        private UUID uuid;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String name;
        private String vkId;
        private String vkLink;
        private String address;
        private String phone;
        private Specialization specialization;

        public enum Specialization{
            DRESS, OTHER, WOMEN_CLOTHES
        }
    }


}
