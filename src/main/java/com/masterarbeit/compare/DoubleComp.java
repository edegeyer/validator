package com.masterarbeit.compare;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

/**
 * Created by jan-philippheinrich on 17.07.17.
 */
public class DoubleComp implements ComparerInterface {

    private final IntegerComp integerComp;
/*
    private int DoubleToInt(double x) {
        String temp = String.valueOf(x);
        temp = temp.replace(".", "");
        return Integer.parseInt(temp);
    }
*/
  
    DoubleComp(IntegerComp i1){
        this.integerComp = i1;
    }
    private double ObjecttoDouble(Object x) {
        String temp = String.valueOf(x);
      
        return Double.parseDouble(temp);
    }
    

    @Override
    public double compare(Object a, Object b, double sig) {

    	
    	double res =   1-exp(-0.5*(pow((ObjecttoDouble(a)-ObjecttoDouble(b))/sig,2)));
    	return res;
    }
/*
    private double compareCommaPosition(double a, double b){
        String tempA = String.valueOf(a);
        int commaA = tempA.indexOf(".");
        String tempB = String.valueOf(b);
        int commaB = tempB.indexOf(".");

        double q = commaA - commaB;
        if (q==0.0)
            return 0.0;
        return 1.0;
    }
    */
    // algorithmus wird nach Thesis umgeschrieben
}