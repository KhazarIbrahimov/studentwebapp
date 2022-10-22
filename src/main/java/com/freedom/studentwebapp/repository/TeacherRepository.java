package com.freedom.studentwebapp.repository;

import com.freedom.studentwebapp.entity.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class TeacherRepository extends Repository<Teacher> {

    @Override
    public List<Teacher> getList() {
        final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        final EntityManager em = emf.createEntityManager();
        final Query sqlQuery = em.createQuery("select m from Teacher m", Teacher.class);

        final List<Teacher> resultList = sqlQuery.getResultList();

        em.close();
        return resultList;
    }

    public List getList(String name, String surname, BigDecimal salary) {
            String sql = "select * from teacher where ";
            if(name!=null && name.trim().length()>0){
                sql += "name like :name and ";
            }
            if(surname!=null && surname.trim().length()>0){
                sql += "surname like :surname and ";
            }
            if(salary!=null){
                sql += "salary=:salary and ";
            }
            sql = sql.substring(0,sql.length()-5);

            final EntityManagerFactory sessionFactory = JpaUtil.getEntityManagerFactory();
            final EntityManager currentSession = sessionFactory.createEntityManager();
            final Query sqlQuery = currentSession.createNativeQuery(sql, Teacher.class);
            if(name!=null && name.trim().length()>0) {
                sqlQuery.setParameter("name", "%" + name + "%");
            }

            if(surname!=null && surname.trim().length()>0) {
                sqlQuery.setParameter("surname", "%" + surname + "%");
            }

            if(salary!=null) {
                sqlQuery.setParameter("salary", "%" + salary + "%");
            }

        return sqlQuery.getResultList();
    }

    @Override
    public Teacher getById(Integer id)  {
        final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        final EntityManager em = emf.createEntityManager();

        final Teacher teacher = em.createNamedQuery("idyeGoreTap", Teacher.class).getSingleResult();

        em.close();

        return teacher;
    }

    @Override
    public Teacher insert(Teacher teacher) {
        final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();

        em.close();
        return teacher;
    }

    @Override
    public boolean update(Teacher teacher) {
        final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        EntityManager em = null;
       try{
           em = emf.createEntityManager();
           em.getTransaction().begin();
           em.merge(teacher);
           em.getTransaction().commit();
       }catch (Exception e) {
           if(em!=null) {
               em.getTransaction().rollback();
           }
       }finally {
         em.close();
       }
        return true;
    }

    @Override
    public boolean delete(Integer id){
        final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        final Teacher teacher = em.find(Teacher.class, id);
        em.remove(teacher);
        em.getTransaction().commit();

        em.close();

        return true;
    }
}
