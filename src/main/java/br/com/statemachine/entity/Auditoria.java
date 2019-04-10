package br.com.statemachine.entity;

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

//    private static final String SEQUENCE = "AUDITORIA_SEQ";

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

    @Column(name = "DATA_AUD")
    private String data;

    @Column(name = "DURACAO")
    private String duracao;
}
