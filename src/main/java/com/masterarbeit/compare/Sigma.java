package com.masterarbeit.compare;

public class Sigma {
  public static double Bool=2.0;  
  public static double Inte=1.0;   //  testen gegen 2.0
  public static double Intelength=2;  // gegen 1.5
  public static double Str=4.0;  // gegen levenshtein
  public static double Doub=1000; // gegen 310
  public static double Monate=2.0; // gegen month sigma=day sigma=2
  public static double Tage=1.0;
  public static double strl_=1.0;
  public static double strd_=1.0;
  public static double stro_=1.0;
  
  public String Strcmp2;
//  public String strcmp;
  public double wert;
  public double tagewert;
  public double monatewert;
  public double strl;
  public  double strd;
  public double stro;
  
  public void typeconv() {
	  if (Strcmp2.equals("L"))
		  Str=1.0;
	  if (Strcmp2.equals("DL"))
		  Str=2.0;
	  if (Strcmp2.equals("JPH"))
		  Str=3.0;}
  public String getStrcmp2(){
	    return  Strcmp2;
	    }

	   public void setStrcmp2(String wert){

	    this.Strcmp2 = wert;
	    }
	   public double getStro(){
		    return  stro;
		    }

		   public void setStro(double wert){

		    this.stro= wert;
		    }
	   public double getStrd(){
		    return  strd;
		    }

		   public void setStrd(double wert){

		    this.strd= wert;
		    }
	   public double getStrl(){
		    return  strl;
		    }

		   public void setStrl(double wert){

		    this.strl= wert;
		    }
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
/*  public void setStrcmp (String input) {
	  strcmp = input;
	}*/
  public void setSigmastro_ (double input)
  {
	  stro_ = input;
	}
  public void setSigmastrl_ (double input)
  {
	  strl_ = input;
	}
  public void setSigmastrd_ (double input)
  {
	  strd_ = input;
	}
}
