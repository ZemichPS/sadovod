package by.zemich.textprocessormicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeInfo {
    private String sizesRange;
    private List<Integer> sizesList;
    private boolean isInSize;

}
