package by.zemich.vkms.domain.model.aggregates;

import by.zemich.vkms.domain.model.entities.Photo;
import by.zemich.vkms.domain.model.entities.Supplier;
import by.zemich.vkms.domain.model.specifications.Specification;
import by.zemich.vkms.domain.model.specifications.ValidSupplierSpecification;
import by.zemich.vkms.domain.model.specifications.VkPostDateSpecification;
import by.zemich.vkms.domain.model.specifications.VkPostTextSpecification;
import by.zemich.vkms.domain.model.valueobjects.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.*;
import java.util.function.Predicate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VkPost {

    private Id id;
    private DateInfo dateInfo;
    private VkPostId vkPostId;
    private Text text;
    private Photos photos;
    private Supplier supplier;

//    public static Predicate<VkPost> publishDatePredicate = vkPost -> {
//        LocalDateTime published = vkPost.dateInfo.getPublishedAt();
//        return Period.between(LocalDate.now(), published.toLocalDate()).getDays() <= actualPeriodOfDay;
//    };


    public URL getLinkToPost() {
        String host = "vk.com";
        String scheme = "https";

        try {
            return new URIBuilder().setScheme(scheme)
                    .setHost(host)
                    .setPath("/" + this.vkPostId.getOriginalPostId())
                    .addParameter("w", "wall" + this.supplier.getVkId() + "_" + this.vkPostId.getOriginalPostId())
                    .build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public Integer getDaysFromPublishedAt() {
        return Period.between(LocalDate.now(), this.dateInfo.getPublishedAt().toLocalDate()).getDays();
    }

    public boolean addPhoto(Photo photo) {
        return photos.getPhotos().add(photo);
    }

    public boolean addText(Text text) {
        VkPostTextSpecification textSpecification = new VkPostTextSpecification();
        textSpecification.check(text);
        this.text = text;
        return true;
    }

    public boolean addSupplier(Supplier supplier) {
        ValidSupplierSpecification supplierSpecification = new ValidSupplierSpecification();
        supplierSpecification.check(supplier);
        this.supplier = supplier;
        return true;
    }

}
