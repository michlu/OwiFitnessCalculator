package model;

import model.User;

import java.util.ResourceBundle;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class Model {


    ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");

    public static double obliczBmi(User user) {
        double height = user.getHeight();
        double weight = user.getWeight();
        // wzor na bmi - waga podzielona przez wzrost(w metrach) do kwadratu

        height = height / 100;
        double bmi = weight / (Math.pow(height, 2));
        return bmi;
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

    public String wynikTxt(User user) {
        double height = user.getHeight();
        height = height / 100;
        double bmi = user.getWeight() / (Math.pow(height, 2));

        String txt = "";
        if (bmi < 18.5) {
            txt = bundle.getString("result.bmi.underweight");
        } else if (bmi <= 24.9) {
            txt = bundle.getString("result.bmi.normal");
        } else if (bmi <= 29.9) {
            txt = bundle.getString("result.bmi.overweight");
        } else if (bmi <= 34.9) {
            txt = bundle.getString("result.bmi.obese1");
        } else if (bmi <= 39.9) {
            txt = bundle.getString("result.bmi.obese2");
        } else {
            txt = bundle.getString("result.bmi.obese3");
        }
        return txt;
    }
    // AIBW
    public double wagaNalezna(User user){
        double height = user.getHeight();

        // wzor waga nalezna = 20,5 x wzrost (w metrach) do kwadratu
        height = height / 100;
        double wn = 20.5 * (Math.pow(height, 2));

        return wn;
    }

    public double obliczWzorBroca(User user){
        int gender = user.getGender();
        double height = user.getHeight();
        double result;
        if(gender == 1){
            result = (height - 100) * 0.90;
        }
        else{
            result = (height - 100) * 0.85;
        }
        return result;
    }
    public double obliczWzorBrocaBrugsha(User user){
        double height = user.getHeight();
        double result;
        if(height <= 164){
            result = (height - 100);
        }
        else if(height <= 175){
            result = (height - 105);
        }
        else {
            result = (height - 110);
        }
        return result;
    }
    public double obliczWzorPottona(User user){
        int gender = user.getGender();
        double height = user.getHeight();
        double result;
        if(height>150){
            if(gender == 1){
                result = height - 100 - (height - 100) / 20;
            }
            else{
                result = height - 100 - (height - 100) / 10;
            }
        }
        else{
            result = 0;
        }
        return result;
    }
    public double obliczWzorLorenza(User user){
        int gender = user.getGender();
        double height = user.getHeight();
        double result;
        if(height>150){
            if(gender == 1){
                result = height - 100 - 0.25*(height - 150);
            }
            else{
                result = height - 100 - 0.5*(height - 150);
            }
        }
        else{
            result = 0;
        }
        return result;
    }
    public double obliczWzorATUnZ(User user){
        double height = user.getHeight();
        double result;
        if(height>150){
            result = 50 + 0.75*(height - 150);
        }
        else{
            result = 0;
        }
        return result;
    }
    // BMR
    public double obliczPpmMifflin(User user){
        int gender = user.getGender();
        int age = user.getAge();
        double height = user.getHeight();
        double weight = user.getWeight();
        double result;

        // SWE
        if(gender == 1){
            result = (10*weight)+(6.25*height)-(5*age)+5;
        }
        else{
            result = (10*weight)+(6.25*height)-(5*age)-161;
        }
        return result;
    }

}
