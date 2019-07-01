package com.masterarbeit.compare;

import java.text.ParseException;
import java.util.*;


import static java.lang.Math.exp;

/**
 * Created by jan-philippheinrich on 27.07.17.
 */
public class StringComp implements ComparerInterface {


    private final IntegerComp integerComp;
    private final InsuranceNumberComp insuranceNumberComp;


    public StringComp(IntegerComp integerComp, InsuranceNumberComp insuranceNumberComp) {

        this.integerComp = integerComp;
        this.insuranceNumberComp = insuranceNumberComp;
    }

    // Levenshtein Distance Implementation
    private int calculate(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
                            min(dp[i - 1][j] + 1,
                                    dp[i][j - 1] + 1));
                }
            }
        }
        int leven = dp[x.length()][y.length()];
        return dp[x.length()][y.length()];
    }

    // Damerau-Levenshtein Distance
    private int calculate_DL(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
                            min(dp[i - 1][j] + 1,
                                    dp[i][j - 1] + 1));

                    if (i > 1 &&
                            j > 1 &&
                            x.charAt(i - 1) == y.charAt(j - 2) &&
                            x.charAt(i - 2) == y.charAt(j - 1)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + x.charAt(i - 1) == y.charAt(j - 1) ? 0 : 1);
                    }
                }
            }
        }

        return dp[x.length()][y.length()];
    }

    private int min(int i, int j) {
        if (i >= j)
            return j;
        else
            return i;
    }

    private int costOfSubstitution(char a, char b) {
        if (a == b)
            return 0;
        else
            return 1;

    }

    private HashMap<Character, Integer> StringToHashMap(String s) {

        char[] _s = s.toLowerCase().toCharArray();
        HashMap<Character, Integer> result = new HashMap<>();

        for (Character c : _s) {
            Integer val = result.get(new Character(c));
            if (val != null)
                result.put(c, val + 1);
            else
                result.put(c, 1);
        }
        return result;
    }

    // calculates, how often one char is in a string
    private int findCharInStringRight(char x, String a, int pos) {

        if (pos >= a.length())
            pos = a.length() - 1;

        int distance = 0;
        for (int i = pos; i < a.length(); i++) {
            if (a.charAt(i) == x)
                return distance;
            distance++;
        }
        return -1;
    }

    private int findCharInStringLeft(char x, String a, int pos) {

        if (pos >= a.length())
            pos = a.length() - 1;

        int distance = 0;
        for (int i = pos; i >= 0; i--) {
            if (a.charAt(i) == x)
                return distance;
            distance++;
        }
        return -1;
    }

    private int[] getTranspositions(String _a, String _b) {

        String a;
        String b;
        int length;

        if (_a.length() > _b.length()) {
            length = _a.length();
            a = _a;
            b = _b;
        } else {
            length = _b.length();
            a = _b;
            b = _a;
        }

        int[] right = new int[length];
        int[] left = new int[length];

        for (int i = 0; i < length; i++) {
            right[i] = findCharInStringRight(a.charAt(i), b, i);
            left[i] = findCharInStringLeft(a.charAt(i), b, i);
        }

        int[] res = new int[length];
        for (int i = 0; i < length; i++) {

            if (right[i] > left[i] && right[i] > -1) {
                res[i] = right[i];
            }
            if (left[i] > right[i] && left[i] > -1) {
                res[i] = right[i];
            }
            if (left[i] == right[i] && left[i] > -1) {
                res[i] = right[i];
            }
            if (left[i] == right[i] && left[i] == -1) {
                res[i] = -1;
            }
        }
        int count = 0;
        for (Integer i : res) {
            if (i != -1)
                count++;
        }
        int[] result = new int[count];
        int temp = 0;
        for (Integer i : res) {
            if (i != -1) {
                result[temp] = i;
                temp++;
            }
        }
        return result;

    }

    private HashMap<Character, Integer> getDistribution(String a, String b) {

        HashMap<Character, Integer> _a = StringToHashMap(a);
        HashMap<Character, Integer> _b = StringToHashMap(b);
        HashMap<Character, Integer> transpositions = new HashMap<>();
        Set<Character> total = new HashSet<>();
        total.addAll(_a.keySet());
        total.addAll(_b.keySet());

        for (Character c : total) {
            int x = 0;
            int y = 0;

            if (_a.get(c) != null)
                x = _a.get(c);
            if (_b.get(c) != null)
                y = _b.get(c);

            transpositions.put(c, Math.abs(x - y)); // wie viel mal mehr char c in string a als in string b vorkommt
        }
        return transpositions;

    }

    private double hammingDistance(String string1, String string2) {

        int shortest = Math.min(string1.length(), string2.length());
        int longest = Math.max(string1.length(), string2.length());
        double result = 0.0;
        // first: compare both strings (only with the length of the shortest)
        for (int i = 0; i < shortest; i++) {
            if (string1.charAt(i) != string2.charAt(i)) {
                result++;
            }
        }
        // both strings differ by the length difference, so also take that in consideration
        result += longest - shortest;
        return result;
    }

    private double compareLength(String a, String b, double sig) {

        double length = Math.abs(a.length() - b.length());
        return 1.0 - (exp(-0.5 * (Math.pow(length / sig, 2))));

    }

    private double compareContent(String a, String b, double sig) {

        double order = compareOrder(a, b, Sigma.sigmaStringOrder);

        double distribution = compareDistribution(a, b, Sigma.sigmastringDistribution);
        return 0.5 * (order + distribution);
    }

    private double compareDistribution(String a, String b, double sig) {

        HashMap<Character, Integer> distributions = getDistribution(a, b);
        Set set = distributions.entrySet();
        Iterator i = set.iterator();
        double sum = 0;

        while (i.hasNext()) {
            Map.Entry<Character, Integer> entry = (Map.Entry) i.next();
            double d = entry.getValue();
            sum += 1.0 - exp(-0.5 * (Math.pow(d / sig, 2)));
        }
        int size = distributions.size();
        if (size == 0)
            return sum;
        return (sum * (1.0 / size));
    }

    private double compareOrder(String a, String b, double sig) {

        int[] transpositions = getTranspositions(a, b);
        if (transpositions.length == 0)
            return 0.0;
        double sum = 0.0;

        for (Integer i : transpositions) {
            sum = sum + (1.0 - exp(-0.5 * Math.pow(i / sig, 2.0)));
        }
        return (sum * (1.0 / transpositions.length));
    }

    @Override
    public double compare(Object a, Object b, double sig) throws ParseException {


        String _a = (String) a;
        String _b = (String) b;

        if (sig == 1.0) // leven
        {
            double nenner = Math.max(_a.length(), _b.length());
            // normalize by divding longest string length
            double value = calculate(_a, _b) / nenner;
            return calculate(_a, _b) / nenner;
        } else if (sig == 2.0) //Damerau-Levenshtein
        {
            double nenner = Math.max(_a.length(), _b.length());


            return calculate_DL(_a, _b) / nenner;
        } else if (sig == 3.0)  // JPH
        {
            if (_a.matches("\\d+") && _b.matches("\\D+")) {
                return 0.0;
            }
            if (_b.matches("\\d+") && _a.matches("\\D+")) {
                return 0.0;
            }

            if (_a.matches("^\\d+[a-zA-Z][a-zA-Z\\d]*$") && _b.matches("^\\d+[a-zA-Z][a-zA-Z\\d]*$")) {
                if ((_a.length() == 12 && _b.length() == 12)) {
                    String x = _a.substring(8, 9);
                    String y = _b.substring(8, 9);
                    if (x.matches("[a-zA-Z]") && y.matches("[a-zA-Z]"))
                        return this.insuranceNumberComp.compare(a, b, sig);
                }
            }

            if (_a.matches("[A-z]+[0-9]$") && _b.matches("[A-z]+[0-9]$")) {
                // TODO: auch den alphanumerischen String als String betrachten und vergleichen
                return 0.0;
            } else if (_a.matches("[0-9 ]+") && _b.matches("[0-9 ]+")) {
                return 0.0;
            }

            double length = compareLength(_a, _b, Sigma.sigmaStringLength);
            double content = compareContent(_a, _b, sig);

            if (_a.toLowerCase().contains(_b.toLowerCase()) || _b.toLowerCase().contains(_a.toLowerCase())) {
                content = 0.0;
            }

            return 0.5 * (length + content);


        } else if (sig == 4.0) { // Hamming
            return hammingDistance(_a, _b);
        }

        // return 1.5 to signal an error (aka sig == 0, the default value)
        return 1.5;
    }

}
