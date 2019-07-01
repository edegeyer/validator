package com.masterarbeit.compare;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

/**
 * Created by jan-philippheinrich on 17.07.17.
 */
public class DoubleComp implements ComparerInterface {

    private final IntegerComp integerComp;


    DoubleComp(IntegerComp i1) {
        this.integerComp = i1;
    }

    private double ObjecttoDouble(Object x) {
        String temp = String.valueOf(x);

        return Double.parseDouble(temp);
    }


    @Override
    public double compare(Object a, Object b, double sig) {
        return 1 - exp(-0.5 * (pow((ObjecttoDouble(a) - ObjecttoDouble(b)) / sig, 2)));
    }

}