/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Pays;
import bean.Recruteur;
import bean.User;
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
public class RecruteurFacade extends AbstractFacade<Recruteur> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecruteurFacade() {
        super(Recruteur.class);
    }
    public List<Recruteur> search(Pays pays,User user) {
        String requette = "SELECT r FROM Recruteur r where 1=1";
       
        if (pays != null) {
            requette += SearchUtil.addConstraint("r", "pays.nom", "=", pays.getNom());
        }
        if (user != null) {
            requette += SearchUtil.addConstraint("r", "user.login", "=", user.getLogin());
        }
        
        return em.createQuery(requette).getResultList();
    }
    public void generatePdf() throws JRException, IOException{
         Map <String,Object> params = new HashMap();
        params.put("date","07/04/2018");
         PdfUtil.generatePdf(findAll(), params,"recruteurs","/jasper/RecruteurJasper.jasper");
     }
 
 public void remov(Long r) {
        super.remove(new Recruteur(r));
    }    
 
 public Long countRecByMonth(int mois) {
        String moisFormate = "";
        if (mois < 10) {
            moisFormate += "0" + mois;
        } else {
            moisFormate = "" + mois;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String annee = simpleDateFormat.format(new Date());
        String requete = "SELECT COUNT(r.id) FROM Recruteur r WHERE r.dateInscription LIKE '" + annee + "-" + moisFormate + "-%' ";
        return (Long) em.createNativeQuery(requete).getSingleResult();

 }
}
