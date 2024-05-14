package by.zemich.cataloguems.catalogueservice.domain.response;

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
    private String sizeRange;
    private List<Integer> sizeList;
    private boolean isInSize;

}
