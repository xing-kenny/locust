package org.koala.locust.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.koala.locust.entity.IdEntity;

/**
 * 
 * @author Xing Kenny
 *
 */
@Entity
@Table(name = "t_dept")
public class Dept extends IdEntity {

    private String name;

    @Column(name="sname", nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
