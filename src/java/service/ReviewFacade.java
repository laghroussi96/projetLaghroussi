/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Mission;
import bean.Review;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fatima
 */
@Stateless
public class ReviewFacade extends AbstractFacade<Review> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Review> findByFreelance(Long id){
     return em.createQuery("SELECT rev FROM Review rev WHERE rev.freelance.id='"+id+"'").getResultList();
    }
     public Long calculeScore(Long id){
        return (Long) em.createQuery("SELECT sum(rev.score) FROM Review rev WHERE rev.freelance.id='"+id+"'").getSingleResult();
    }
    
   public Double calculeMoyenScore(Long id){
       
      return (Double) em.createQuery("SELECT AVG(rev.score)FROM Review rev WHERE rev.freelance.id='"+id+"' ").getSingleResult();
       
   }
    public ReviewFacade() {
        super(Review.class);
    }
    
}
