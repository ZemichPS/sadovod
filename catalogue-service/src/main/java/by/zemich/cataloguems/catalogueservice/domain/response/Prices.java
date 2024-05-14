package by.zemich.cataloguems.catalogueservice.domain.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Prices {
    Double costPerPiece;
    Double costPerSetOrBox;
}
