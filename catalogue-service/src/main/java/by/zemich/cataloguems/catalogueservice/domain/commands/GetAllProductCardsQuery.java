package by.zemich.cataloguems.catalogueservice.domain.commands;

import org.springframework.data.domain.Pageable;


public class GetAllProductCardsQuery {
    private Pageable pageable;

    public GetAllProductCardsQuery(Pageable pageable) {
        this.pageable = pageable;
    }

    public GetAllProductCardsQuery() {
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
