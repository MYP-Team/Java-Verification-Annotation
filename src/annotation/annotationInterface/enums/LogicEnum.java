package annotation.annotationInterface.enums;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public enum LogicEnum {
    
    Or("Or","||"),OrBracket("OrBracket","||("),And("And","&&"),AndBracket("AndBracket","&&(");

    @Getter
    private final String name;
    @Getter
    private final String value;

    private LogicEnum(String name, String value) {
        this.name=name;
        this.value=value;
    }

    public static List<String> getValues(){
        return Arrays.stream(LogicEnum.values()).map(LogicEnum::getValue).collect(Collectors.toList());
    }
}
