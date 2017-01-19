/**
 * @author Michlu
 * @sience 2017-01-19
 */
import model.User;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import model.Model;

import java.util.ResourceBundle;

public class TestModel {
    User user;
    Model model;
    ResourceBundle bundle;

    private static boolean doublesAreEqual(double expected, double actual) {
        return Double.doubleToLongBits(expected) == Double.doubleToLongBits(actual);
    }


    @Before
    public void setUp(){
        user = new User();
        model = new Model();
        bundle = ResourceBundle.getBundle("bundles.model");

        TestReflectionField.setFieldValue(user, "gender", 1);
        TestReflectionField.setFieldValue(user, "age", 30);
        TestReflectionField.setFieldValue(user, "height", 180.0);
        TestReflectionField.setFieldValue(user, "weight", 80.0);
        TestReflectionField.setFieldValue(user, "waistCircumference", 80.0);
        TestReflectionField.setFieldValue(user, "hipCircumference", 90.0);
        TestReflectionField.setFieldValue(user, "activity", 1.2);
        TestReflectionField.setFieldValue(user, "chest", 20.0);
        TestReflectionField.setFieldValue(user, "navel", 20.0);
        TestReflectionField.setFieldValue(user, "triceps", 20.0);
        TestReflectionField.setFieldValue(user, "hip", 20.0);
        TestReflectionField.setFieldValue(user, "thigh", 20.0);
    }

    @Test
    public void userTest(){
        assertEquals("gender", 1, user.getGender());
        assertEquals("age",30, user.getAge());
        assertTrue("height", doublesAreEqual(180.0, user.getHeight()));
        assertTrue("weight", doublesAreEqual(80.0, user.getWeight()));
        assertTrue("waistCircumference", doublesAreEqual(80.0, user.getWaistCircumference()));
        assertTrue("hipCircumference", doublesAreEqual(90.0, user.getHipCircumference()));
        assertTrue("activity", doublesAreEqual(1.2, user.getActivity()));
        assertTrue("chest", doublesAreEqual(20.0, user.getChest()));
        assertTrue("navel", doublesAreEqual(20.0, user.getNavel()));
        assertTrue("triceps", doublesAreEqual(20.0, user.getTriceps()));
        assertTrue("hip", doublesAreEqual(20.0, user.getHip()));
        assertTrue("thigh", doublesAreEqual(20.0, user.getThigh()));
    }

    @Test
    public void roundDoubleTest(){
        assertEquals("zaokraglanie", 50.55, Model.roundDouble(50.555555), 0.1);
    }

    @Test
    public void meanTest() throws Exception {
        double[] twoNumber = {12.0, 24.0};
        double[] threeNumber = {5.0, 4.0, 3.0};
        assertTrue("mean - twoNumber", doublesAreEqual(18.0, Model.mean(twoNumber)));
        assertTrue("mean - threeNumber", doublesAreEqual(4.0, Model.mean(threeNumber)));
    }

    @Test
    public void obliczBmiTest() throws Exception {
        assertEquals("BMI", 24.69, model.obliczBmi(user), 0.01);
    }

    @Test
    public void wagaMinimalnaTest() throws Exception {
        assertEquals("waga minimalna",59.94, model.wagaMinimalna(user), 0.01);
    }

    @Test
    public void wagaMaksymalnaTest() throws Exception {
        assertEquals("waga maksymalna", 80.6, model.wagaMaksymalna(user), 0.1);
    }

    @Test
    public void wynikTxtBmiTest() throws Exception {
        String expected = bundle.getString("result.bmi.normal");
        assertEquals("wynik tekstowy bmi", expected, model.wynikTxtBmi(user));
    }

    @Test
    public void wagaNaleznaTest() throws Exception {
        assertEquals("waga nalezna", 66.42, model.wagaNalezna(user), 0.01);
    }

    @Test
    public void obliczWzorBrocaTest() throws Exception {
        assertEquals("wzor broca -man", 72.0, model.obliczWzorBroca(user), 0.01);
        TestReflectionField.setFieldValue(user, "gender", -1); // woman
        assertEquals("wzor broca -woman", 68.0, model.obliczWzorBroca(user), 0.01);
    }

    @Test
    public void obliczWzorBrocaBrugshaTest() throws Exception {
        assertEquals("wzor broca brugsha", 70.0, model.obliczWzorBrocaBrugsha(user), 0.01);
    }

    @Test
    public void obliczWzorPottonaTest() throws Exception {
        assertEquals("wzor Pottona -man", 76.0, model.obliczWzorPottona(user), 0.01);
        TestReflectionField.setFieldValue(user, "gender", -1); // woman
        assertEquals("wzor Pottona -woman", 72.0, model.obliczWzorPottona(user), 0.01);
    }

    @Test
    public void obliczWzorLorenzaTest() throws Exception {
        assertEquals("wzor Lorenza -man", 72.5, model.obliczWzorLorenza(user), 0.01);
        TestReflectionField.setFieldValue(user, "gender", -1); // woman
        assertEquals("wzor Lorenza -woman", 65.0, model.obliczWzorLorenza(user), 0.01);
    }

    @Test
    public void obliczWzorATUnZTest() throws Exception {
        assertEquals("wzor ATUnZ", 72.5, model.obliczWzorATUnZ(user), 0.01);
    }

    @Test
    public void obliczPpmMifflinTest() throws Exception {
        assertEquals("PPM Mifflin -man", 1780.0, model.obliczPpmMifflin(user), 1);
        TestReflectionField.setFieldValue(user, "gender", -1); // woman
        assertEquals("PPM Mifflin -woman", 1614.0, model.obliczPpmMifflin(user), 1);
    }

    @Test
    public void obliczPpmHarrisBenedictTest() throws Exception {
        assertEquals("PPM HarrisBenedict -man", 1864.0, model.obliczPpmHarrisBenedict(user), 1);
        TestReflectionField.setFieldValue(user, "gender", -1); // woman
        assertEquals("PPM HarrisBenedict -woman", 1613.0, model.obliczPpmHarrisBenedict(user), 1);
    }

    @Test
    public void obliczBFTest() throws Exception {
        assertEquals("BF -man", 17.88, model.obliczBF(user), 0.1);
        TestReflectionField.setFieldValue(user, "gender", -1); // woman
        assertEquals("BF -woman", 21.92, model.obliczBF(user), 0.1);
    }

    @Test
    public void obliczWHRTest() throws Exception {
        assertEquals("WHR", 0.89, model.obliczWHR(user), 0.01);
    }

    @Test
    public void wynikTxtWhr() throws Exception {

    }

    @Test
    public void obliczWQueteletaTest() throws Exception {
        assertEquals("Quetelet", 444.4, model.obliczWQueteleta(user), 0.1);
    }

    @Test
    public void wynikTxtWQueteleta() throws Exception {
    }

    @Test
    public void obliczWRohrera() throws Exception {
        assertEquals("Rohrer", 1.37, model.obliczWRohrera(user), 0.01);
    }

    @Test
    public void wynikTxtWgWankegoKolasy() throws Exception {

    }

    @Test
    public void wynikTxtWgKowalewskiej() throws Exception {

    }

    @Test
    public void obliczWSheldona() throws Exception {
        assertEquals("Sheldon", 41.77, model.obliczWSheldona(user), 0.01);
    }

    @Test
    public void wynikTxtWgSheldona() throws Exception {

    }





}
