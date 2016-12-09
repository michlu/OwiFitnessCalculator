package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

/**
 * @author Michlu
 * @sience 2016-11-25
 */
public class ViewModel {

    // Button plec
    private BooleanProperty genderManProperty = new SimpleBooleanProperty(false);

    private BooleanProperty genderWomanProperty = new SimpleBooleanProperty(false);
    private BooleanProperty okGenderProperty = new SimpleBooleanProperty(false);

    // TextField wiek
    private IntegerProperty ageProperty = new SimpleIntegerProperty();
    private BooleanProperty okAgeProperty = new SimpleBooleanProperty(false);

    // TextField wzorst
    private DoubleProperty heightProperty = new SimpleDoubleProperty();
    private BooleanProperty disableHeightProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okHeigihtProperty = new SimpleBooleanProperty(false);

    // TextField waga
    private DoubleProperty weightProperty = new SimpleDoubleProperty();
    private BooleanProperty disableWeightProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okWeightProperty = new SimpleBooleanProperty(false);

    // Button Calculate
    private BooleanProperty okCalculateProperty = new SimpleBooleanProperty(false);
    private BooleanProperty disableCalculateProperty = new SimpleBooleanProperty(true);

    // CheckBox aktywnosc
    private IntegerProperty activityIndexProperty = new SimpleIntegerProperty();


    public ViewModel(){
            okGenderProperty.bind(genderManProperty.not().and(genderWomanProperty.not()));
            okAgeProperty.bind(ageProperty.isNotEqualTo(0));
            okHeigihtProperty.bind(heightProperty.isNotEqualTo(0));
            okWeightProperty.bind(weightProperty.isNotEqualTo(0));

            disableHeightProperty.bind(ageProperty.isEqualTo(0));
            disableWeightProperty.bind(heightProperty.isEqualTo(0));
            disableCalculateProperty.bind(weightProperty.isEqualTo(0));


    }

    public int getActivityIndexProperty() {
        return activityIndexProperty.get();
    }

    public IntegerProperty activityIndexPropertyProperty() {
        return activityIndexProperty;
    }

    public void setActivityIndexProperty(int activityIndexProperty) {
        this.activityIndexProperty.set(activityIndexProperty);
    }


    public boolean isGenderManProperty() {
        return genderManProperty.get();
    }

    public BooleanProperty genderManPropertyProperty() {
        return genderManProperty;
    }

    public boolean isGenderWomanProperty() {
        return genderWomanProperty.get();
    }

    public BooleanProperty genderWomanPropertyProperty() {
        return genderWomanProperty;
    }

    public boolean isOkGenderProperty() {
        return okGenderProperty.get();
    }

    public BooleanProperty okGenderPropertyProperty() {
        return okGenderProperty;
    }

    public int getAgeProperty() {
        return ageProperty.get();
    }

    public IntegerProperty agePropertyProperty() {
        return ageProperty;
    }

    public boolean isOkAgeProperty() {
        return okAgeProperty.get();
    }

    public BooleanProperty okAgePropertyProperty() {
        return okAgeProperty;
    }

    public double getHeightProperty() {
        return heightProperty.get();
    }

    public DoubleProperty heightPropertyProperty() {
        return heightProperty;
    }

    public boolean isDisableHeightProperty() {
        return disableHeightProperty.get();
    }

    public BooleanProperty disableHeightPropertyProperty() {
        return disableHeightProperty;
    }

    public boolean isOkHeigihtProperty() {
        return okHeigihtProperty.get();
    }

    public BooleanProperty okHeigihtPropertyProperty() {
        return okHeigihtProperty;
    }

    public double getWeightProperty() {
        return weightProperty.get();
    }

    public DoubleProperty weightPropertyProperty() {
        return weightProperty;
    }

    public boolean isDisableWeightProperty() {
        return disableWeightProperty.get();
    }

    public BooleanProperty disableWeightPropertyProperty() {
        return disableWeightProperty;
    }

    public boolean isOkWeightProperty() {
        return okWeightProperty.get();
    }

    public BooleanProperty okWeightPropertyProperty() {
        return okWeightProperty;
    }

    public boolean isOkCalculateProperty() {
        return okCalculateProperty.get();
    }

    public BooleanProperty okCalculatePropertyProperty() {
        return okCalculateProperty;
    }

    public boolean isDisableCalculateProperty() {
        return disableCalculateProperty.get();
    }

    public BooleanProperty disableCalculatePropertyProperty() {
        return disableCalculateProperty;
    }
}
