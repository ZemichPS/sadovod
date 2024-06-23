package by.zemich.vkms.application.usecases;


import lombok.*;

import java.util.UUID;

public interface VkPostManagementUseCase {
        void fetchPostsFromVK();

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        class FetchVKPostQuery {
                private String supplierVkId;
                private UUID supplierUUID;
                private String supplierName;
                private Integer count;
                private Integer interval;
                private Integer offset;
        }
}
