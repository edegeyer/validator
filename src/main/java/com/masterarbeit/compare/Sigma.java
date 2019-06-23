package com.masterarbeit.compare;

import java.util.Arrays;
import java.util.List;

public class Sigma {
    public static double sigmaBoolean = 1.0;
    public static double sigmaInteger = 1.0;
    public static double sigmaIntLength = 2;
    public static double sigmaString = 1.0;
    public static double sigmaDouble = 1000;
    public static double sigmaMonth = 1.0;
    public static double sigmaDay = 1.0;
    public static double sigmaStringLength = 1.0;
    public static double sigmastringDistribution = 1.0;
    public static double sigmaStringOrder = 1.0;

    public String Strcmp2 = "Heinrich";
    public List<String> possibleComparer = Arrays.asList("Levenshtein", "Damerau", "Heinrich", "Hamming");

    public double value = 1.0;
    public double dayvalue = 1.0;
    public double monthvalue = 1.0;
    public double strl = 1.0;
    public double strd = 1.0;
    public double stro = 1.0;

    public void typeconv() {

        switch (Strcmp2) {
            case "Levenshtein":
                sigmaString = 1.0;
                break;
            case "Damerau":
                sigmaString = 2.0;
                break;
            case "Heinrich":
                sigmaString = 3.0;
                break;
            case "Hamming":
                sigmaString = 4.0;
                break;
            default:
                sigmaString = 0.0;
                break;
        }
    }

    public String getStrcmp2() {
        return Strcmp2;
    }

    public void setStrcmp2(String wert) {

        this.Strcmp2 = wert;
    }

    public double getStro() {
        return stro;
    }

    public void setStro(double wert) {

        this.stro = wert;
    }

    public double getStrd() {
        return strd;
    }

    public void setStrd(double wert) {

        this.strd = wert;
    }

    public double getStrl() {
        return strl;
    }

    public void setStrl(double wert) {

        this.strl = wert;
    }

    public double getWert() {
        return value;
    }

    public void setWert(double value) {

        this.value = value;
    }

    public double getTagewert() {
        return dayvalue;
    }

    public void setTagewert(double dayvalue) {
        checkSigma(dayvalue);
        this.dayvalue = dayvalue;
    }

    public double getMonatewert() {
        checkSigma(monthvalue);
        return monthvalue;
    }

    public void setMonatewert(double monthvalue) {
        checkSigma(monthvalue);
        this.monthvalue = monthvalue;
    }

    public void setSigmaBool(double input) {
        checkSigma(input);
        sigmaBoolean = input;
    }

    public void setSigmaInte(double input) {
        checkSigma(input);
        sigmaInteger = input;
    }

    public void setSigmaIntelength(double input) {
        checkSigma(input);
        sigmaIntLength = input;
    }

    public void setSigmaStr(double input) {
        checkSigma(input);
        sigmaString = input;
    }

    public void setSigmaDoub(double input) {
        checkSigma(input);
        sigmaDouble = input;
    }

    public void setSigmaMonate(double input) {
        checkSigma(input);
        sigmaMonth = input;
    }

    public void setSigmaTage(double input) {
        checkSigma(input);
        sigmaDay = input;
    }

    public void setSigmastro_(double input) {
        checkSigma(input);
        sigmaStringOrder = input;
    }

    public void setSigmastrl_(double input) {
        checkSigma(input);
        sigmaStringOrder = input;
    }

    public void setSigmastrd_(double input) {
        checkSigma(input);
        sigmastringDistribution = input;
    }

    public void checkSigma(double input) {
        if (input == 0.0) {
            throw new IllegalArgumentException("Sigma not allowed to be zero");
        }
    }
}
