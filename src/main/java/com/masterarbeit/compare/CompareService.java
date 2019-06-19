package com.masterarbeit.compare;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


import com.masterarbeit.entities.*;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;


public class CompareService {


    private IntegerComp intCompare = new IntegerComp();
    private DateComp dateCompare = new DateComp(intCompare);
    private DoubleComp doubleCompare = new DoubleComp(intCompare);
    private InsuranceNumberComp insuranceNumberComp = new InsuranceNumberComp(intCompare, dateCompare);
    private StringComp stringCompare = new StringComp(intCompare, insuranceNumberComp);
    private BoolComp boolComp = new BoolComp();

    private Comparer comp = new Comparer(intCompare,doubleCompare,dateCompare,stringCompare,boolComp, insuranceNumberComp);

    private double sigma ;  // kontrollparameter
    File file = new File("out.txt");


    public Map<Integer, Double> compareOneOnOne(List<Patient> patients, List<lastcharweg> patient_anonym) throws IllegalAccessException, ParseException, FileNotFoundException {

        Iterator<Patient> it1 = patients.iterator();
        Iterator<lastcharweg> it2 = patient_anonym.iterator();
        Map<Integer, Double> resultsPerRecord = new HashMap<>();

        while(it1.hasNext() && it2.hasNext()){
            Patient p = it1.next();
            lastcharweg pa = it2.next();
            Map<String, Double> result = compareEntities(p, pa);
            resultsPerRecord.put(p.getId(), getAbsolute(result));
        }
        return resultsPerRecord;
    }
// complexität o(N^2)
    public Map<Integer, Double> doCompareSAP(List<sap> original, List<qup_sap> anonym) throws IllegalAccessException, ParseException {

        Iterator<sap> it1 = original.iterator();
        Iterator<qup_sap> it2 = anonym.iterator();
        List<Map<String, Double>> results = new ArrayList<>();
        Map<Integer, Double> overallOutcome = new HashMap<>();

        while(it1.hasNext() && it2.hasNext()){

            Object patientOriginal = it1.next();
            Object patientAnonym = it2.next();

            Field[] fieldsPatientOriginal = patientOriginal.getClass().getDeclaredFields();
            Field[] fieldsPatientAnonym = patientAnonym.getClass().getDeclaredFields();
            Map<String, Double> result = new HashMap<>();

            for (int i=0; i<fieldsPatientOriginal.length; i++){
                fieldsPatientAnonym[i].setAccessible(true);
                fieldsPatientOriginal[i].setAccessible(true);
                double value = comp.compare(fieldsPatientOriginal[i].get(patientOriginal), fieldsPatientAnonym[i].get(patientAnonym),sigma);
                result.put(fieldsPatientOriginal[i].getName(), value);
            }

            sap sap = (sap) patientOriginal;
            overallOutcome.put(sap.getId(), getAbsolute(result));

            results.add(result);
        }
        System.out.println("----------Results:---------------");

        for (Object result : results) {
            HashMap<String, Double> temp = (HashMap<String, Double>) result;
            Set<java.util.Map.Entry<String,Double>> set = temp.entrySet();
            for (Object aSet : set) {
                Map.Entry<String,Double> me = (Map.Entry<String,Double>) aSet;
                System.out.println(me.getKey() + " : " + me.getValue());
            }
            System.out.println("-----------------------------");
        }

        return overallOutcome;
    }
    // terminbuchung in sap qup_sap

    public double getAbsolute(Map<String,Double> entry){

        double sum = 0;

        Set set=entry.entrySet();
        for (Object aSet: set) {
            Map.Entry me = (Map.Entry) aSet;
            sum = sum + (Double) me.getValue();

        }
        return sum/(set.size());
    }
// gesamteanonymisierungsgrad aufsummieren
    public double resultForTable(Map<Integer, Double> map){

        double result=0;
        for (Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            result = result + (Double) entry.getValue();
        }

        System.out.println("Table: " + result/map.size());
        return (result/map.size());

    }

    public LinkedHashMap<String, Double> compareEntities(Patient p, lastcharweg pa) throws IllegalAccessException, ParseException, FileNotFoundException {

    	LinkedHashMap<String, Double>  result = new LinkedHashMap<String,Double>();
        Field[] fieldPatient = p.getClass().getDeclaredFields();
        Field[] fieldPatientAnonym = pa.getClass().getDeclaredFields();

        try
        {
            FileOutputStream fos = new FileOutputStream(file,true);
            PrintStream ps = new PrintStream(fos);
  	    	System.setOut(ps);
 	    	
 	    	
            for (int i=1; i<fieldPatientAnonym.length; i++) {
                fieldPatient[i].setAccessible(true);
                fieldPatientAnonym[i].setAccessible(true);
                result.put(fieldPatient[i].getName(), comp.compare(fieldPatient[i].get(p), fieldPatientAnonym[i].get(pa), sigma));

                
            }
            // wird je nach objekt typ entsprend gerechnet:datecomp doublecomp intergercomp stringcomp
        Set<java.util.Map.Entry<String,Double>> set = result.entrySet();

        }
        catch  (FileNotFoundException ex) 
        {
            System.out.println("file not found exception "+ ex);
        }
        
		return result;
    }

    public interface Comparable<T>{
        int compareTo(T o);
    }


// wird in compareoneonone aufgerufen
    public Integer findTheMostLikely(Patient p, List<lastcharweg> patient_anonym) throws IllegalAccessException, ParseException, FileNotFoundException {

        double res = 1.0;
        int mostLikely = 0;

        for (lastcharweg patientAnonym : patient_anonym) {
            double tmp = getAbsolute(compareEntities(p, patientAnonym));
            System.out.println("tmp für: "+ patientAnonym.getContactFirstName() + " " + tmp);
            if (tmp < res) {
                res = tmp;
                mostLikely = patientAnonym.getId();
            }
        }
        return mostLikely;
    }
  //finde das ähnlichste patient_anonym zu p
    public double compareAttribute(Object a, Object b) throws ParseException {

        return comp.compare(a,b,sigma);

    }


}