package by.zemich.textprocessormicroservice.domain.model;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ColorInfo {
    private List<String> colors;
    private boolean isItPossibleToChooseColor;
    private boolean isItPossibleToChoosePicture;


}
