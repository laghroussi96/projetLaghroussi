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

    public int distMonatant(Paiement paiement) {
        Freelance freelance = ejbfaFacade.findByCompte(paiement.getCompte());
        Double nvSolde = 0.0;
        Double pourcentage = 0.02;
        if (paiement.getCompte().equals(freelance.getCompte())) {
            pourcentage = 0.08;
        }
        nvSolde = paiement.getMontant() - paiement.getMontant() * pourcentage;
        paiement.getCompte().setSolde(nvSolde);
        edit(paiement);
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
