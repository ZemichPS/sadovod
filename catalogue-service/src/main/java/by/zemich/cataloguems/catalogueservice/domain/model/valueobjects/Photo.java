package by.zemich.cataloguems.catalogueservice.domain.model.valueobjects;

import java.util.UUID;

public record Photo(UUID uuid, UUID productUuid, String link) {
}
