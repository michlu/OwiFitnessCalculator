package model;

import model.User;

import java.util.ResourceBundle;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class Model {


    ResourceBundle bundle = ResourceBundle.getBundle("bundles.model");

    /**
     * Oblicza srednia arytmetyczna z przekazanych liczb
     * @param ary tablica liczb
     * @return zwraca double średniej arytmetycznej
     */
    public static double mean(double[] ary) {
        double avg = 0;
        int t = 1;
        for (double x : ary) {
            avg += (x - avg) / t;
            ++t;
        }
        return avg;
    }

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

    public String wynikTxtBmi(User user) {
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
    public double obliczPpmHarrisBenedict(User user){
        int gender = user.getGender();
        int age = user.getAge();
        double height = user.getHeight();
        double weight = user.getWeight();
        double result;
        // SWE
        if(gender == 1){
            result = 66.5+(13.75*weight)+(5.003*height)-(6.775*age);
        }
        else{
            result = 655.1+(9.563*weight)+(1.85*height)-(4.676*age);
        }
        return result;
    }
    //BF
    public double obliczBF(User user){
        double result;
        /**
         Body density = 1.1093800 – (0.0008267 x #3) + 0.0000016 x #3’2) – (0.0002574 x #4)
         #3 – suma pomiarów
         #3’2 – suma pomiarów podniesiona do potęgi 2
         #4 – wiek

         BF% = (457 / Body density) – 414.2
         BF% = (495 / Body density) – 450
         (wyciągamy średnią)
         */
        int gender = user.getGender();
        //Man:
        double chest = user.getChest(); //klatka
        double navel = user.getNavel(); //pepek
        //Woman:
        double triceps = user.getTriceps();
        double hip = user.getHip(); //biodro

        double thigh = user.getThigh(); //udo
        double sumMan = chest+navel+thigh;
        double sumWoman = triceps+hip+thigh;
        int age = user.getAge();

        double bodyDensity;
        if(gender == 1){ //Man
            bodyDensity = 1.1093800 - (0.0008267*sumMan) + (0.0000016*Math.pow(sumMan, 2)) - (0.0002574*age);
        }
        else{ //Woman
            bodyDensity = 1.1099421 - (0.0009929*sumWoman) + (0.0000016*Math.pow(sumWoman, 2)) - (0.0002574*age);
        }
        double bf1 = (457/bodyDensity)-414.2;
        double bf2 = (495/bodyDensity)-450;
        double[] avgBF = {bf1, bf2};

        return result = mean(avgBF);
    }
    // Obliczanie WHR
    public double obliczWHR(User user){
        double waistCircumference = user.getWaistCircumference();
        double hipCircumference = user.getHipCircumference();
        double whr = waistCircumference/hipCircumference;
        return whr;
    }

    public String wynikTxtWhr(User user){
        double whr = obliczWHR(user);
        int gender = user.getGender();

        String txt = "";

        if(gender == 1){ // Man
            if (whr >= 1) {
                txt = bundle.getString("result.whr.man1"); // Otylosc aneroidalna (jablko)
            }
            else{
                txt = bundle.getString("result.whr.man2"); // Otylosc ginoidalna (gruszka)
            }
        }
        else{ // Woman
            if (whr >= 0.8) {
                txt = bundle.getString("result.whr.woman1"); // Otylosc aneroidalna (jablko)
            }
            else{
                txt = bundle.getString("result.whr.woman2"); // Otylosc ginoidalna (gruszka)
            }
        }
        return txt;
    }
}