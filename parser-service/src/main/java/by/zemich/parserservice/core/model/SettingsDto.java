package by.zemich.parserservice.core.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class SettingsDto {

    private UUID uuid;

    private Timestamp dtCreate;

    private Timestamp dtUpdate;

    private Integer interval;



}
