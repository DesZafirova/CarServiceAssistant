package com.vehicleassistancediary.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "taxes")
public class TaxesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @ManyToOne
    private TollResponse tollResponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TollResponse getTollResponse() {
        return tollResponse;
    }

    public void setTollResponse(TollResponse tollResponse) {
        this.tollResponse = tollResponse;
    }
}
