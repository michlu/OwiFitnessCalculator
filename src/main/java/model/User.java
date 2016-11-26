package model;

import controller.BmiController;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class User {
    private int gender; // M = 1, K = -1
    private int age;
    private double height;
    private double weight;
    private double waistCircumference;
    private double hipCircumference;


    public void getAtributes(ViewModel viewModel){
        if(viewModel.isGenderManProperty()){
            gender=1;
        }
        else {
            gender=-1;
        }
        age = viewModel.getAgeProperty();
        this.weight = viewModel.getWeightProperty();
        this.height = viewModel.getHeightProperty();
    }

    @Override
    public String toString() {
        return "User{" +
                "gender=" + gender +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", waistCircumference=" + waistCircumference +
                ", hipCircumference=" + hipCircumference +
                '}';
    }

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
}
