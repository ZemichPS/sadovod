package by.zemich.parserservice.core.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ShortPostDto {
    private String message;
    private List<String> imageLinksList;

}
