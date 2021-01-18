import annotation.annotationInterface.factory.CheckAnnotationFactory;
import entrys.Person;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) {
        try {
            Person person = new Person();
            person.setName("3");
            person.setAge("2");
            person.setBirthDay("2012-03-03");
            CheckAnnotationFactory.verify(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
