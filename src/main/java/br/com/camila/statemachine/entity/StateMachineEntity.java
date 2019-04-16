package br.com.camila.statemachine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "STATEMACHINE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateMachineEntity implements Serializable {

//    private static final String SEQUENCE = "state_machine_seq";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID", nullable = false, length = 10)
    private Long id;

    @Column(name = "NUMERO_PROPOSTA")
    private String idMaquina;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    @Lob
    @Column(name = "CONTEXTO", nullable = false)
    private byte[] contexto;

    @Column(name = "TIPO_PROPOSTA")
    private String tipoProposta;

}
