/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Freelance;
import bean.Pays;
import controler.util.PdfUtil;
import controler.util.SearchUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author fatima
 */
@Stateless
public class FreelanceFacade extends AbstractFacade<Freelance> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;
    private Pays pays ;
    private FreelanceFacade freelanceFacade;
    private MissionFacade missionFacade;

    public MissionFacade getMissionFacade() {
        return missionFacade;
    }

    public void setMissionFacade(MissionFacade missionFacade) {
        this.missionFacade = missionFacade;
    }
    

    public FreelanceFacade getFreelanceFacade() {
        return freelanceFacade;
    }

    public void setFreelanceFacade(FreelanceFacade freelanceFacade) {
        this.freelanceFacade = freelanceFacade;
    }
    

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FreelanceFacade() {
        super(Freelance.class);
    }
    
      public List<Freelance> search(Pays pays,  Double max, Double min, String name) {
        String requette = "SELECT f FROM Freelance f where 1=1";
       
        requette += SearchUtil.addConstraintMinMax("f", "tarif", max, min);
        if (pays != null) {
            requette += SearchUtil.addConstraint("f", "pays.nom", "=", pays.getNom());
        }
        requette += SearchUtil.addConstraint("f", "nom", "=", name);
       

        return em.createQuery(requette).getResultList();
    }
       public void generatePdf() throws JRException, IOException{
         Map <String,Object> params = new HashMap();
         PdfUtil.generatePdf(findAll(), params,"recruteurs","/jasper/FreelancerJasper.jasper");
     }
       
        public void remov(Long f) {
        super.remove(new Freelance(f));
    }
        public Long countFreelancerByMonth(int mois) {
        String moisFormate = "";
        if (mois < 10) {
            moisFormate += "0" + mois;
        } else {
            moisFormate = "" + mois;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String annee = simpleDateFormat.format(new Date());
        String requete = "SELECT COUNT(f.id) FROM Freelance f WHERE f.dateInscription LIKE '" + annee + "-" + moisFormate + "-%' ";
        return (Long) em.createNativeQuery(requete).getSingleResult();

    }

}
