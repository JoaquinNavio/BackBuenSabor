package com.entidades.buenSabor.domain.audit;


import com.entidades.buenSabor.domain.config.CustomRevisionListener;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "REVISION_INFO")
@RevisionEntity(CustomRevisionListener.class)
@Getter
@Setter
@ToString
@EqualsAndHashCode
//AGREGAMOS EN TODAS LAS ENTIDADES AUDITABLES @Audited
//En todas las clases auditables, sus relaciones deben serlo a no ser que lo especifiquemos en la relacion
//Con @NotAudited
public class Revision implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//,generator="revision_seq")
    //@SequenceGenerator(name="",sequenceName = "rbac.seq_revision_id")
    @RevisionNumber
    private int id;

    @Column(name = "REVISION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @RevisionTimestamp
    private Date date;
}

