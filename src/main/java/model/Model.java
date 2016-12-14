package model;

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

    // typu budowy sylwetki

    //Wskaźnik wagowo-wzrostowy Queteleta I
    //Oblicza się go dzieląc masę ciała w gramach przez wzrost [B-v] w cm.
    public double obliczWQueteleta(User user){
        double weight = user.getWeight(); //g
        double height = user.getHeight(); // cm

        double wq = (weight*1000)/height;
        return wq;
    }
    public String wynikTxtWQueteleta(User user){
        double wq = obliczWQueteleta(user);
        int gender = user.getGender();
        String txt = "";
        if(gender == 1){ // Man
            if (wq <= 300) {
                txt = bundle.getString("result.wq.man1"); //wychudzenie
            }
            else if(wq <=319){
                txt = bundle.getString("result.wq.man2"); //budowa bardzo słaba
            }
            else if(wq <=349){
                txt = bundle.getString("result.wq.man3"); //budowa słaba
            }
            else if(wq <=369){
                txt = bundle.getString("result.wq.man4"); //budowa średnio mocna
            }
            else if(wq <=399){
                txt = bundle.getString("result.wq.man5"); //budowa mocna
            }
            else if(wq <=499){
                txt = bundle.getString("result.wq.man6"); //budowa bardzo mocna
            }
            else{
                txt = bundle.getString("result.wq.man7"); //otłuszczenie
            }
        }
        else{ // Woman
            if (wq <= 289) {
                txt = bundle.getString("result.wq.woman1"); //budowa bardzo słaba
            }
            else if(wq <=314){
                txt = bundle.getString("result.wq.woman2"); //budowa słaba
            }
            else if(wq <=339){
                txt = bundle.getString("result.wq.woman3"); //budowa średnio mocna
            }
            else if(wq <=369){
                txt = bundle.getString("result.wq.woman4"); //budowa mocna
            }
            else{
                txt = bundle.getString("result.wq.woman5"); //budowa bardzo mocna
            }
        }
        return txt;
    }
    //Wskaźnik budowy ciała Rohrera
    //Oblicza się go dzieląc masę ciałą (w gramach) przez wzrost [B-v] pomnożony do potęgi trzeciej i mnożąc wynik przez 100.
    public double obliczWRohrera(User user){
        double weight = user.getWeight(); //g
        double height = user.getHeight(); // cm

        double wr = ((weight*1000)/Math.pow(height, 3))*100;
        return wr;
    }
    public String wynikTxtWgWankegoKolasy(User user){
        double wr = obliczWRohrera(user);
        int gender = user.getGender();
        String txt = "";
        if(gender == 1){ // Man
            if(wr <=1.24){
                txt = bundle.getString("result.wr.wank.man1"); //smukly
            }
            else if(wr <=1.36){
                txt = bundle.getString("result.wr.wank.man2"); //sredni
            }
            else if(wr >=1.37){
                txt = bundle.getString("result.wr.wank.man3"); //tegi
            }
        }
        else{ // Woman
            if (wr <= 1.37) {
                txt = bundle.getString("result.wr.wank.woman1"); //smukly
            }
            else if(wr <=1.58){
                txt = bundle.getString("result.wr.wank.woman2"); //sredni
            }
            else if(wr >=1.59){
                txt = bundle.getString("result.wr.wank.woman3"); //tegi
            }
        }
        return txt;
    }
    public String wynikTxtWgKowalewskiej(User user){
        double wr = obliczWRohrera(user);
        int gender = user.getGender();
        String txt = "";
        if(gender == 1){ // Man
            if(wr <=1.13){
                txt = bundle.getString("result.wr.kowal.man1"); //typ leptosomiczny
            }
            else if(wr <=1.34){
                txt = bundle.getString("result.wr.kowal.man2"); //typ atletyczny
            }
            else if(wr >=1.35){
                txt = bundle.getString("result.wr.kowal.man3"); //typ pykniczny
            }
        }
        else{ // Woman
            if (wr <= 1.23) {
                txt = bundle.getString("result.wr.kowal.woman1"); //typ leptosomiczny
            }
            else if(wr <=1.43){
                txt = bundle.getString("result.wr.kowal.woman2"); //typ atletyczny
            }
            else if(wr >=1.44){
                txt = bundle.getString("result.wr.kowal.woman3"); //typ pykniczny
            }
        }
        return txt;
    }
    //Wskaźnik smukłości.  Używany przy wyróżnianiu typów budowy w systemie Sheldona.
    //Oblicza się go dzieląc wzrost [B-v] w cm, przez pierwiastek sześcienny wagi w kilogramach.
    public double obliczWSheldona(User user){
        double weight = user.getWeight(); //g
        double height = user.getHeight(); // cm

        double ws = height/Math.cbrt(weight);
        return ws;
    }
    public String wynikTxtWgSheldona(User user){
        double ws = obliczWRohrera(user);
        int gender = user.getGender();
        String txt = "";
        if(gender == 1){ // Man
            if(ws <=39.5){
                txt = bundle.getString("result.wr.shel.man1"); //Budowa tęga (endomorfia)
            }
            else if(ws <=43.2){
                txt = bundle.getString("result.wr.shel.man2"); //Budowa średnia (mezomorfia)
            }
            else if(ws >=43.3){
                txt = bundle.getString("result.wr.shel.man3"); //Budowa smukła (ektomorfia)
            }
        }
        else{ // Woman
            if (ws <= 40.7) {
                txt = bundle.getString("result.wr.shel.woman1"); //Budowa tęga (endomorfia
            }
            else if(ws <=44.2){
                txt = bundle.getString("result.wr.shel.woman2"); //Budowa średnia (mezomorfia)
            }
            else if(ws >=42.3){
                txt = bundle.getString("result.wr.shel.woman3"); //Budowa smukła (ektomorfia)
            }
        }
        return txt;
    }
}