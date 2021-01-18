package AnnotationTest;

import annotation.annotationInterface.Number;
import annotation.annotationInterface.factory.CheckAnnotationFactory;
import entrys.Person;
import excaption.BusinessException;
import lombok.Data;
import org.drools.core.command.assertion.AssertEquals;
import org.junit.Assert;
import org.junit.Test;

public class DefaultNumberTest {
    @Test
    public void MinNumberTest() {
        try {
            DefaultNumberClass number = new DefaultNumberClass();
            number.setNumber(DefaultNumberClass.Min - 1);
            CheckAnnotationFactory.verify(number);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail();
    }

    @Test
    public void MaxNumberTest() {
        try {
            DefaultNumberClass number = new DefaultNumberClass();
            number.setNumber(DefaultNumberClass.Max + 1);
            CheckAnnotationFactory.verify(number);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail();
    }

    @Test
    public void ExceptionMessageTest() {
        try {
            DefaultNumberClass number = new DefaultNumberClass();
            number.setNumber(DefaultNumberClass.Max + 1);
            CheckAnnotationFactory.verify(number);
        } catch (BusinessException e) {
            Assert.assertEquals(DefaultNumberClass.Message, e.getMessage());
            Assert.assertEquals(DefaultNumberClass.Code, e.getCode());
            return;
        }
        Assert.fail();
    }

    @Test
    public void ConditionTest() {
        try {
            DefaultNumberClass number = new DefaultNumberClass();
            number.setDoCondition(false);
            CheckAnnotationFactory.verify(number);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}

@Data
class DefaultNumberClass {
    public static final int Min = 0;
    public static final int Max = 10;
    public static final String Message = "TestMessage";
    public static final String Code = "TestCode";
    public static final String Conditions = "this.DoCondition == true";

    @annotation.annotationInterface.Number(min = Min, max = Max, message = Message, code = Code, conditions = Conditions)
    int Number;

    boolean DoCondition = false;
}
