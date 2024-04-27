package by.zemich.vkms.application.outboundservices.alc.model;

import by.zemich.vkms.domain.model.entities.Supplier;

public class VKPostQuery {
    private Integer interval;
    private Integer count;
    private Integer offset;
    private Supplier supplier;

    public VKPostQuery(Integer interval, Integer count, Integer offset, Supplier supplier) {
        this.interval = interval;
        this.count = count;
        this.offset = offset;
        this.supplier = supplier;
    }

    public VKPostQuery() {
    }

    public Integer getInterval() {
        return interval;
    }

    public VKPostQuery setInterval(Integer interval) {
        this.interval = interval;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public VKPostQuery setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public VKPostQuery setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public VKPostQuery setSupplier(Supplier supplier) {
        this.supplier = supplier;
        return this;
    }
}
