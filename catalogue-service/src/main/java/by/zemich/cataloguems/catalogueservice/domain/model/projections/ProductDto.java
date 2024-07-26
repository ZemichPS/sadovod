package by.zemich.cataloguems.catalogueservice.domain.model.projections;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductDto {
    UUID getUuid();
    String getName();
    UUID getPostUuid();
    String getCategory();
    BigDecimal getOriginPrice();
    boolean getAvailableInPieces();
    boolean getAvailableInBulk();
    Integer getQuantityInPackage();
    String getAttributes();
    SupplierDto getSupplier();
    List<ImageDto> getImageEntityList();

    interface SupplierDto {
        public UUID getUuid();
        public String getName();
    }

    interface ImageDto{
        UUID getUuid();
        String getLink();
    }
}
