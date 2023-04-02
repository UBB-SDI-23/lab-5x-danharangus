package com.sdi.lab.model;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class LoyalCustomersKey implements Serializable {
    @Column(name = "dealership_id")
    private Integer dealershipId;

    @Column(name = "customer_id")
    private Integer customerId;

    public Integer getDealershipId() {
        return dealershipId;
    }

    public void setDealershipId(Integer dealershipId) {
        this.dealershipId = dealershipId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoyalCustomersKey that = (LoyalCustomersKey) o;
        return dealershipId.equals(that.dealershipId) && customerId.equals(that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealershipId, customerId);
    }
}
