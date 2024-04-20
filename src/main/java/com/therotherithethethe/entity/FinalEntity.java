package com.therotherithethethe.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Getter;

@Getter
public abstract class FinalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

}
