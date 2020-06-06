package ru.metro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "turnstile")
public class Turnstile extends BaseEntity{

    @Column(name = "quantity")
    private Integer quantity = 0;

    @Column(name = "enabled")
    private boolean enabled = Boolean.FALSE;

    @Column(name = "update")
    private Date updated;
}
