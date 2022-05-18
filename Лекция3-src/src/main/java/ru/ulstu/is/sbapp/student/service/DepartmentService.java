package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DepartmentService {
    @PersistenceContext
    private EntityManager em;

    /**
     * hasText(CharSequence str)
     Проверьте CharSequence, содержит ли данный текст фактический текст.
     * final - предотвращает наследование.
     * em - Entity Manager
     * При использовании merge вместо persist: Hibernate выполнит SELECT выражение для того чтобы убедиться
     что в БД не существует записи с таким же идентификатором.
     Для новых сущностей, всегда нужно использовать persist, а для detached сущностей нужно вызывать merge
     * */
    @Transactional
    public Department addDepartment(String DepartmentName, String Login, String Password) {
        if(!StringUtils.hasText(DepartmentName) || !StringUtils.hasText(Login) || !StringUtils.hasText(Password)){
            throw new IllegalArgumentException("Department name/Login/Password is null or empty");
        }
        final Department department = new Department(DepartmentName, Login, Password);
        em.persist(department);
        return department;
    }

    @Transactional(readOnly = true)
    public Department findDepartment(Long id) {
        final Department department = em.find(Department.class, id);
        if (department == null) {
            throw new EntityNotFoundException(String.format("Department with id [%s] is not found", id));
        }
        return department;
    }

    @Transactional(readOnly = true)
    public List<Department> findAllDepartments() {
        return em.createQuery("select s from Department s", Department.class)
                .getResultList();
    }

    @Transactional
    public Department updateDepartment(Long id, String DepartmentName, String Login, String Password) {
        if (!StringUtils.hasText(DepartmentName) || !StringUtils.hasText(Login) || !StringUtils.hasText(Password)) {
            throw new IllegalArgumentException("Department name/Login/Password is null or empty");
        }
        final Department currentDepartment = findDepartment(id);
        currentDepartment.setDepartmentName(DepartmentName);
        currentDepartment.setLogin(Login);
        currentDepartment.setPassword(Password);
        return em.merge(currentDepartment);
    }

    @Transactional
    public Department deleteDepartment(Long id) {
        final Department currentDepartment = findDepartment(id);
        em.remove(currentDepartment);
        return currentDepartment;
    }

    @Transactional
    public void deleteAllDepartments() {
        em.createQuery("delete from Department").executeUpdate();
    }
}
