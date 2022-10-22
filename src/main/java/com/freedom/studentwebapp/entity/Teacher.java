package com.freedom.studentwebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "teacher")
@NamedQueries({
    @NamedQuery(name = "idyeGoreTap", query = "SELECT m FROM Teacher m")})
public class Teacher implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "salary")
    private BigDecimal salary;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    @ManyToOne
    private University universityId;
    @JsonIgnore
    @OneToMany(mappedBy = "teacherId")
    private List<TeacherUniversity> teacherUniversityList;


    public Teacher() {
    }

    public Teacher(Integer id) {
        this.id = id;
    }

    public Teacher(Integer id, String name, String surname, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public Teacher setId(final Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Teacher setName(final String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Teacher setSurname(final String surname) {
        this.surname = surname;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Teacher setSalary(final BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Teacher setCreatedOn(final Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public Teacher setUpdatedOn(final Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public University getUniversityId() {
        return universityId;
    }

    public Teacher setUniversityId(final University universityId) {
        this.universityId = universityId;
        return this;
    }

    public List<TeacherUniversity> getTeacherUniversityList(){return teacherUniversityList;}

    public void setTeacherUniversityList(List<TeacherUniversity> teacherUniversityList) {
        this.teacherUniversityList = teacherUniversityList;
    }
}
