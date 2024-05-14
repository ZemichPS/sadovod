package by.zemich.cataloguems.catalogueservice.domain.response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ColorInfo {
    private List<String> colorlist;
    private boolean possibleToChooseColor;
    private boolean possibleToChoosePicture;


}
