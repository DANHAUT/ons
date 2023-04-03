package com.edhec.commonedhecapps.service.impl;

import com.edhec.commonedhecapps.model.common.ComContact;
import com.edhec.commonedhecapps.model.common.ComCriteriaLView;
import com.edhec.commonedhecapps.service.CommonStudentAbroadService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

@Service
public class CommonStudentAbroadServiceImpl implements CommonStudentAbroadService {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(CommonStudentAbroadServiceImpl.class);

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public ComContact findContactById(String id) {
        Query query = entityManager.createQuery("select o from ComContact o where o.id = :id").setParameter("id", id);
        ComContact contact = null;

        if(query != null && query.getResultList().size() > 0) {
            contact = (ComContact)query.getSingleResult();
        }
        return contact;
    }

    public List<ComCriteriaLView> findCriteriaByParameters(String crtCkaId, String lngId) {
        Query query = entityManager.createNamedQuery("ComCriteriaLView.findAllByParameters").setParameter("crtCkaId", crtCkaId).setParameter("lngId", lngId);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public String generateId(String genkey) {
        String id = null;
        String dbName = (String)entityManager.createNativeQuery("select value from v$parameter where name = 'db_name'").getSingleResult();
        //String dbName = (String)entityManagerFactory.getSessionFactory().openSession().createSQLQuery("select value from v$parameter where name = 'db_name'").uniqueResult();
        StringBuffer query = new StringBuffer("select '");
        query.append(dbName.toUpperCase());
        query.append("'||ltrim(to_char(");
        query.append(genkey);
        query.append(".nextval,'00000000009')) from dual ");
        List<String> maList = entityManager.createNativeQuery(query.toString()).getResultList();
        for (Iterator<String> iterator = maList.iterator(); iterator.hasNext();) {
            id = (String) iterator.next();
            break;
        }
        return id;
    }
}
