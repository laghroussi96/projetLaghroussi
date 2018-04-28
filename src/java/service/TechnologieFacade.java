/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.Technologie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fatima
 */
@Stateless
public class TechnologieFacade extends AbstractFacade<Technologie> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TechnologieFacade() {
        super(Technologie.class);
    }
     public void remov(Long t) {
        super.remove(new Technologie(t));
    }
      public List<Technologie> findByCate(Categorie categorie){
        return em.createQuery("SELECT t FROM Technologie t WHERE t.categorie.id='"+categorie.getId()+"'").getResultList();
    }
}
