package by.zemich.vkms.domain.model.specifications;

import by.zemich.vkms.domain.model.entities.Supplier;
import by.zemich.vkms.domain.model.exceptions.GenericSpecificationException;

import java.util.Objects;

public class ValidSupplierSpecification extends AbstractSpecification<Supplier> {
    @Override
    public boolean isSatisfiedBy(Supplier supplier) {
        return Objects.nonNull(supplier.getUuid());
    }

    @Override
    public void check(Supplier item) {
        if(!isSatisfiedBy(item)) throw new GenericSpecificationException("It's not possible to save supplier without Id (UUID)");
    }
}
