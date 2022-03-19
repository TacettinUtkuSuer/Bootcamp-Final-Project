package com.example.emlakburada.model.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "info_table")
@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name="key_column")
    private String key;
    @Column(name="value_column")
    private String value;

    public Info(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
