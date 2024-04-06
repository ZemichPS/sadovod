package by.zemich.supplierservice.infrastructure.adapters.input.rest.model.response;


import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorResponse(String code,
                            String message,
                            List<String> details,
                            LocalDateTime timestamp) {
}
