package by.zemich.parserservice.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Post {
    private ProductCardDto productCard;

    public Post(ProductCardDto productCard) {
        this.productCard = productCard;
    }
}
