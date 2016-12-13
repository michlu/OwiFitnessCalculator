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

    // BMi result
    private DoubleProperty bmiProperty = new SimpleDoubleProperty();
    private BooleanProperty disableBmiProperty = new SimpleBooleanProperty(true);

    // CheckBox aktywnosc
    private IntegerProperty activityIndexProperty = new SimpleIntegerProperty();

    // Propertisy do BF ============================================================

    private DoubleProperty chestProperty = new SimpleDoubleProperty();
    private BooleanProperty disableChestProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okChestProperty = new SimpleBooleanProperty(false);

    private DoubleProperty navelProperty = new SimpleDoubleProperty();
    private BooleanProperty disableNavelProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okNavelProperty = new SimpleBooleanProperty(false);

    private DoubleProperty tricepsProperty = new SimpleDoubleProperty();
    private BooleanProperty disableTricepsProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okTricepsProperty = new SimpleBooleanProperty(false);

    private DoubleProperty hipProperty = new SimpleDoubleProperty();
    private BooleanProperty disableHipProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okHipProperty = new SimpleBooleanProperty(false);

    private DoubleProperty thighProperty = new SimpleDoubleProperty();
    private BooleanProperty disableThighProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okThighProperty = new SimpleBooleanProperty(false);

    // Button Calculate BF
    private BooleanProperty disableCalculateBfProperty = new SimpleBooleanProperty(true);

    // Propertisy do WHR =============================================================

    private DoubleProperty waistCircumferenceProperty = new SimpleDoubleProperty();
    private BooleanProperty disableWaistCircumferenceProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okWaistCircumferenceProperty = new SimpleBooleanProperty(false);

    private DoubleProperty hipCircumferenceProperty = new SimpleDoubleProperty();
    private BooleanProperty disableHipCircumferenceProperty = new SimpleBooleanProperty(true);
    private BooleanProperty okHipCircumferenceProperty = new SimpleBooleanProperty(false);

    // Button Calculate Whr
    private BooleanProperty disableCalculateWhrProperty = new SimpleBooleanProperty(true);

    public ViewModel(){
            okGenderProperty.bind(genderManProperty.not().and(genderWomanProperty.not()));
            okAgeProperty.bind(ageProperty.isNotEqualTo(0));
            okHeigihtProperty.bind(heightProperty.isNotEqualTo(0));
            okWeightProperty.bind(weightProperty.isNotEqualTo(0));

            disableHeightProperty.bind(ageProperty.isEqualTo(0));
            disableWeightProperty.bind(heightProperty.isEqualTo(0));
            disableCalculateProperty.bind(weightProperty.isEqualTo(0));

            // BF
            disableChestProperty.bind(ageProperty.isEqualTo(0));
            okChestProperty.bind(chestProperty.isNotEqualTo(0));
            disableTricepsProperty.bind(ageProperty.isEqualTo(0));
            okTricepsProperty.bind(tricepsProperty.isNotEqualTo(0));

            disableNavelProperty.bind(chestProperty.isEqualTo(0));
            okNavelProperty.bind(navelProperty.isNotEqualTo(0));
            disableHipProperty.bind(tricepsProperty.isEqualTo(0));
            okHipProperty.bind(hipProperty.isNotEqualTo(0));

            disableThighProperty.bind(navelProperty.isEqualTo(0));
            disableThighProperty.bind(hipProperty.isEqualTo(0));
            okThighProperty.bind(thighProperty.isNotEqualTo(0));

            disableCalculateBfProperty.bind(thighProperty.isEqualTo(0));

            // WHR
            disableWaistCircumferenceProperty.bind(ageProperty.isEqualTo(0));
            okWaistCircumferenceProperty.bind(waistCircumferenceProperty.isNotEqualTo(0));

            disableHipCircumferenceProperty.bind(waistCircumferenceProperty.isEqualTo(0));
            okHipCircumferenceProperty.bind(hipCircumferenceProperty.isNotEqualTo(0));

            disableCalculateWhrProperty.bind(hipCircumferenceProperty.isEqualTo(0));

            disableBmiProperty.bind(bmiPropertyProperty().isEqualTo(0));
    }
    // SETERY
    public void setActivityIndexProperty(int activityIndexProperty) {
        this.activityIndexProperty.set(activityIndexProperty);
    }

    public void setBmiProperty(double bmiProperty) {
        this.bmiProperty.set(bmiProperty);
    }



    // GETERY

    public double getBmiProperty() {
        return bmiProperty.get();
    }

    public DoubleProperty bmiPropertyProperty() {
        return bmiProperty;
    }

    public boolean isDisableBmiProperty() {
        return disableBmiProperty.get();
    }

    public BooleanProperty disableBmiPropertyProperty() {
        return disableBmiProperty;
    }

    public void setDisableBmiProperty(boolean disableBmiProperty) {
        this.disableBmiProperty.set(disableBmiProperty);
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

    public int getActivityIndexProperty() {
        return activityIndexProperty.get();
    }

    public IntegerProperty activityIndexPropertyProperty() {
        return activityIndexProperty;
    }

    public double getChestProperty() {
        return chestProperty.get();
    }

    public DoubleProperty chestPropertyProperty() {
        return chestProperty;
    }

    public boolean isDisableChestProperty() {
        return disableChestProperty.get();
    }

    public BooleanProperty disableChestPropertyProperty() {
        return disableChestProperty;
    }

    public boolean isOkChestProperty() {
        return okChestProperty.get();
    }

    public BooleanProperty okChestPropertyProperty() {
        return okChestProperty;
    }

    public double getNavelProperty() {
        return navelProperty.get();
    }

    public DoubleProperty navelPropertyProperty() {
        return navelProperty;
    }

    public boolean isDisableNavelProperty() {
        return disableNavelProperty.get();
    }

    public BooleanProperty disableNavelPropertyProperty() {
        return disableNavelProperty;
    }

    public boolean isOkNavelProperty() {
        return okNavelProperty.get();
    }

    public BooleanProperty okNavelPropertyProperty() {
        return okNavelProperty;
    }

    public double getTricepsProperty() {
        return tricepsProperty.get();
    }

    public DoubleProperty tricepsPropertyProperty() {
        return tricepsProperty;
    }

    public boolean isDisableTricepsProperty() {
        return disableTricepsProperty.get();
    }

    public BooleanProperty disableTricepsPropertyProperty() {
        return disableTricepsProperty;
    }

    public boolean isOkTricepsProperty() {
        return okTricepsProperty.get();
    }

    public BooleanProperty okTricepsPropertyProperty() {
        return okTricepsProperty;
    }

    public double getHipProperty() {
        return hipProperty.get();
    }

    public DoubleProperty hipPropertyProperty() {
        return hipProperty;
    }

    public boolean isDisableHipProperty() {
        return disableHipProperty.get();
    }

    public BooleanProperty disableHipPropertyProperty() {
        return disableHipProperty;
    }

    public boolean isOkHipProperty() {
        return okHipProperty.get();
    }

    public BooleanProperty okHipPropertyProperty() {
        return okHipProperty;
    }

    public double getThighProperty() {
        return thighProperty.get();
    }

    public DoubleProperty thighPropertyProperty() {
        return thighProperty;
    }

    public boolean isDisableThighProperty() {
        return disableThighProperty.get();
    }

    public BooleanProperty disableThighPropertyProperty() {
        return disableThighProperty;
    }

    public boolean isOkThighProperty() {
        return okThighProperty.get();
    }

    public BooleanProperty okThighPropertyProperty() {
        return okThighProperty;
    }

    public boolean isDisableCalculateBfProperty() {
        return disableCalculateBfProperty.get();
    }

    public BooleanProperty disableCalculateBfPropertyProperty() {
        return disableCalculateBfProperty;
    }

    public double getWaistCircumferenceProperty() {
        return waistCircumferenceProperty.get();
    }

    public DoubleProperty waistCircumferencePropertyProperty() {
        return waistCircumferenceProperty;
    }

    public boolean isDisableWaistCircumferenceProperty() {
        return disableWaistCircumferenceProperty.get();
    }

    public BooleanProperty disableWaistCircumferencePropertyProperty() {
        return disableWaistCircumferenceProperty;
    }

    public boolean isOkWaistCircumferenceProperty() {
        return okWaistCircumferenceProperty.get();
    }

    public BooleanProperty okWaistCircumferencePropertyProperty() {
        return okWaistCircumferenceProperty;
    }

    public double getHipCircumferenceProperty() {
        return hipCircumferenceProperty.get();
    }

    public DoubleProperty hipCircumferencePropertyProperty() {
        return hipCircumferenceProperty;
    }

    public boolean isDisableHipCircumferenceProperty() {
        return disableHipCircumferenceProperty.get();
    }

    public BooleanProperty disableHipCircumferencePropertyProperty() {
        return disableHipCircumferenceProperty;
    }

    public boolean isOkHipCircumferenceProperty() {
        return okHipCircumferenceProperty.get();
    }

    public BooleanProperty okHipCircumferencePropertyProperty() {
        return okHipCircumferenceProperty;
    }

    public boolean isDisableCalculateWhrProperty() {
        return disableCalculateWhrProperty.get();
    }

    public BooleanProperty disableCalculateWhrPropertyProperty() {
        return disableCalculateWhrProperty;
    }
}
