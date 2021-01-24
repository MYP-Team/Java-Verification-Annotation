package annotation.annotationInterface.enums;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public enum ConstEnum {

    This("This","this."),NullStr("NullStr",""),SplitOr("SplitOr","\\|\\|");

    @Getter
    private final String name;
    @Getter
    private final String value;

    private ConstEnum(String name, String value) {
        this.name=name;
        this.value=value;
    }

    public static List<String> getValues(){
        return Arrays.stream(ConstEnum.values()).map(ConstEnum::getValue).collect(Collectors.toList());
    }
}
