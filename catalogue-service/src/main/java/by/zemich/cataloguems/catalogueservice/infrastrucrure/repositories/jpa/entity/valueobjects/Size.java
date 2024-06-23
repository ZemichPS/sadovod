package by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Embeddable
public class Size {
    private String availableSizes;
    boolean inSize;
}
