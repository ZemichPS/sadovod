package by.zemich.vkms.domain.model.specifications;

import by.zemich.vkms.domain.model.exceptions.GenericSpecificationException;
import by.zemich.vkms.domain.model.valueobjects.Text;

public class VkPostTextSpecification extends AbstractSpecification<Text> {
    private final Integer MIN_TEXT_LENGTH = 10;

    @Override
    public boolean isSatisfiedBy(Text item) {
        String text = item.getText();
        return text.length() >= MIN_TEXT_LENGTH;
    }

    @Override
    public void check(Text item) {
        if(!isSatisfiedBy(item)) throw new GenericSpecificationException("The minimum length of the text should be at least %s".formatted(MIN_TEXT_LENGTH));
    }
}
