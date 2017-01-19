import java.lang.reflect.Field;

/**
 * @author Michlu
 * @sience 2017-01-19
 */
public class TestReflectionField {
    public static void setFieldValue(Object object, String field, int value){
        try {

        Field f = object.getClass().getDeclaredField(field);
        f.setAccessible(true);
        f.set(object, value);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public static void setFieldValue(Object object, String field, double value){
        try {

            Field f = object.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(object, value);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
