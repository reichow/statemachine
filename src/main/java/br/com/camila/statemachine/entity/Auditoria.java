package br.com.camila.statemachine.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Builder
@Table(name = "AUDITORIA")
public class Auditoria {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID_AUDITORIA", nullable = false, precision = 10)
    private Long id;

    @Column(name = "NUMERO_PROPOSTA")
    private Long numeroProposta;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "CPF_CLIENTE")
    private String cpf;

    @Column(name = "DIA_HORA")
    private Timestamp diaHora;

    @Column(name = "TIPO_PROPOSTA")
    private String tipoProposta;
}
