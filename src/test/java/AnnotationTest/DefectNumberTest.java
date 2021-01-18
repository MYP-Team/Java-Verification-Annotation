package AnnotationTest;

import annotation.annotationInterface.Number;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

public class DefectNumberTest {

    @Test
    public void InValidRangeAnnotationTest() {
        try {
            DefectNumber number = new DefectNumber();
        } catch (Exception e) {
            return;
        }

        Assert.fail();
    }

}

@Data
class DefectNumber {
    public static final int Max = -1;
    public static final int Min = 0;

    @annotation.annotationInterface.Number(min = Min, max = Max)
    int Number;

}