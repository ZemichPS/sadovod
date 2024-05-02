package by.zemich.vkms.application.outboundservices.alc.model;

public class VKPostQuery {
    private Integer interval;
    private Integer count;
    private Integer offset;
    private String supplierVkId;



    public VKPostQuery() {
    }

    public VKPostQuery(Integer interval, Integer count, Integer offset, String supplierVkId) {
        this.interval = interval;
        this.count = count;
        this.offset = offset;
        this.supplierVkId = supplierVkId;
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

    public String getSupplierVkId() {
        return supplierVkId;

    }

    public VKPostQuery setSupplierVkId(String supplierVkId) {
        this.supplierVkId = supplierVkId;
        return this;
    }
}
