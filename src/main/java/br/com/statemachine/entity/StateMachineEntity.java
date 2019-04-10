package br.com.statemachine.entity;

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
    @Column(name = "id", nullable = false, length = 10)
    private Long id;

    @Column(name = "id_maquina")
    private String idMaquina;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Lob
    @Column(name = "contexto", nullable = false)
    private byte[] contexto;

}
