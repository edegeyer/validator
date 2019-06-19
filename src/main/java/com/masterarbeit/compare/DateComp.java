package com.masterarbeit.compare;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by jan-philippheinrich on 08.07.17.
 */
public class DateComp implements ComparerInterface {

    private final IntegerComp integerComp;

    DateComp(IntegerComp i1) {
        this.integerComp = i1;
    }

    private LocalDate convertDate(Object a) {
        LocalDate date;
        if (a.getClass() == Date.class) {
            date = ((Date) a).toLocalDate();
        } else {
            date = ((LocalDate) a);
        }
        return date;
    }

    private double gausCompareTwoValues(int org, int anonymus, double sig) {

        if (org == anonymus)
            return 0.0;

        double del = -0.5 * Math.pow(((Math.abs(anonymus - org)) / sig), 2.0);
        return 1.0 - Math.exp(del);

    }

    @Override
    public double compare(Object a, Object b, double sig) {
        LocalDate date1 = convertDate(a);
        LocalDate date2 = convertDate(b);
        double day = gausCompareTwoValues(date1.getDayOfMonth(), date2.getDayOfMonth(), Sigma.Monate);
        double month = gausCompareTwoValues(date1.getMonthValue(), date2.getMonthValue(), Sigma.Tage);
        double year = this.integerComp.compare(date1.getYear(), date2.getYear(), 1.0);
        return ((day + month + year) / 3.0);
    }
}
