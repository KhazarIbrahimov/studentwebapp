package com.freedom.studentwebapp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teacher_university")
public class TeacherUniversity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    @ManyToOne
    private Teacher teacherId;
    @JoinColumn(name = "university_id",referencedColumnName = "id")
    @ManyToOne
    private University universityId;

    public TeacherUniversity(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    public University getUniversityId() {
        return universityId;
    }

    public void setUniversityId(University universityId) {
        this.universityId = universityId;
    }

    @Override
    public int hashCode(){
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object){
        if(!(object instanceof TeacherUniversity)){
            return false;
    }
    TeacherUniversity other = (TeacherUniversity) object;
        if((this.id ==null && other.id != null) || (this.id != null && !this.id.equals(other.id))){
        return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TeacherUniversity{" +
                "id=" + id +
                '}';
    }
}

