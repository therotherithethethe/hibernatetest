package com.therotherithethethe.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.HashSet;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public final class Recipe extends FinalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @ElementCollection
    private HashSet<Resource> ingredients = new HashSet<>();
    private String name;
}
