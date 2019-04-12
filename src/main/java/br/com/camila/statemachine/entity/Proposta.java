package br.com.camila.statemachine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "PROPOSTA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposta {

    private static final String SEQUENCE = "PROPOSTA_SEQ";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_PROPOSTA", nullable = false, precision = 10)
    private Long id;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "NUMERO")
    private Long numero;

    @Column(name = "CPF")
    private String cpf;
}

