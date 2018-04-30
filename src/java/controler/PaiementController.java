package controler;

import bean.Admin;
import bean.Compte;
import bean.Paiement;
import bean.Recruteur;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import java.io.IOException;
import service.PaiementFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import net.sf.jasperreports.engine.JRException;

@Named("paiementController")
@SessionScoped
public class PaiementController implements Serializable {

    @EJB
    private service.PaiementFacade ejbFacade;
    private List<Paiement> items = null;
    private Paiement selected;
    private Recruteur recruteur;
    private Admin admin;
    private Double montant;
    private Compte compte;

   
 public void distMontant(){
     System.out.println("effectuer");
     ejbFacade.distMonatant(selected);
     JsfUtil.addSuccessMessage("paiement effectuer");
     
 }
    public PaiementFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PaiementFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Recruteur getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    
    public PaiementController() {
    }

    public Paiement getSelected() {
        System.out.println("selected");
        if(selected==null){
            selected=new Paiement();
        }
        return selected;
    }

    public void setSelected(Paiement selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PaiementFacade getFacade() {
        return ejbFacade;
    }

    public Paiement prepareCreate() {
        selected = new Paiement();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        System.out.println("create");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PaiementCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PaiementUpdated"));
    }

    public void generatePdf() throws JRException, IOException {
        ejbFacade.generatePdf();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PaiementDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Paiement> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Paiement getPaiement(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Paiement> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Paiement> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Paiement.class)
    public static class PaiementControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaiementController controller = (PaiementController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paiementController");
            return controller.getPaiement(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Paiement) {
                Paiement o = (Paiement) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Paiement.class.getName()});
                return null;
            }
        }

    }

}
