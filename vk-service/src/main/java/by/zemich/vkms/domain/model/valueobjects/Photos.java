package by.zemich.vkms.domain.model.valueobjects;

import by.zemich.vkms.domain.model.entities.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photos {
    private List<Photo> photos;
}
