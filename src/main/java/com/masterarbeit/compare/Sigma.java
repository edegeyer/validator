package com.masterarbeit.compare;

public class Sigma {
  public static double Bool=2.0;  
  public static double Inte=1.0;   //  testen gegen 2.0
  public static double Intelength=2;  // gegen 1.5
  public static double Str=2.0;  // gegen levenshtein
  public static double Doub=1000; // gegen 310
  public static double Monate=2.0; // gegen month sigma=day sigma=2
  public static double Tage=1.0;
  public double wert;
  public double tagewert;
  public double monatewert;
  
  public double getWert(){
    return  wert;
    }

   public void setWert(double wert){

    this.wert = wert;
    }
   public double getTagewert(){
	   return  tagewert;
	   }
 
   public void setTagewert(double wert){

	     this.tagewert = wert;
	       }
   public double getMonatewert(){
	  
          return  monatewert;}

   public void setMonatewert(double wert){

	       this.monatewert = wert;
	       }
	     
    public void setSigmaBool (double input) {
	  Bool=input;
  }
  public void setSigmaInte (double input) {
	  Inte=input;
  }
  public void setSigmaIntelength (double input) {
	  Intelength=input;
  }
  public void setSigmaStr (double input) {
	  Str=input;
  }
  public void setSigmaDoub (double input) {
	  Doub=input;
  }
  public void setSigmaMonate (double input) {
	  Monate=input;
  }
  public void setSigmaTage (double input) {
  Tage = input;
}
}
