package by.zemich.cataloguems.catalogueservice.infrastrucrure.repositories.jpa.entity.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.value.qual.BoolVal;

@Embeddable

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Color {
    private String colors;
    boolean colorChoice;
    boolean pictureChoice;
}
