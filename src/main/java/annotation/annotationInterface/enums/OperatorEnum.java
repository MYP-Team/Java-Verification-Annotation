package annotation.annotationInterface.enums;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public enum OperatorEnum {

    Equal("Equal","=="),UnEqual("UnEqual","!="),GreaterOrEqual("GreaterOrEqual",">="),Greater("Greater",">"),
    LessOrEqual("LessOrEqual","<="),Less("Less","<"),IsNotBlank("IsNotBlank",".IsNotBlank"),IsBlank("IsBlank",".IsBlank");

    @Getter
    private final String name;
    @Getter
    private final String value;

    private OperatorEnum(String name,String value) {
        this.name=name;
        this.value=value;
    }

    public static List<String> getValues(){
        return Arrays.stream(OperatorEnum.values()).map(OperatorEnum::getValue).collect(Collectors.toList());
    }
}
