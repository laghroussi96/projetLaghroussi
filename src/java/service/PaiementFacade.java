/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Admin;
import bean.Compte;
import bean.Freelance;
import bean.Paiement;
import bean.Recruteur;
import controler.util.PdfUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author fatima
 */
@Stateless
public class PaiementFacade extends AbstractFacade<Paiement> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    private FreelanceFacade ejbfaFacade;
    @EJB
    private RecruteurFacade recruteurFacade;
    @EJB
    private AdminFacade adminFacade;
    @EJB
    private CompteFacade compteFacade;

    public int createPaiement(Recruteur recruteur, Admin admin, Double montant, Compte compte) {
        Paiement paiement = new Paiement();
        Double nvSoldeRec = 0.0;
        Double nvSoldeAdmin = 0.0;
//        if(compte.getUser().equals(recruteur.getUser())){
//            nvSoldeRec=compte.getSolde()-montant;
//            compte.setSolde(nvSoldeRec);
//        }
        if (compte.getUser().equals(admin.getUser())) {
            nvSoldeAdmin = compte.getSolde() + montant;
            compte.setSolde(nvSoldeAdmin);
            compteFacade.edit(compte);
        }
        create(paiement);
        return 1;
    }

    public int distMonatant(Paiement paiement) {
        Freelance freelance = ejbfaFacade.find(paiement.getFreelance().getId());
        Double nvSolde = 0.0;
        Double pourcentage = 0.02;
        if (paiement.getCompte().getUser().equals(freelance.getUser())) {
            pourcentage = 0.08;
        }
        Compte compte = compteFacade.find(paiement.getCompte().getId());
        nvSolde = paiement.getMontant() - paiement.getMontant() * pourcentage;
        compte.setSolde(compte.getSolde() + nvSolde);
        compteFacade.edit(compte);
        return 1;
    }

    public PaiementFacade() {
        super(Paiement.class);
    }

    public void generatePdf() throws JRException, IOException {
        Map<String, Object> params = new HashMap();

        params.put("date", "09/04/2018");
        PdfUtil.generatePdf(findAll(), params, "paiements", "/jasper/PaiementJasper.jasper");
    }
}
