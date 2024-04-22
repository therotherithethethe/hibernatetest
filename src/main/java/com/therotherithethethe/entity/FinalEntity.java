package com.therotherithethethe.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Getter;

public interface FinalEntity<T> {
    T getId();
    void setId(T id);
}
