package controler;

import bean.Freelance;
import bean.Pays;
import bean.Review;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import java.io.IOException;
import service.FreelanceFacade;

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
import service.MissionFacade;
import service.ReviewFacade;

@Named("freelanceController")
@SessionScoped
public class FreelanceController implements Serializable {

    @EJB
    private service.FreelanceFacade ejbFacade;
    @EJB
    private ReviewFacade reviewFacade;
    private List<Freelance> items = null;
       private List<Freelance> itemss = null;
    private Freelance selected;
    private Review selectedReview;
    private Long somScroe;
    private Double vote;
     @EJB
    private FreelanceFacade ejbFacadee;
     @EJB
    private MissionFacade missionFacade;
   
 private Pays pays;
    private Double max; 
    private Double min;
    private String name;

    public List<Freelance> getItemss() {
        return itemss;
    }

    public void setItemss(List<Freelance> itemss) {
        this.itemss = itemss;
    }

    public FreelanceFacade getEjbFacadee() {
        return ejbFacadee;
    }

    public void setEjbFacadee(FreelanceFacade ejbFacadee) {
        this.ejbFacadee = ejbFacadee;
    }

     public Long calculeScore(Freelance freelance){
        somScroe=reviewFacade.calculeScore(freelance.getId());
        return somScroe;
    }
    public void findByFreelance(Freelance freelance){
        getSelected().setReviews(reviewFacade.findByFreelance(freelance.getId()));
    }
    public Double calculeMoyenScore(Freelance freelance){
        vote=reviewFacade.calculeMoyenScore(freelance.getId());
        return vote;
    }

    public ReviewFacade getReviewFacade() {
        return reviewFacade;
    }

    public void setReviewFacade(ReviewFacade reviewFacade) {
        this.reviewFacade = reviewFacade;
    }
    

    public Review getSelectedReview() {
        if(selectedReview==null){
            selectedReview=new Review();
        }
        return selectedReview;
    }

    public void setSelectedReview(Review selectedReview) {
        this.selectedReview = selectedReview;
    }

    public Long getSomScroe() {
        return somScroe;
    }

    public void setSomScroe(Long somScroe) {
        this.somScroe = somScroe;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    
    public FreelanceFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(FreelanceFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FreelanceController() {
    }

    public Freelance getSelected() {
        if(selected==null){
            selected=new Freelance();
                    
        }
        return selected;
    }

    public void setSelected(Freelance selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FreelanceFacade getFacade() {
        return ejbFacade;
    }

    public Freelance prepareCreate() {
        selected = new Freelance();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void test(){
        System.out.println("selected "+selected);
    }

    public void create() {
        System.out.println("create");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FreelanceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FreelanceUpdated"));
    }
 public void search() {
      items = ejbFacade.search(pays, max, min, name);
    }
 public void detail(Freelance item){
    getSelected().setMissions(missionFacade.findByFreelance(item.getId()));
}
 public void generatePdf() throws JRException, IOException{
      ejbFacade.generatePdf();
      FacesContext.getCurrentInstance().responseComplete();
  }
 
  public void remove(Freelance item) {
          ejbFacade.remov(item.getId());
    items.remove(items.indexOf(item));
      
    }
 
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FreelanceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Freelance> getItems() {
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
                    System.out.println("selected "+selected);
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

    public Freelance getFreelance(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Freelance> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Freelance> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Freelance.class)
    public static class FreelanceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FreelanceController controller = (FreelanceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "freelanceController");
            return controller.getFreelance(getKey(value));
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
            if (object instanceof Freelance) {
                Freelance o = (Freelance) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Freelance.class.getName()});
                return null;
            }
        }

    }

}
