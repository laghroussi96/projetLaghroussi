/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Lenovo
 */
@Stateless
@ViewScoped
public class ChartFacade implements Serializable {

    @EJB
    private service.MissionFacade missionFacade;
        @EJB
    private RecruteurFacade recruteurFacade;
        @EJB
    private FreelanceFacade freelanceFacade;
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private Long maxLine;
    private Long maxBar;
    SimpleDateFormat maDateLongue = new SimpleDateFormat("MM");
    String moisExtrait = maDateLongue.format(new Date());
    int mois = Integer.parseInt(moisExtrait);

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Nombre de : Missions / Freelancers / Recruteurs ");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        Axis xAxis = animatedModel1.getAxis(AxisType.X);
        yAxis.setLabel("valeur");
        yAxis.setMin(0);
        yAxis.setMax(getMaxLine() + 1);
        xAxis.setMin(1);
        xAxis.setMax(12);
        xAxis.setTickInterval("1");
        xAxis.setLabel("mois");

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Missions : Aboutis / Non aboutis / En cour");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        xAxis = animatedModel2.getAxis(AxisType.X);
        yAxis.setLabel("valeur");
        yAxis.setMin(0);
        yAxis.setMax(getMaxBar() + 1);
        xAxis.setMin(1);
        xAxis.setMax(12);
        xAxis.setLabel("mois");
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries bar1 = new ChartSeries();
        ChartSeries bar2 = new ChartSeries();
        ChartSeries bar3 = new ChartSeries();
        bar1.setLabel("aboutis");
        bar2.setLabel("Non aboutis");
        bar3.setLabel("En cour");
        Long maxBars = missionFacade.countMissionEncour(1);

        for (int i = 1; i <= mois; i++) {
            Long nbraboutis = missionFacade.countMissionAboutis(i);
            Long nbrNonAboutis = missionFacade.countMissionNonAboutis(i);
            Long nbrEnCour = missionFacade.countMissionEncour(i);
            bar1.set(i, nbraboutis);
            bar2.set(i, nbrNonAboutis);
            bar3.set(i, nbrEnCour);
            if (nbraboutis > maxBars) {
                maxBars = nbraboutis;
            } else if (nbrNonAboutis > maxBars) {
                maxBars = nbrNonAboutis;
            }else if (nbrEnCour > maxBars) {
                maxBars = nbrEnCour;
            }
        }
        setMaxBar(maxBars);

        model.addSeries(bar1);
        model.addSeries(bar2);
        model.addSeries(bar3);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        series1.setLabel("recruteur");
        series2.setLabel("freelancer");
        series3.setLabel("mission");
        Long maxSerie = missionFacade.countMissonByMonth(1);

        for (int i = 1; i <= mois; i++) {
            Long nbrRec = recruteurFacade.countRecByMonth(i);
            Long nbrFree = freelanceFacade.countFreelancerByMonth(i);
            Long nbrMiss = missionFacade.countMissonByMonth(i);
            series1.set(i, nbrRec);
            series2.set(i, nbrFree);
            series3.set(i, nbrMiss);
            if (nbrRec > maxSerie) {
                maxSerie = nbrRec;
            } else if (nbrFree > maxSerie) {
                maxSerie = nbrFree;
            } else if (nbrMiss > maxSerie) {
                maxSerie = nbrMiss;
            }
        }
        setMaxLine(maxSerie);
        model.addSeries(series1);
        model.addSeries(series2);
        model.addSeries(series3);
        return model;
    }

    public LineChartModel getAnimatedModel1() {
        if (animatedModel1 == null) {
            animatedModel1 = new LineChartModel();
        }
        return animatedModel1;
    }

    public void setAnimatedModel1(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        if (animatedModel2 == null) {
            animatedModel2 = new BarChartModel();
        }
        return animatedModel2;
    }

    public void setAnimatedModel2(BarChartModel animatedModel2) {
        this.animatedModel2 = animatedModel2;
    }

    public Long getMaxLine() {
        return maxLine;
    }

    public void setMaxLine(Long maxLine) {
        this.maxLine = maxLine;
    }

    public Long getMaxBar() {
        return maxBar;
    }

    public void setMaxBar(Long maxBar) {
        this.maxBar = maxBar;
    }

}
