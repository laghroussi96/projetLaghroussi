package controler;

import bean.Categorie;
import bean.Commentaire;
import bean.Mission;
import bean.Technologie;
import bean.TechnologieMission;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import controler.util.SessionUtil;
import service.TechnologieMissionFacade;

import java.io.Serializable;
import java.util.ArrayList;
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
import service.CommentaireFacade;
import service.MissionFacade;
import service.TechnologieFacade;

@Named("technologieMissionController")
@SessionScoped
public class TechnologieMissionController implements Serializable {

    @EJB
    private service.TechnologieMissionFacade ejbFacade;
    @EJB
    private service.TechnologieFacade ejbTecFacade;
    @EJB
    private service.MissionFacade ejbMesFacade;
    @EJB
    private service.CommentaireFacade commentaireFacade;
    private List<TechnologieMission> items = null;
    private List<TechnologieMission> technologieMissions = new ArrayList<>();
    private List<Technologie> technologies = new ArrayList<>();
    private List<Mission> missions = new ArrayList<>();
    private TechnologieMission selected;
    private Categorie categorie;
    private List<Commentaire> coms = new ArrayList<>();

     public List<Technologie> findByCate() {
        technologies = ejbTecFacade.findByCate(categorie);
        return technologies;
    }
   public String voirMission(Mission mission){
       if(mission!=null){
           SessionUtil.setAttribute("thisMission", ejbMesFacade.find(mission.getId()));
           return "/template/otherPages/MissionItem";
       }
       return null;
   }
    public List<TechnologieMission> findByMissions(Mission mission) {
        technologieMissions = ejbFacade.findByMission(mission.getId());
        return technologieMissions;
    }

    public List<Commentaire> listCommetaire(Mission mission) {
        coms = commentaireFacade.findByMission(mission.getId());
        return coms;
    }
    public TechnologieMissionController() {
    }

    public TechnologieMissionFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(TechnologieMissionFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public TechnologieFacade getEjbTecFacade() {
        return ejbTecFacade;
    }

    public void setEjbTecFacade(TechnologieFacade ejbTecFacade) {
        this.ejbTecFacade = ejbTecFacade;
    }

    public MissionFacade getEjbMesFacade() {
        return ejbMesFacade;
    }

    public void setEjbMesFacade(MissionFacade ejbMesFacade) {
        this.ejbMesFacade = ejbMesFacade;
    }

    public CommentaireFacade getCommentaireFacade() {
        return commentaireFacade;
    }

    public void setCommentaireFacade(CommentaireFacade commentaireFacade) {
        this.commentaireFacade = commentaireFacade;
    }

    public List<TechnologieMission> getTechnologieMissions() {
        return technologieMissions;
    }

    public void setTechnologieMissions(List<TechnologieMission> technologieMissions) {
        this.technologieMissions = technologieMissions;
    }

    public List<Technologie> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technologie> technologies) {
        this.technologies = technologies;
    }

    public List<Mission> getMissions() {
        missions=ejbMesFacade.findAll();
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Commentaire> getComs() {
        return coms;
    }

    public void setComs(List<Commentaire> coms) {
        this.coms = coms;
    }

    public TechnologieMission getSelected() {
        return selected;
    }

    public void setSelected(TechnologieMission selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TechnologieMissionFacade getFacade() {
        return ejbFacade;
    }

    public TechnologieMission prepareCreate() {
        selected = new TechnologieMission();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TechnologieMissionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TechnologieMissionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TechnologieMissionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TechnologieMission> getItems() {
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

    public TechnologieMission getTechnologieMission(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<TechnologieMission> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TechnologieMission> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TechnologieMission.class)
    public static class TechnologieMissionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TechnologieMissionController controller = (TechnologieMissionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "technologieMissionController");
            return controller.getTechnologieMission(getKey(value));
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
            if (object instanceof TechnologieMission) {
                TechnologieMission o = (TechnologieMission) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TechnologieMission.class.getName()});
                return null;
            }
        }

    }

}
