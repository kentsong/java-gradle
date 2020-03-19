package reflect;

import java.lang.reflect.Field;

public class PersonReflectDemo {

    public static void main(String[] args) {
        Person p1 = new Person("wang");
        try {
            Field field = Person.class.getDeclaredField("age");
            field.setAccessible(true);
            field.set(null, 20);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        Person p2 = new Person("kent");

        try {
            Field field = p2.getClass().getDeclaredField("age");
            field.setAccessible(true);
            field.set(p2, 12);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("p1="+p1.toString());
        System.out.println("p2="+p2.toString());

    }
}
