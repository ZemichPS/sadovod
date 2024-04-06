package by.zemich.parserservice.service.api;

public interface IPostCompositor<T, S> {
    T compose(S sourceDto);
}
