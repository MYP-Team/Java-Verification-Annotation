import annotation.annotationInterface.enums.OperatorEnum;
import annotation.annotationInterface.factory.CheckAnnotationFactory;
import entrys.Person;


public class Test {
    public static void main(String[] args) {
        try {
            Person person = new Person();
            person.setName("caog2");
            person.setAge("1");
            person.setBirthDay("2012-03-05");
            CheckAnnotationFactory.verify(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
