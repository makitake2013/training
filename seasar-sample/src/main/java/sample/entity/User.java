package sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;



@Entity
public class User {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(name="FIRST_NAME")
    public String firstName;

    @Column(name="LAST_NAME")
    public String lastName;

}