package com.peaq.ec3.shopp.response;

import java.util.List;

public class PageResponse<T> {


    private long total;

    private List<T> records;

    public PageResponse() {
    }

    public PageResponse(long total, List<T> records) {
        super();
        this.total = total;
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

}
