/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import bean.Mission;
import bean.User;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;

@Named("chartController")
@SessionScoped
@ViewScoped
public class ChartController implements Serializable {

    @EJB
    private service.ChartFacade chartFacade;

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;

    public ChartController() {
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    @FacesConverter(forClass = User.class)
    public static class MissionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MissionController controller = (MissionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "missionController");
            return controller.getMission(Long.valueOf(getKey(value)));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
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
                return getStringKey(o.getId()+"");
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mission.class.getName()});
                return null;
            }
        }

    }

    public LineChartModel getAnimatedModel1() {
        if (animatedModel1 == null) {
            animatedModel1 = new LineChartModel();
        }
        return chartFacade.getAnimatedModel1();
    }

    public void setAnimatedModel1(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        if (animatedModel2 == null) {
            animatedModel2 = new BarChartModel();
        }
        return chartFacade.getAnimatedModel2();
    }

    public void setAnimatedModel2(BarChartModel animatedModel2) {
        this.animatedModel2 = animatedModel2;
    }

}
