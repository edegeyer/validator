package com.masterarbeit.compare;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

/**
 * Created by jan-philippheinrich on 05.07.17.
 */
public class IntegerComp implements ComparerInterface {

    private ArrayList<Integer> intToArrayList(int x) {
        int temp = x;
        ArrayList<Integer> array = new ArrayList<>();
        while (temp > 0) {
            array.add(temp % 10);
            temp = temp / 10;
        }
        Collections.reverse(array);
        return array;
    }

    private ArrayList<Integer> stringToArrayList(String x){
        ArrayList<Integer> array = new ArrayList<>();
        for (int i=0; i<x.length(); i++){
            array.add((Character.getNumericValue(x.charAt(i))));
        }
        return array;
    }

    private ArrayList<Integer> longToArrayList(long x) {
        ArrayList<Integer> array = new ArrayList<>();
        String temp = String.valueOf(x);
        temp = temp.replace(".", "");
        for (int i=0; i<temp.length(); i++){
            char c = temp.charAt(i);
            array.add(Character.getNumericValue(c));
        }
        return array;
    }

/*
    private void arrangeLength(ArrayList<Integer> a, ArrayList<Integer> b){

        if (a.size() != b.size()){
            if (a.size() < b.size()){
                int dif = b.size()-a.size();
                for (int i = 0; i < dif; i++){
                    a.add(0);
                }
            }
            if (b.size() < a.size()) {
                int dif = a.size()-b.size();
                for (int i = 0; i < dif; i++){
                    b.add(0);
                }
            }
        }
    }
*/
// durch arrangeLength werden viele 0 am kurze Integer hinzugefügt, bis zwei Interger gleichstellig sind, das könnte aber zu schlecht Ergebnis führen, wenn 
// das lange Integer viele 0 besitzen
    private double compareLength(ArrayList<Integer> a, ArrayList<Integer> b, double sig){

        double result;
        if (a.size() > b.size()) {
            result = exp(-0.5*(((pow(((a.size() - b.size())/sig),2)))));
        }
        else if (a.size() < b.size()){
            result = exp(-0.5*(((pow(((b.size()-a.size())/sig),2)))));
        }
        else {
            return 0.0;
        }
        return result;
    }

    @Override
    public double compare(Object a, Object b, double sig) {

        ArrayList<Integer> org = new ArrayList<>();
        ArrayList<Integer> anonymus = new ArrayList<>();

        if (a instanceof String && b instanceof String){
            org = (stringToArrayList(((String) a)));
            anonymus = (stringToArrayList(((String) b)));
            if (a.equals(b))
                return 0.0;
        }
        else if (a instanceof Integer && b instanceof Integer) {
            System.out.println("is integer " + a +" und " + b);
            if (a.equals(b)){
                System.out.println("equals");
                return 0.0;}
            org = (intToArrayList(((Integer) a)));
            anonymus = (intToArrayList(((Integer) b)));
        }
        else if (a instanceof Long && b instanceof Long) {
            System.out.println("is long " + a +" und " + b);

            if (a.equals(b)){
                System.out.println("equals");

                return 0.0;}
            org = (longToArrayList(((long) a)));
            anonymus = (longToArrayList(((long) b)));
        }


        double length = compareLength(org,anonymus,Sigma.Intelength);
  //    arrangeLength(org, anonymus);
        int len;
        if (org.size()>anonymus.size())
        {
        	len=anonymus.size();
        }
        else
        	{
        	len = org.size();
        	}
        
        double sum = 0.0;

        for (int i = len-1; i >= 0; i--) {
            sum += exp(-0.5*(pow((org.get(i)-anonymus.get(i))/sig,2)));
        }
// hier steht nicht mit thesis überein
        Collections.reverse(anonymus);
        if (org.equals(anonymus))
            sum =  0.0;

        double cont = (1.0/len) * sum;
        return (0.5*(cont+length));
    }

}

