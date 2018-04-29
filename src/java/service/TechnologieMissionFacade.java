/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Mission;
import bean.TechnologieMission;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fatima
 */
@Stateless
public class TechnologieMissionFacade extends AbstractFacade<TechnologieMission> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    public List<TechnologieMission> findBytechnologie(Mission mission){
//        return em.createQuery("SELECT tec FROM TechnologieMission tec WHERE tec.mission.id'"+mission.getId()+"'").getResultList();
//    }
    
    public TechnologieMissionFacade() {
        super(TechnologieMission.class);
    }
    public List<TechnologieMission> findByMission(Long id){
        return em.createQuery("SELECT t FROM TechnologieMission t WHERE t.mission.id='"+id+"'").getResultList();
    }
}
