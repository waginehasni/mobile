/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.InfiniteProgress;
import com.codename1.services.ServiceReclamation;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.esprit.pidev.entities.Reclamation;
import java.util.ArrayList;

/**
 *
 * @author ASuS
 */
public class StatForm extends Form {
    ArrayList<Reclamation> data = new ArrayList<>();
    ArrayList<Reclamation> recActifs = new ArrayList<>();
    ArrayList<Reclamation> recClotures = new ArrayList<>();
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
    protected CategorySeries buildCategoryDataset(String title, ArrayList<Reclamation> data) {
    CategorySeries series = new CategorySeries(title);
    data = ServiceReclamation.getInstance().affichageReclamations();
    int pt=0;
    int pc=0;
    
        pt= ServiceReclamation.getInstance().getReclamationActif().size();
        pc= ServiceReclamation.getInstance().getReclamationCloture().size();
        
        
        series.add("Actifs ", pt);
        series.add("cloturées", pc);
        
    return series;
}

    public StatForm(Form previous) {
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.CYAN, ColorUtil.MAGENTA};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setLabelsTextSize(16);
    renderer.setLegendTextSize(16);
    renderer.setChartTitle("Etat Des Réclamations");
    renderer.setLabelsColor(12345);
    renderer.setBackgroundColor(0xFF00FF00);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.CYAN);
    r.setGradientStop(0, ColorUtil.MAGENTA);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Nombre de réclamation par état", data), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    add(c);
    
    Button btnRetour  = new Button("<< Retour");
        addStringValue("", btnRetour);
        btnRetour.addActionListener(e-> previous.showBack());
    Button btnPdf  = new Button("Télécharger PDF");
        addStringValue("", btnPdf);
        btnPdf.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress(); //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    ServiceReclamation.getInstance().exportPDF();
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    refreshTheme();//Actualisation
        });
        
    this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
}
    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
     public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}