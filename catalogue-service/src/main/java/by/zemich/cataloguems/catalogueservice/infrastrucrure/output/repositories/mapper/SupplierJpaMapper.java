package by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.mapper;

import by.zemich.cataloguems.catalogueservice.domain.model.entity.Supplier;
import by.zemich.cataloguems.catalogueservice.infrastrucrure.output.repositories.jpa.entity.SupplierEntity;

public class SupplierJpaMapper {
    public static SupplierEntity supplierDomainToEntity(Supplier supplier){
        return SupplierEntity.builder()
                .uuid(supplier.getId().uuid())
                .name(supplier.getName())
                .build();
    }
}
