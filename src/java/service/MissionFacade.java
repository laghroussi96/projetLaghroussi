/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.Langue;
import bean.Mission;
import bean.Recruteur;
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
public class MissionFacade extends AbstractFacade<Mission> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MissionFacade() {
        super(Mission.class);
    }

    public List<Mission> search(Recruteur recruteur, Double max, Double min, Langue langue, Categorie categorie, String etat) {
        String requette = "SELECT m FROM Mission m where 1=1";

        requette += SearchUtil.addConstraintMinMax("m", "maxBudget", max, min);
        requette += SearchUtil.addConstraint("m", "avancement", "=", etat);
        if (recruteur != null) {
            requette += SearchUtil.addConstraint("m", "recruteur.nom", "=", recruteur.getNom());
        }
        if (langue != null) {
            requette += SearchUtil.addConstraint("m", "langue.nom", "=", langue.getNom());
        }
        if (categorie != null) {
            requette += SearchUtil.addConstraint("m", "categorie.nom", "=", categorie.getNom());
        }

        return em.createQuery(requette).getResultList();
    }

    public List<Mission> searchMiss(Categorie categorie, Double max, Double min) {

        String requette = "SELECT m FROM Mission m where 1=1";
        requette += SearchUtil.addConstraintMinMax("m", "maxBudget", max, min);
        if (categorie != null) {
            requette += SearchUtil.addConstraint("m", "categorie.nom", "=", categorie.getNom());
        }
        return em.createQuery(requette).getResultList();
    }

    public void generatePdf() throws JRException, IOException {
        Map<String, Object> params = new HashMap();
        params.put("date", "04/04/2018");
        PdfUtil.generatePdf(findByMissionAbouti(), params,"missions", "/jasper/MissionJasper.jasper");
    }
    
    public void generatePdf11() throws JRException, IOException {
        Map<String, Object> params = new HashMap();
        params.put("date", "04/04/2018");
        PdfUtil.generatePdf(findByMission1(), params,"missions", "/jasper/MissionJasper.jasper");
    }

    public List<Mission> findByMissionEnCour() {
        String req = ("select m from Mission m where m.avancement='en cour' and m.isAccepted=true");
        return em.createQuery(req).getResultList();
    }

    public List<Mission> findByMissionAbouti() {
        String reqe = ("select m from Mission m where m.avancement='aboutis' and m.isAccepted=true");
        return em.createQuery(reqe).getResultList();
    }

    public List<Mission> findByMissionNonAbouti() {
        String reqe = ("select m from Mission m where m.avancement='non aboutis' and m.isAccepted=true");
        return em.createQuery(reqe).getResultList();
    }

    public List<Mission> findMission() {
        String reqe = ("select m from Mission m where m.isAccepted=true");
        return em.createQuery(reqe).getResultList();
    }

    public int removeByFreelance(Long r) {
        return em.createQuery("DELETE FROM Mission m WHERE m.freelance.id='" + r + "'").executeUpdate();
    }

    public List<Mission> findByMissionEnCour2() {
        String req = ("select m from Mission m where m.avancement='en cour'");
        return em.createQuery(req).getResultList();
    }
    public List<Mission> findByMission1() {
        String req = ("select m from Mission m where m.isAccepted=true ");
        return em.createQuery(req).getResultList();
    }

    public void remov(Long r) {
        super.remove(new Mission(r));
    }

    public void accepte(Mission item) {
        Mission mission = find(item.getId());
        item.setIsAccepted(true);
        edit(mission);

    }
public Long countMissionAboutis(int mois){
    String moisFormate="";
    if(mois<10){
        moisFormate +="0" + mois;
    }else{
        moisFormate=""+ mois;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
    String annee = simpleDateFormat.format(new Date());
   String requete = "SELECT COUNT(m.id) FROM Mission m WHERE m.datePublication LIKE '" + annee + "-" + moisFormate + "-%' and m.avancement='aboutis'";
        return (Long) em.createNativeQuery(requete).getSingleResult();
    }
               public Long countMissionEncour(int mois) {
        String moisFormate = "";
        if (mois < 10) {
            moisFormate += "0" + mois;
        } else {
            moisFormate = "" + mois;
        }
        SimpleDateFormat maDateLongue = new SimpleDateFormat("yyyy");
        String annee = maDateLongue.format(new Date());
        String requete = "SELECT COUNT(m.id) FROM Mission m WHERE m.datePublication LIKE '" + annee + "-" + moisFormate + "-%' and m.avancement='en cour' ";
        return (Long) em.createNativeQuery(requete).getSingleResult();
    }
               public Long countMissionNonAboutis(int mois) {
        String moisFormate = "";
        if (mois < 10) {
            moisFormate += "0" + mois;
        } else {
            moisFormate = "" + mois;
        }
        SimpleDateFormat maDateLongue = new SimpleDateFormat("yyyy");
        String annee = maDateLongue.format(new Date());
        String requete = "SELECT COUNT(m.id) FROM Mission m WHERE m.datePublication LIKE '" + annee + "-" + moisFormate + "-%' and m.avancement='non aboutis' ";
        return (Long) em.createNativeQuery(requete).getSingleResult();
    }
               public Long countMissonByMonth(int mois) {
        String moisFormate = "";
        if (mois < 10) {
            moisFormate += "0" + mois;
        } else {
            moisFormate = "" + mois;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String annee = simpleDateFormat.format(new Date());
        String requete = "SELECT COUNT(m.id) FROM Mission m WHERE m.datePublication LIKE '" + annee + "-" + moisFormate + "-%' ";
        return (Long) em.createNativeQuery(requete).getSingleResult();

    }


}   



