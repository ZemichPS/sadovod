package by.zemich.parserservice.core.model;

import lombok.*;

@Builder
@Data
public class VKPostQuery {
    private int interval;
    private int count;
    private int offset;
    private SupplierDto supplier;
}
