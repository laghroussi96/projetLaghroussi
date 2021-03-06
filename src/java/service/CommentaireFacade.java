/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commentaire;
import bean.Freelance;
import bean.Mission;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fatima
 */
@Stateless
public class CommentaireFacade extends AbstractFacade<Commentaire> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
     public void createCom(Mission mission,String text,Freelance freelance){
        Commentaire commentaire=new Commentaire();
        commentaire.setMission(new Mission(mission.getId()));
        //commentaire.setFreelance(new Freelance(freelance.getId()));
        commentaire.setText(text);
        System.out.println("createcom facade");
        create(commentaire);
    }
    
    public List<Commentaire> findByMission(Long numero){
        return em.createQuery("SELECT com FROM Commentaire com WHERE com.mission.id='"+numero+"'").getResultList();
    }

    public CommentaireFacade() {
        super(Commentaire.class);
    }
    
}
