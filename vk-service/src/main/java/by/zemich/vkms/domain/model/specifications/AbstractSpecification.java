package by.zemich.vkms.domain.model.specifications;

public abstract class AbstractSpecification<T> implements Specification<T>{
    public abstract void check(T item);
}
