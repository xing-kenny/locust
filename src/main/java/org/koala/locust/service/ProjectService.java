package org.koala.locust.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.koala.locust.domain.Project;
import org.koala.locust.repository.ProjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class ProjectService {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @PersistenceContext
    private EntityManager em;

    private ProjectDao projectDao;

    public Project getProject(Long id){
        Project project = projectDao.findOne(id); 
        initActualMandays(project);
        return project;
    }


    public Page<Project> getProjects(int pageNumber, int pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize );
        Page<Project> page = projectDao.findAll(pageRequest);
        
        List<Project> projects = page.getContent();
        for(Project project : projects){
            initActualMandays(project);
        }
        return page;
    }



    @Transactional(readOnly = false)
    public void saveProject(Project entity) {
        projectDao.save(entity);
    }
    
    @Transactional(readOnly = false)
    public void deleteProject(Long id) {
        projectDao.delete(id);
    }
    
    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
    
    private void initActualMandays(Project project) {
        String sqlString = "select actualMandays from v_project where id = ?";
        Query query = em.createNativeQuery(sqlString);
        query.setParameter(1, project.getId());
        project.setActualMandays(Float.valueOf(query.getSingleResult().toString()));
    }

    private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
        Sort sort = new Sort(Direction.ASC, "id");
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

}
