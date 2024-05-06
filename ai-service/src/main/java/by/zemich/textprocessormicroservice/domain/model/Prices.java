package by.zemich.textprocessormicroservice.domain.model;

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
