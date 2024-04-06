package by.zemich.vkservice.application.model;

import by.zemich.vkservice.domain.model.supplier.Supplier;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VKPostQuery {
    private int interval;
    private int count;
    private int offset;
    private Supplier supplier;
}
