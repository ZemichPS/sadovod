package by.zemich.vkms.domain.model.specifications;

import by.zemich.vkms.domain.model.aggregates.VkPost;
import by.zemich.vkms.domain.model.exceptions.GenericSpecificationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class VkPostDateSpecification extends AbstractSpecification<VkPost> {
    private final int MAX_OF_DAYS_AFTER_PUBLICATION = 7;

    @Override
    public boolean isSatisfiedBy(VkPost vkPost) {
        LocalDateTime published = vkPost.getDateInfo().getPublishedAt();
        return Period.between(LocalDate.from(LocalDateTime.now()), published.toLocalDate()).getDays() <= MAX_OF_DAYS_AFTER_PUBLICATION;
    }

    @Override
    public void check(VkPost item) {
        if(!isSatisfiedBy(item))
            throw new GenericSpecificationException("The publication date exceeds the maximum time");
    }
}
