package by.zemich.supplierservice.interfaces.rest.model.response;


import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorResponse(String code,
                            String message,
                            List<String> details,
                            LocalDateTime timestamp) {
}
