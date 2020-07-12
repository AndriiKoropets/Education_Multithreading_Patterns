package education.multithreading.concurrent.java8;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.stream.Stream;

interface XmlSerializer {
    default String toXml() throws IllegalAccessException {
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        fields[0].setAccessible(true);
        String result = "";
        result += "<" + clazz.getSimpleName() + ">";
        result += fields[0].getName() + " = " + fields[0].get(this);
        result += "</" + clazz.getSimpleName() + ">";
        return result;
    }
}

class User implements XmlSerializer, Serializable {
    private int age = 42;
    private String name = "Mike";
}

public class App04 {
    public static void main(String[] args) throws IllegalAccessException {

        System.out.println(new User().toXml());

        Stream.iterate(0L, k -> k+1)
                .parallel()
                .filter(k -> k % 3 == 2)
                .map(k -> "~" + k)
                .limit(10)
                .forEach(k -> System.out.println(Thread.currentThread()));
    }
}
