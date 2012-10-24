package org.koala.locust.service;

import java.util.List;

import org.koala.locust.domain.Dept;
import org.koala.locust.repository.DeptDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class DeptService {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(DeptService.class);
    private DeptDao deptDao;

    public Dept getDept(Long id){
        return deptDao.findOne(id);
    }
    
    public List<Dept> getAllDept() {
        return (List<Dept>) deptDao.findAll();
    }

    @Transactional(readOnly = false)
    public void saveDept(Dept entity) {
           deptDao.save(entity);
    }
    
    @Transactional(readOnly = false)
    public void deleteDept(Long id) {
        deptDao.delete(id);
    }
    
    @Autowired
    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }
    
    
}
