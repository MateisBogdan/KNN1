    package knn1;


import java.util.Arrays;



public  class kmeans1 extends Thread  { 
     
    static int K;
    
  
    
   static public double[][] concat(double[][] a, double[][] b) {
   
       int aLen = a.length;
   int bLen = b.length;
   int aWid = a[0].length;
   double[][] con= new double[aLen+bLen][aWid];
   System.arraycopy(a, 0, con, 0, aLen);
   System.arraycopy(b, 0, con, aLen, bLen);
   return con;

   }
    
static  double[] min(double[][] totest){
       
        double[] minim=new double[totest[0].length];
        for(int i=0;i<minim.length;i++)
            minim[i]=totest[0][i];
     
        for(int i=0;i<totest[0].length-1;i++){
        for(int j=0;j<totest.length;j++){
        if(minim[i]> totest[j][i] &&  totest[j][i]>0)
            minim[i]= totest[j][i];
        }
                }
                       
        return minim;
}
   static double[] max(double[][] totest){
       
       double[] maxim=new double[totest[0].length];
       for(int i =0;i<maxim.length;i++)
           maxim[i]=totest[0][i];
        for(int j=0;j<totest.length;j++)
        {
        for(int i=0;i<totest[0].length-1;i++)
        {
        if(maxim[i] <  totest[j][i] &&  totest[j][i]>0)
            maxim[i]= totest[j][i];
        }
          }
        return maxim;
}
   
  
  static double[][] initializare(){
      int m=data.datetest[0].length;
      double[][] date= concat(data.datetest,data.querytest ); 
      double[][] start= new double[K][m];
        
      double[]maximums= max(date);
      double[]minimums= min(date);
      
     
       
       for(int i =0;i<K;i++){
           for(int j=0;j<start[0].length-1;j++)
           start[i][j]=minimums[j]+Math.random()*(maximums[j]-minimums[j]);
       }
       return start;
   }
 static double[][] assign(double[][] centroids){
    
      int f = centroids.length;
      int ck= centroids[0].length;
      
      int m=data.datetest[0].length;
      double[][] date = concat(data.datetest,data.querytest );
      int i,j,l;
      double[][] cluster= new double[f][ck];
      for(i=0;i<f;i++)
          for(j=0;j<ck;j++)
              cluster[i][j]=centroids[i][j];
      
      double[][] distante=new double[date.length][cluster.length]; 
      double[][] clasificare=new double[distante.length][2];
     
       for( i=0;i<K;i++){
          
          cluster[i][m-1]=i;
      }
      
      
      for(l=0;l<cluster.length;l++){
      for(i=0;i<date.length;i++){
           double suma=0;
           
      for(j=0;j<cluster[0].length-1;j++)
      {
         
          suma+=  Math.pow(date[i][j]-cluster[l][j],2.0); 
      }
        distante[i][l]=Math.sqrt(suma); 
        
      }
      }
           
      for(i=0;i<distante.length;i++)
           clasificare[i][0]=distante[i][0]; 
               
       for(i=0;i<distante.length;i++){
       for(j=0;j<distante[0].length;j++){
           if(clasificare[i][0]>distante[i][j]){
               
               clasificare[i][0]=distante[i][j];// clasifica elementul din poz
                clasificare[i][1]=j;// resp in functie de cea mai mica distanta fata de centroid
           }
           
           }
       
       }
       
      return clasificare;
      
      
  }
 
 
 static double[][] average(double[][]clustere){
   int m=data.datetest[0].length;
        double[][] date = concat(data.datetest,data.querytest);
        double[][] atrib= clustere;
        for(int i=0;i<date.length;i++){
        date[i][m-1]=atrib[i][1];
        }
         double med[][]=new double[K][m];
    
           double[] counter=new double[K]; 
   
        for(int k=0;k<med.length;k++){
            counter[k]=0;
            med[k][m-1]=k;
         for(int i=0;i<date.length;i++){
        if(date[i][m-1]==k){
             counter[k]++;
           for(int j =0;j<m-1;j++){   
    
        
        med[k][j]+=date[i][j];
           
        }
       }
         }
        }
    for(int i=0;i<med.length;i++)    
    for(int j =0;j<med[0].length-1;j++)
       {
       med[i][j]=med[i][j]/counter[i];
       }
  

        return med;
    
    }
 
 
 static double calculeroare(double [][]classifier,double [][]cluster) {
    
         int m=data.datetest[0].length;  
        
        double date[][]=concat(data.datetest,data.querytest);
        double clas[][]=classifier;
        double suma=0;
        double err=0;
       
        int i,j;
        for(i=0;i<date.length;i++){
        date[i][m-1]=clas[i][1];
        }
       
       
        for(i=0;i<date.length-1;i++){
            suma =0;
            int curent =(int)date[i][m-1];
            for(j=0;j<date[0].length-1;j++){
               
                suma+=Math.pow(date[i][j]-cluster[curent][j],2.0);
                
            }
        err+= Math.sqrt(suma);
        
        
           
        
 }
       
 return err;
 }
 
 static double[] calculfinal()
 {
     int m=data.datetest[0].length;
      // combina metodele de assign centroid si mean ca sa returneze erori
     
     double[][]init2 =assign(initializare());
     double[][]init =average(init2);
     double temp;
     double erori[]= new double[50];
     erori[0]=calculeroare(init2,init);   
     
    boolean status = true;
       
    
      
        int k=0;
       
        while(status){
          
           
        init=average(init2);
        init2=assign(init);
             temp=calculeroare(init2,init);
          erori[k+1]=temp;
             
             if(erori[k]==temp){
                 status=false;
             }
             
         
         k++;
     
         }
              
        

                                                            
                                                
                                                                   
                                                            
     return erori;
 }
  @Override
     public void run(){
       
       for(int i=0;i<KNN1.numberofsets;i++){
       
    
    KNN1.erori[i]=kmeans1.calculfinal();
        }
   }
  

}
