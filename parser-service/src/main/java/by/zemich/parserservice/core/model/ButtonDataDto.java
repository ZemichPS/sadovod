package by.zemich.parserservice.core.model;

import lombok.Builder;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
@Builder
public class ButtonDataDto {
    private String buttonText;
    private String callBackData;
}
