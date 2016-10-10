package me.gking2224.mstemplate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

import me.gking2224.common.web.View.Summary;

@Entity
@Table
public class Thing implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2707606975268392692L;

    private Long id;
    
    private String name;
    
    private String location;
    
    public Thing() {
        super();
    }
    
    public Thing(String name) {
        this.name = name;
    }

    public Thing(long id, String name) {
        this(name);
        this.id = id;
    }


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    @JsonView(Summary.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Transient
    @JsonView(Summary.class)
    public String getLocation(String location) {
        return this.location;
    }

    @Override
    public String toString() {
        return String.format("Thing [id=%s, name=%s]", id, name);
    }
}
