package br.com.statemachine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "PROPOSTA")
public class Proposta {

    private static final String SEQUENCE = "PROPOSTA_SEQ";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_PROPOSTA", nullable = false, precision = 10)
    private Long id;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "PROP1")
    private String prop1;

    @Column(name = "PROP2")
    private String prop2;
}
