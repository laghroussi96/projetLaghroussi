package controler;

import bean.Categorie;
import bean.Commentaire;
import bean.Langue;
import bean.Mission;
import bean.Recruteur;
import bean.TechnologieMission;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import controler.util.SessionUtil;
import java.io.IOException;
import service.MissionFacade;

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
import net.sf.jasperreports.engine.JRException;
import service.CommentaireFacade;

@Named("missionController")
@SessionScoped
public class MissionController implements Serializable {

    @EJB
    private service.MissionFacade ejbFacade;
    
    @EJB
    private CommentaireFacade commentaireFacade;
    private List<Mission> items = null;
    private List<Mission> itemsEnCour = null;
    private List<Mission> itemsEnCour2 = null;
    private List<Mission> itemsAboutis = null;
    private List<Mission> itemsNonAboutis = null;
    private List<Mission> itemsMission = null;
    private List<Mission> listMiss ;
    private List<Mission> itemsMission1 = null;
    private List<Mission> itemss = null;
    private Mission selected;
    private Commentaire selectedCommentaire;
    private List<Commentaire> coms;
    private List<Commentaire> commentaires;
    private List<Commentaire> commentForMission=new ArrayList<>();
    private List<TechnologieMission> listTechnologie;
    // private Mission selected2;
    // private Mission mission = new Mission();
    //private  List<Mission> missionItems = new ArrayList<>();
    //private BarChartModel model;  
    private Recruteur recruteur;
    private Recruteur voirRecruteur;
    private Double max;
    private Double min;
    private Langue langue;
    private Categorie categorie;
    private String etat;
    private Mission voirMission;

     public String sesMission(Mission mission){
       if(mission!=null){
           SessionUtil.setAttribute("thisMission", ejbFacade.find(mission.getId()));
           return "/template/otherPages/MissionItem_1";
       }
       return null;
   }
     public List<Commentaire> comments(){
        commentaires=commentaireFacade.findByMission(voirMission.getId());
        return commentaires;
    }
    public List<Mission> getItemss() {
        return itemss;
    }

    public void setItemss(List<Mission> itemss) {
        this.itemss = itemss;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    
    public Recruteur getVoirRecruteur() {
        voirRecruteur=(Recruteur)SessionUtil.getAttribute("thisRecruteur");
        return voirRecruteur;
    }


    public void setVoirRecruteur(Recruteur voirRecruteur) {
        this.voirRecruteur = voirRecruteur;
    }
   
    
    public List<Commentaire> listCommetaireForMission() {
       commentForMission=commentaireFacade.findByMission(voirMission.getId());
        return commentForMission;
    }
    public MissionController(MissionFacade ejbFacade, Mission selected) {
        this.ejbFacade = ejbFacade;
        this.selected = selected;
    }

    public List<Mission> getListMiss() {
        listMiss=ejbFacade.findByRecruteur(getVoirRecruteur());
        return listMiss;
    }

    public void setListMiss(List<Mission> listMiss) {
        this.listMiss = listMiss;
    }

    
    public List<Commentaire> getCommentForMission() {
        return commentForMission;
    }

    public void setCommentForMission(List<Commentaire> commentForMission) {
        this.commentForMission = commentForMission;
    }

    public Mission getVoirMission() {
          voirMission=(Mission)SessionUtil.getAttribute("thisMission");
        return voirMission;
    }

    public void setVoirMission(Mission voirMission) {
        this.voirMission = voirMission;
    }

    public List<Mission> getItemsMission1() {
        return itemsMission1;
    }

    public void setItemsMission1(List<Mission> itemsMission1) {
        this.itemsMission1 = itemsMission1;
    }

    public List<TechnologieMission> getListTechnologie() {
        return listTechnologie;
    }

    public void setListTechnologie(List<TechnologieMission> listTechnologie) {
        this.listTechnologie = listTechnologie;
    }


//    @EJB
//    MissionFacade missionFacade; 
//    public Mission getSelected2() {
//        return selected2;
//    }
//
//    public void setSelected2(Mission selected2) {
//        this.selected2 = selected2;
//    }
//
//    public Mission getMission() {
//        return mission;
//    }
//
//    public void setMission(Mission mission) {
//        this.mission = mission;
//    }
//
//    public List<Mission> getMissionItems() {
//        return missionItems;
//    }
//
//    public void setMissionItems(List<Mission> missionItems) {
//        this.missionItems = missionItems;
//    }
//
//    public BarChartModel getModel() {
//        return model;
//    }
//
//    public void setModel(BarChartModel model) {
//        this.model = model;
//    }
//    public MissionFacade getMissionFacade() {
//        return missionFacade;
//    }
//
//    public void setMissionFacade(MissionFacade missionFacade) {
//        this.missionFacade = missionFacade;
//    }
    public void findByMission(Mission mission) {
        getSelected().setCommentaires(commentaireFacade.findByMission(mission.getId()));
    }

//    public List<TechnologieMission> getListTechnologie() {
//        listTechnologie=technologieMissionFacade.findBytechnologie(selected);
//        return listTechnologie;
//    }
//    public void setListTechnologie(List<TechnologieMission> listTechnologie) {
//        this.listTechnologie = listTechnologie;
//    }
//
//    public TechnologieMissionFacade getTechnologieMissionFacade() {
//        return technologieMissionFacade;
//    }
//
//    public void setTechnologieMissionFacade(TechnologieMissionFacade technologieMissionFacade) {
//        this.technologieMissionFacade = technologieMissionFacade;
//    }
    public List<Commentaire> listCommetaire(Mission mission) {
        coms = commentaireFacade.findByMission(mission.getId());
        return coms;
    }

    public CommentaireFacade getCommentaireFacade() {
        return commentaireFacade;
    }

    public void setCommentaireFacade(CommentaireFacade commentaireFacade) {
        this.commentaireFacade = commentaireFacade;
    }

    public Commentaire getSelectedCommentaire() {
        System.out.println("selected");
        if (selectedCommentaire == null) {
            selectedCommentaire = new Commentaire();
        }
        return selectedCommentaire;
    }

    public void setSelectedCommentaire(Commentaire selectedCommentaire) {
        this.selectedCommentaire = selectedCommentaire;
    }

    public List<Commentaire> getComs() {
        return coms;
    }

    public void setComs(List<Commentaire> coms) {
        this.coms = coms;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public MissionFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(MissionFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Recruteur getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
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

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public MissionController() {
    }

    public Mission getSelected() {
        if (selected == null) {
            selected = new Mission();
        }
        return selected;
    }

    public void setSelected(Mission selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MissionFacade getFacade() {
        return ejbFacade;
    }

    public Mission prepareCreate() {
        selected = new Mission();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        System.out.println("create");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MissionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MissionUpdated"));
    }

    public void search() {
        items = ejbFacade.search(recruteur, max, min, langue, categorie, etat);
    }

    public void searchMiss() {
        items = ejbFacade.searchMiss(categorie, max, min);
    }

    public void generatePdf() throws JRException, IOException {
        ejbFacade.generatePdf();
        FacesContext.getCurrentInstance().responseComplete();
    }
    public void generatePdf1() throws JRException, IOException {
        ejbFacade.generatePdf11();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void accepter(Mission item) {
        ejbFacade.accepte(item);
     
       
    }

//   @PostConstruct
//    public void init() {
//        createAnimatedModels();
//    }
//
//
//
//    public void createAnimatedModels() {
//        model = chartsMissionsByAvancement();
//        model.setTitle("Missions by avancement");
//        model.setAnimate(true);
//        Axis yAxis = model.getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(100);
//    }
//    
//        public BarChartModel chartsMissionsByAvancement() {
//        BarChartModel barChartModel = new BarChartModel();
//        ChartSeries clients = new ChartSeries();
//        clients.setLabel("avancement");
//        for (int i = 0; i < missionFacade.findAll().size(); i++) {
//            clients.set(missionFacade.findAll().get(i).getAvancement(), ejbFacade.findByAvancement(missionFacade.findAll().get(i)));
//        }
//        barChartModel.addSeries(clients);
//        return barChartModel;
//    }
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MissionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Mission> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Mission> getItemsEnCour() {
        if (itemsEnCour == null) {
            itemsEnCour = getFacade().findByMissionEnCour();
        }
        return itemsEnCour;
    }

    public List<Mission> getItemsEnCour2() {
        if (itemsEnCour2 == null) {
            itemsEnCour2 = getFacade().findByMissionEnCour2();
        }
        return itemsEnCour2;
    }

    public List<Mission> getItemsAboutis() {
        if (itemsAboutis == null) {
            itemsAboutis = getFacade().findByMissionAbouti();
        }
        return itemsAboutis;
    }

    public List<Mission> getItemsNonAboutis() {
        if (itemsNonAboutis == null) {
            itemsNonAboutis = getFacade().findByMissionNonAbouti();
        }
        return itemsNonAboutis;
    }

    public List<Mission> getItemsMission() {
        if (itemsMission == null) {
            itemsMission = getFacade().findMission();
        }
        return itemsMission;
    }
    public List<Mission> getItemsMissions() {
        if (itemsMission1 == null) {
            itemsMission1 = getFacade().findByMission1();
        }
        return itemsMission1;
    }

    public void remove(Mission item) {
        ejbFacade.remov(item.getId());
        itemsAboutis.remove(itemsAboutis.indexOf(item));

    }

    public void removee(Mission item) {
        ejbFacade.remov(item.getId());
        itemsEnCour2.remove(itemsEnCour2.indexOf(item));

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

    public Mission getMission(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Mission> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Mission> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Mission.class)
    public static class MissionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MissionController controller = (MissionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "missionController");
            return controller.getMission(getKey(value));
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
            if (object instanceof Mission) {
                Mission o = (Mission) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mission.class.getName()});
                return null;
            }
        }

    }

    public void jhgj() {

    }
}
