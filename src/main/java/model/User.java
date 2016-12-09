package model;


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
    private double activity; // 1.2 , 1.4 , 1.6 , 1.75 , 2.0 , 2.2


    public void getAtributes(ViewModel viewModel){
        // Gender
        if(viewModel.isGenderManProperty()){
            gender=1;
        }
        else {
            gender=-1;
        }
        // Age, weight, height
        age = viewModel.getAgeProperty();
        this.weight = viewModel.getWeightProperty();
        this.height = viewModel.getHeightProperty();
        // Activity
        switch (viewModel.getActivityIndexProperty()){
            case 0:
                activity = 1.2;
                break;
            case 1:
                activity = 1.4;
                break;
            case 2:
                activity = 1.6;
                break;
            case 3:
                activity = 1.75;
                break;
            case 4:
                activity = 2.0;
                break;
            case 5:
                activity = 2.2;
                break;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "gender=" + gender +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", activity=" + activity +
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

    public double getActivity() {
        return activity;
    }
}
