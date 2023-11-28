package com.vehicleassistancediary.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "taxes")
public class TaxesEntity extends BaseEntity{
    @ManyToOne
    private TollResponse tollResponse;

    public TollResponse getTollResponse() {
        return tollResponse;
    }

    public void setTollResponse(TollResponse tollResponse) {
        this.tollResponse = tollResponse;
    }
}
