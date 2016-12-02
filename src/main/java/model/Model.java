package model;

import model.User;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class Model {


    public static String wynikTxt;

    public static double obliczBmi(User user) {
        double height = user.getHeight();
        double weight = user.getWeight();
        // wzor na bmi - waga podzielona przez wzrost(w metrach) do kwadratu

        height = height / 100;
        double bmi = weight / (Math.pow(height, 2));
        System.out.println(height + " " + weight);
        return bmi;
    }

    public double wagaNalezna(User user){

        double height = user.getHeight();

        // wzor waga nalezna = 20,5 x wzrost (w metrach) do kwadratu
        height = height / 100;
        double wn = 20.5 * (Math.pow(height, 2));

        return wn;
    }
    public double wagaMinimalna(User user){
        double height = user.getHeight();
        height = height / 100;
        return 18.5 * (Math.pow(height, 2));
    }
    public double wagaMaksymalna(User user){
        double height = user.getHeight();
        height = height / 100;
        return 24.9 * (Math.pow(height, 2));
    }

    public static String wynikTxt(double bmi) {
        String txt = "";
        if (bmi < 18.5) {
            txt = "Masz niedowagę.";
        } else if (bmi <= 24.9) {
            txt = "Masz prawidłową masę ciała";
        } else if (bmi <= 29.9) {
            txt = "Masz nadwagę.";
        } else if (bmi <= 34.9) {
            txt = "Masz otyłość stopnia  I";
        } else if (bmi <= 39.9) {
            txt = "Masz otyłość stopnia  II";
        } else {
            txt = "Masz otyłość stopnia  III";
        }
        wynikTxt = txt;
        System.out.println(txt);
        return txt;
    }
}
