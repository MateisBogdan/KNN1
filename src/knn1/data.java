
package knn1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;




public class data  {
  
    public static  double[][] querytest;
    
    public static double[][] datetest;
   
    
     static File test_d;
     
     static File test_q;
    
    static int coloane;

   static void setare(int set){
    try {
       //selecteaza fisierele si numarul de coloane in functie de datasetul ales
       
        switch(set){
            case 1 :
                 
                  test_d = new File("data.txt"); 
                  test_q =new File("queries.txt");
                 
                  coloane=5;
                  datetest=citirefisier(test_d);
                  querytest=citirefisier(test_q);       
                 
                  KNN1.High = 4;
                  KNN1.Low =1;
               //  KNN1.chartTitle += "Iris ";
                  kmeans1.K=3;
                  
                  break;
            
            case 2 :
               
                test_d = new File("datapima.txt");
                test_q = new File("queriespima.txt");
                 
                coloane=9;
                kmeans1.K=2;
                 KNN1.High=2;
                 KNN1.Low=0;
                
                 
                         
                 datetest=citirefisier(test_d);
                querytest=citirefisier(test_q);
                
                
                break;
            case 3 :
               
                test_d = new File("survival.txt");
                test_q = new File("survivalquery.txt");
                
                coloane=4;
                KNN1.High=3;
                KNN1.Low =1;
                kmeans1.K=2;
                
                datetest=citirefisier(test_d);
                querytest=citirefisier(test_q);
                break;
            case 4 :
               
                test_d= new File("balancedata.txt");
                test_q= new File("balancequery.txt");
                
                coloane = 5;
                KNN1.High = 3;
                KNN1.Low =1;
               
                kmeans1.K=3;
                datetest=citirefisier(test_d);
                querytest=citirefisier(test_q);
                              
                                
                break;
           
               
                
        }
          
        
    } catch (final FileNotFoundException e) {
        throw new ExceptionInInitializerError(e.getMessage());
    }
   }
    
  //metoda ce returneaza numarul de linii din fisierul citit 
    
     public static int counter (File fisier) throws FileNotFoundException {
       
         FileReader x= new FileReader(fisier); 
         Scanner count = new Scanner(x);
         
         int numarator=0;
                  
        
            while(count.hasNextLine()){
                numarator++;
                count.nextLine();
            }
          count.close();
            return numarator;
        }
      
   public static double[][] citirefisier(File fisier) throws FileNotFoundException {
      
       FileReader fileread = new FileReader(fisier);
       
       int randuri=counter(fisier);
       
        Scanner reader = new Scanner(fileread);
      
            double[][] rezultate= new double[randuri][coloane];
            String[][] rezultat= new String[randuri][coloane];
              
           
            String[] date_text= new String[randuri];
            int i=0;
        
            while(reader.hasNextLine()){
       date_text[i]= reader.nextLine();//introduce fiecare linie din fisier in string
       
        i++;
        }
        reader.close();
        //separa numerele din string
        for(i=0;i<randuri-1;i++){
        
            rezultat[i]= date_text[i].split(",");
               
        }
       //introduce numerele din string intr-o matrice
        for(i=0;i<randuri-1;i++){
        for(int j=0;j<coloane;j++){
            rezultate[i][j]=Double.parseDouble(rezultat[i][j]);
        }
        }
        
        
        return rezultate;
        }

}
