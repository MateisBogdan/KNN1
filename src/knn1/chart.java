
package knn1;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;



public class chart extends JFrame {
    
    
   
    int min(float[][] totest){
        
        int minim=100;
        for(int j=0;j<totest[0].length;j++){
        for(int i=0;i<totest.length;i++){
        if(minim> totest[i][j] &&  totest[i][j]>1)
            minim=(int) totest[i][j];
        }
                }
       
        if(minim>6){
        minim-=5;
        }
         while(minim %10!=0){
        minim--;
        }
        
        return minim;
}
   int max(float[][] totest){
       
       int maxim=0;
        for(int j=0;j<totest[0].length;j++){
        for(int i=0;i<totest.length;i++){
        if(maxim <  totest[i][j] &&  totest[i][j]>1)
            maxim=(int) totest[i][j];
        }
                }
        while(maxim % 10 !=0){
        maxim++;
        }
        return maxim;
}
  float boundaryL = min(KNN1.acuratii);
  float boundaryU = max(KNN1.acuratii);
  
  double boundariesU[]= new double[5];
  double boundariesL[]=new double[5];
 
  public chart(String Title)
  
  {
        JTabbedPane panes= new JTabbedPane();
        
        JPanel pan = new JPanel();
        JPanel panIris= new JPanel();
        JPanel panDiabetes=new JPanel();
        JPanel panSurvival=new JPanel();
        JPanel panBalance=new JPanel();
        
        pan.setVisible(true);
        pan.setLayout(new BorderLayout());
    
        this.setSize(520,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Acuratete KNN");
    
    JFreeChart chart = ChartFactory.createLineChart(Title,
         "K","Acuratete",
         createdataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
    
    ChartPanel chartp= new ChartPanel(chart);
    chartp.setSize(500,360);
    
    pan.add(chartp);
    
    JFreeChart chartIris = ChartFactory.createLineChart("Kmeans Iris",
         "Iteratii","Eroare",
         createdatasetIris(),
         PlotOrientation.VERTICAL,
         true,true,false);
     
     ChartPanel chartpanIris= new ChartPanel(chartIris);
    chartpanIris.setSize(500,360);
    panIris.add(chartpanIris);
    
    JFreeChart chartDiabetes = ChartFactory.createLineChart("Kmeans Diabetes",
         "Iteratii","Eroare",
         createdatasetDiabetes(),
         PlotOrientation.VERTICAL,
         true,true,false);
     
     ChartPanel chartpanDiabetes= new ChartPanel(chartDiabetes);
    chartpanDiabetes.setSize(500,360);
    panDiabetes.add(chartpanDiabetes);
    
     JFreeChart chartSurvival = ChartFactory.createLineChart("Kmeans Survival",
         "Iteratii","Eroare",
         createdatasetSurvival(),
         PlotOrientation.VERTICAL,
         true,true,false);
     
     ChartPanel chartpanSurvival= new ChartPanel(chartSurvival);
    chartpanSurvival.setSize(500,360);
    panSurvival.add(chartpanSurvival);
    
      JFreeChart chartBalance = ChartFactory.createLineChart("Kmeans Balance",
         "Iteratii","Eroare",
         createdatasetBalance(),
         PlotOrientation.VERTICAL,
         true,true,false);
     
     ChartPanel chartpanBalance= new ChartPanel(chartBalance);
    chartpanBalance.setSize(500,360);
    panBalance.add(chartpanBalance);
    
    
    
    panes.addTab("KNN",null,pan,"Acuratete KNN");
    panes.addTab("Kmeans Iris",null,panIris,"Eroare Iris(artificial)");
    panes.addTab("Kmeans Diabetes",null,panDiabetes,"Eroare Diabetes");
    panes.addTab("Kmeans Survival",null,panSurvival,"Eroare Survival");
    panes.addTab("Kmeans Balance",null,panBalance,"Eroare Balance(artificial)");
    this.add(panes);
    
    
    // seteaza limitele axei OY pentru acuratete
    CategoryPlot x= chart.getCategoryPlot();
    ValueAxis yAxis = x.getRangeAxis();
    yAxis.setRange(boundaryL,boundaryU);
    
    x.getRendererForDataset(x.getDataset(0)).setSeriesPaint(1, Color.BLACK); 
    
   //limitele axei OY pentru erori
   
    for(int i=0;i<5;i++)
  {
    boundariesU[i]=KNN1.erori[i][0]+(KNN1.erori[i][0]*0.05);
  }
     
    for(int i=0;i<5;i++)
  {
      double currentval=0;
      for(int j=0;j<KNN1.erori[0].length;j++){
    currentval=KNN1.erori[i][j]-(KNN1.erori[i][j]*0.05);
    
    if(KNN1.erori[i][j+1]==0){
        boundariesL[i]=currentval;
    break;
    }
      }
  }
  
    CategoryPlot x2= chartIris.getCategoryPlot();
    CategoryPlot x3=chartDiabetes.getCategoryPlot();
    CategoryPlot x4=chartSurvival.getCategoryPlot();
    CategoryPlot x5=chartBalance.getCategoryPlot();
    
    ValueAxis yAxis2 = x2.getRangeAxis();
    ValueAxis yAxis3= x3.getRangeAxis();
    ValueAxis yAxis4= x4.getRangeAxis();
    ValueAxis yAxis5=x5.getRangeAxis();
    
    yAxis2.setRange(boundariesL[0],boundariesU[0]);
    yAxis3.setRange(boundariesL[1],boundariesU[1]);
    yAxis4.setRange(boundariesL[2],boundariesU[2]);
    yAxis5.setRange(boundariesL[3],boundariesU[3]);
    
    LineAndShapeRenderer z2= (LineAndShapeRenderer) x2.getRenderer();
    LineAndShapeRenderer z3= (LineAndShapeRenderer) x3.getRenderer();
    LineAndShapeRenderer z4= (LineAndShapeRenderer) x4.getRenderer();
    LineAndShapeRenderer z5= (LineAndShapeRenderer) x5.getRenderer();
   
    z2.setBaseShapesVisible(true);
    z2.setUseOutlinePaint(true);
    
    z3.setBaseShapesVisible(true);
    z3.setUseOutlinePaint(true);
    
    z4.setBaseShapesVisible(true);
    z4.setUseOutlinePaint(true);
    
    z5.setBaseShapesVisible(true);
    z5.setUseOutlinePaint(true);
   
    //highlight la punctele de pe chart dintr-o serie 
   
    LineAndShapeRenderer z= (LineAndShapeRenderer) x.getRenderer();
    z.setBaseShapesVisible(true);
    z.setUseOutlinePaint(true);
  this.pack();
    }
    
    private DefaultCategoryDataset createdataset()
    
    {
      
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      
      for(int j=0;j<KNN1.numberofsets;j++){
      for(int i=1;i<KNN1.acuratii[0].length;i++){
      dataset.addValue(KNN1.acuratii[j][i] , KNN1.titles[j] , ""+i );
     
      if(KNN1.acuratii[0][i+1]<1)
          break;
      }
      }   
      return dataset;
   
    }

    private DefaultCategoryDataset createdatasetIris(){
 DefaultCategoryDataset erori = new DefaultCategoryDataset();
    
    for(int j=0;j<KNN1.erori[0].length;j++){
      
          
              
    erori.addValue(KNN1.erori[0][j] , "Iris" , ""+j);
    if(KNN1.erori[0][j+1]==0)
        break;
      
}
    return erori;
}
      private DefaultCategoryDataset createdatasetDiabetes(){
 DefaultCategoryDataset erori = new DefaultCategoryDataset();
    
    for(int j=0;j<KNN1.erori[0].length;j++){
      
          
              
    erori.addValue(KNN1.erori[1][j] , "Diabetes" , ""+j);
    if(KNN1.erori[1][j+1]==0)
        break;
      
}
    return erori;
}
     
      private DefaultCategoryDataset createdatasetSurvival(){
 DefaultCategoryDataset erori = new DefaultCategoryDataset();
    
    for(int j=0;j<KNN1.erori[0].length;j++){
      
          
              
    erori.addValue(KNN1.erori[2][j] , "Survival" , ""+j);
    if(KNN1.erori[2][j+1]==0)
        break;
     
}
    return erori;
}
      
      private DefaultCategoryDataset createdatasetBalance(){
 DefaultCategoryDataset erori = new DefaultCategoryDataset();
    
    for(int j=0;j<KNN1.erori[0].length;j++){
      
          
              
    erori.addValue(KNN1.erori[3][j] , "Balance" , ""+j);
    if(KNN1.erori[3][j+1]==0)
        break;
      
}
    return erori;
}

}