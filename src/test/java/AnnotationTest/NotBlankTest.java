package AnnotationTest;

import annotation.annotationInterface.NotBlank;
import annotation.annotationInterface.factory.CheckAnnotationFactory;
import excaption.BusinessException;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

@Data
class Number {
    public static final String Message = "TestMessage";
    public static final String Code = "TestCode";

    @NotBlank(message = Message, code = Code)
    Integer number;
}

public class NotBlankTest {

    @Test
    public void InBlankTest() {
        try {
            Number number = new Number();
            CheckAnnotationFactory.verify(number);
        } catch (Exception e) {
            return;
        }

        Assert.fail();
    }

    @Test
    public void InBlankExceptionMessageTest() {
        try {
            Number number = new Number();
            CheckAnnotationFactory.verify(number);
        } catch (BusinessException e) {
            Assert.assertEquals(Number.Message, e.getMessage());
            Assert.assertEquals(Number.Code, e.getCode());
            return;
        }
        Assert.fail();
    }

}


