package com.masterarbeit.compare;

import java.util.Arrays;
import java.util.List;

public class Sigma {
    public static double Bool = 2.0;
    public static double Inte = 1.0;
    public static double Intelength = 2;
    public static double Str = 4.0;
    public static double Doub = 1000;
    public static double Monate = 2.0;
    public static double Tage = 1.0;
    public static double strl_ = 1.0;
    public static double strd_ = 1.0;
    public static double stro_ = 1.0;

    public String Strcmp2;
    public List<String> possibleComparer = Arrays.asList("Levenshtein", "Damerau", "Heinrich", "Hamming");

    public double wert = 1.0;
    public double tagewert = 1.0;
    public double monatewert = 1.0;
    public double strl = 1.0;
    public double strd = 1.0;
    public double stro = 1.0;

    public void typeconv() {

        switch (Strcmp2) {
            case "Levenshtein":
                Str = 1.0;
                break;
            case "Damerau":
                Str = 2.0;
                break;
            case "Heinrich":
                Str = 3.0;
                break;
            case "Hamming":
                Str = 4.0;
                break;
            default:
                Str = 0.0;
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
        return wert;
    }

    public void setWert(double wert) {

        this.wert = wert;
    }

    public double getTagewert() {
        return tagewert;
    }

    public void setTagewert(double wert) {
        checkSigma(wert);
        this.tagewert = wert;
    }

    public double getMonatewert() {
        checkSigma(wert);
        return monatewert;
    }

    public void setMonatewert(double wert) {
        checkSigma(wert);
        this.monatewert = wert;
    }

    public void setSigmaBool(double input) {
        checkSigma(input);
        Bool = input;
    }

    public void setSigmaInte(double input) {
        checkSigma(input);
        Inte = input;
    }

    public void setSigmaIntelength(double input) {
        checkSigma(input);
        Intelength = input;
    }

    public void setSigmaStr(double input) {
        checkSigma(input);
        Str = input;
    }

    public void setSigmaDoub(double input) {
        checkSigma(input);
        Doub = input;
    }

    public void setSigmaMonate(double input) {
        checkSigma(input);
        Monate = input;
    }

    public void setSigmaTage(double input) {
        checkSigma(input);
        Tage = input;
    }

    public void setSigmastro_(double input) {
        checkSigma(input);
        stro_ = input;
    }

    public void setSigmastrl_(double input) {
        checkSigma(input);
        strl_ = input;
    }

    public void setSigmastrd_(double input) {
        checkSigma(input);
        strd_ = input;
    }

    public void checkSigma(double input) {
        if (input == 0.0) {
            throw new IllegalArgumentException("Sigma not allowed to be zero");
        }
    }
}
