package by.zemich.vkms.domain.model.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class FetchVKPostQuery {
    private String supplierVkId;
    private UUID supplierUUID;
    private String supplierName;
    private Integer count = 30;
    private Integer interval = 15;
    private Integer offset = 0;


}
