package com.masterarbeit.controller;

import com.masterarbeit.dbOperations.DatabaseOperations;
import com.masterarbeit.compare.CompareService;
import com.masterarbeit.compare.Sigma;
import com.masterarbeit.entities.*;
import com.masterarbeit.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Jan on 13.04.2017.
 */
@Controller
public class ViewController {

    private PatientRepository patientRepository;
    private PatientAnonymRepository patientAnonymRepository;
    private CompareService compareService;
    private PatientTestRepository patientTestRepository;

    @Autowired
    public ViewController(PatientRepository patientRepository, PatientAnonymRepository patientAnonymRepository,
                          PatientTestRepository patientTestRepository) {
        this.patientRepository = patientRepository;
        this.patientAnonymRepository = patientAnonymRepository;
        this.patientTestRepository = patientTestRepository;
    }

    @RequestMapping("/")
    public String index(Model theModel) {

        theModel.addAttribute("Patient", patientTestRepository.findAll());
        theModel.addAttribute("Patient_anonym", patientAnonymRepository.findAll());


        return "index";
    }

    @RequestMapping("/compare")
    public String compare(Model theModel) throws IllegalAccessException, ParseException, FileNotFoundException {
        long start = System.currentTimeMillis();
        CompareService compareService = new CompareService();
        Map<Integer, Double> results = compareService.compareOneOnOne(patientRepository.findAll(), patientAnonymRepository.findAll());
        // Gesamtergebnis
        double tableResult = compareService.resultForTable(results);
        theModel.addAttribute("patients", patientRepository.findAll());
        theModel.addAttribute("patientsAnonym", patientAnonymRepository.findAll());
        theModel.addAttribute("result", results);
        // total time in ms
        long totalTime = System.currentTimeMillis() - start;
        theModel.addAttribute("totalTime", totalTime);
        theModel.addAttribute("table", tableResult);

        return "compare";
    }

    @RequestMapping("/compareSelected")
    public String compareSelected(Model theModel, @RequestParam(name = "pid") List<String> selection) {

        List<Patient> list = new ArrayList<>();

        for (String s : selection) {
            System.out.println(s);
            list.add(patientRepository.findOne(Integer.parseInt(s)));
        }
        theModel.addAttribute("patients", list);

        return "compareSelected";
    }

    @RequestMapping("/compareOne")
    public String compareOne(Model model, @RequestParam(name = "pid") String selection) throws IllegalAccessException, ParseException, FileNotFoundException {

        compareService = new CompareService();
        Patient p = patientRepository.findOne(Integer.parseInt(selection));
        lastcharweg pa = patientAnonymRepository.findOne(Integer.parseInt(selection));
        HashMap<String, Double> results = compareService.compareEntities(p, pa);
        model.addAttribute("patient", p);
        model.addAttribute("patientAnonym", pa);
        model.addAttribute("results", results);

        return "compareOne";
    }

    @RequestMapping("/findTheMostLikely")
    public String findTheMostLikely(Model model, @RequestParam(name = "pid") String selection) throws IllegalAccessException, ParseException, FileNotFoundException {

        compareService = new CompareService();
        Patient p = patientRepository.findOne(Integer.parseInt(selection));
        List<lastcharweg> patient_anonym = patientAnonymRepository.findAll();
        int mostLikely = compareService.findTheMostLikely(p, patient_anonym);
        lastcharweg pa = patientAnonymRepository.findOne(mostLikely);
        HashMap<String, Double> results = compareService.compareEntities(p, pa);
        System.out.println(results.keySet());
        double total = compareService.getAbsolute(results);
        System.out.println(total);

        model.addAttribute("patient", p);
        model.addAttribute("mostLikely", pa);
        model.addAttribute("results", results);
        model.addAttribute("total", total);

        return "findTheMostLikely";
    }


    @RequestMapping("/selectTables")
    public String tablenames(Model model) throws Exception {

        DatabaseOperations DatabaseOperations = new DatabaseOperations();
        List<String> tablenames = DatabaseOperations.readDBTables();
        System.out.println(Arrays.toString(tablenames.toArray()));
        model.addAttribute("tablenames", tablenames);
        return "showTables";
    }

    @RequestMapping("/showSelectedTables")
    public String showSelectedTables(Model model, @RequestParam(name = "table") List<String> selection) throws Exception {

        DatabaseOperations databaseOperations = new DatabaseOperations();
        List<String> selectedTables = new ArrayList<>();

        List<ArrayList> tableOne = databaseOperations.getTable(selection.get(0));
        List<ArrayList> tableTwo = databaseOperations.getTable(selection.get(1));


        for (String s : selection) {
            selectedTables.add(s);
        }
        model.addAttribute("selectedTables", selectedTables);
        model.addAttribute("tableOne", tableOne);
        model.addAttribute("tableTwo", tableTwo);


        return "showSelectedTables";
    }

    @ModelAttribute("alltypes")
    public String[] getalltypes() {
        return new String[]{
                "Levenshtein", "Damerau-Leven", "JP-Heinrich"
        };
    }

    @GetMapping("/test")
    public String test(Model model) throws ParseException {

        Sigma sigma1 = new Sigma();
        model.addAttribute("sigma", sigma1);  // add greeting as object, which is used both in greeting.html and result.html as the instance
        return "test";
    }

    @PostMapping("/test")
    public String greetingSubmit(@ModelAttribute Sigma sigma) {

        if (sigma.value != 0 && sigma.dayvalue != 0 && sigma.monthvalue != 0 &&
                sigma.strd != 0 && sigma.strl != 0 && sigma.stro != 0) {

            sigma.setSigmaDoub(sigma.value);
            sigma.setSigmaTage(sigma.dayvalue);
            sigma.setSigmaMonate(sigma.monthvalue);
            sigma.setSigmastro_(sigma.stro);
            sigma.setSigmastrl_(sigma.strl);
            sigma.setSigmastrd_(sigma.strd);
            sigma.typeconv();
        } else {
            System.out.println("something went wrong here");
        }
        return "result";
    }


}


