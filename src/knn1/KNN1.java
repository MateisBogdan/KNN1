
package knn1;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import java.util.Random;


 public class KNN1  {
     
     // k-numaru de vecini selectati  pentru clasificare
      
        int k=0;
         static float[][] acuratii= new float[5][15];
        static int  numberofsets=4;
        
        static double[][] erori= new double[5][25];    
        static  String chartTitle= "Acuratete";
           
        static String[] titles = {"Iris","Diabetes","Survival","Balance"};   
        
        //gen numar random pentru cazuri de egalitate
        Random r = new Random();
        static int Low;
        static int High;
        
        //nr de randuri si coloane      
        
        int n=data.datetest.length;
         int m=data.coloane;
    
     
        

        
//calculeaza distanta(euclidiana) dintre datele de training si setul de clasificat
    public double[][] calculdistanta(double[][] datas,double[] queries){
        
            double[][] results=new double[n][2];
            double distances[][] =datas;
            double suma=0.0;
     
          for(int i=0;i<n;i++)
      {
            for(int j=0;j<m-1;j++) 
              {
               suma+=  Math.pow(distances[i][j]-queries[j],2.0); 
                   
              }  
       double dist= Math.sqrt(suma); 
       suma=0.0;
       results[i][0]=dist;
       results[i][1]=data.datetest[i][m-1];
       //stocheaza distanta dintre obiectul curent si fiecare obiect din set
       
      }
          
      return results;
     }
     
     
     
 public int[] masoaravecini(){
         
         int global;
         double temp;
         
         int[] test = new int[data.querytest.length];
         
         for(global=0;global<data.querytest.length;global++){
         //matrice de distante
             double[][] sortat  = calculdistanta(data.datetest,data.querytest[global]);
         int decision;
         
         int counter1=0;
         int counter2=0;
         int counter3=0;
         int counter4=0;
         
         int Result = r.nextInt(High-Low) + Low;
         
         
        
  //sortare matrice distante pentru a selecta cei mai apropriati (primii K) vecini
         
         
         for(int i=0;i<sortat.length;i++)
          
             for(int j=i+1;j<sortat.length;j++) 
           {    
           if (sortat[i][0] > sortat[j][0])
                           {
         
               temp=sortat[i][0];
               sortat[i][0]=sortat[j][0];
               sortat[j][0]=temp;
          
         
               temp=sortat[i][1];
               sortat[i][1]=sortat[j][1];
               sortat[j][1]=temp;
           
                           }   
           
           }
    //masoara cei mai apropriati K vecini
         for (int i=0;i<k;i++)
     {
     
         if (sortat[i][1]== 1.0)
            counter1++;
    
         else if(sortat[i][1]== 2.0)
            counter2++;
    
         else if(sortat[i][1]==3.0)
            counter3++;
         
         else if(sortat[i][1]==0.0)             
            counter4++;  
     }
        //votul majoritatii determina decizia clasificarii 
         if(counter1 > counter2 && counter1 > counter3 && counter1 > counter4)
                  
             decision = 1;
         
         else if(counter2 > counter1 && counter2 > counter3 && counter2 > counter4)
                  
             decision = 2;
         
         else if (counter3 > counter1 && counter3 > counter2 && counter3 > counter4)
                 
             decision = 3;
        
         else if(counter4 > counter1 && counter4> counter2 && counter4 > counter3)
             
             decision = 0;
         
         else
            decision=Result;
     
//matrice de rezultate pentru compararea cu clasele din date
         test[global]=decision; 
     
                     
         }
         
         return test; 
 }

        public float acuratete(int [] decizii){
                     
                int[] prelucrare= decizii;
                int corect = 0;
       
        for(int i=0;i<prelucrare.length;i++){
                    
              if(prelucrare[i]==data.querytest[i][m-1])
                         corect++;  
                                                   }
  //compara clasificarile cu clasele reale
        float procent=0;
        procent=((corect* 100.0f)/data.querytest.length);
   //calculeaza procentul de clasificari corecte 
  
  return procent;
  }
     
     
     public static void main(String[] args) throws FileNotFoundException 
 {
    data.setare(1);
        
        KNN1 tests= new KNN1();
        kmeans1 tell= new kmeans1();
        kmeans1 test = tell;
         
    
    for(int i=0;i<numberofsets;i++){
            data.setare(i+1); 
            tests.m=data.coloane;
            
            tests.n=data.datetest.length;
            
              for(tests.k=1;tests.k<13;tests.k++){
     
         acuratii[i][tests.k]=tests.acuratete(tests.masoaravecini());
      
     }
   }
    test.start();
     DecimalFormat f = new DecimalFormat("#0.00");
   


 
 chart tested = new chart(chartTitle);      
    
      
      
    
}
    
            
            
            
        }
    
  



 
 
