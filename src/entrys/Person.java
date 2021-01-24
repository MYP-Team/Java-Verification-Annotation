package entrys;

import annotation.annotationInterface.*;
import annotation.annotationInterface.Number;
import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
@CustomVerify(className="annotation.annotationInterface.impl.DiyDemo")
public class Person {

    @NotBlank(code="DSF3455",message = "姓名不能为空")
    @Select(value={"1","2"},code="DSF3455",message = "姓名只能为1，2中的一个",conditions = "(this.age>=2||this.nullTest.IsNotBlank)&&this.age==1")
    @CustomVerify(className="annotation.annotationInterface.impl.DiyDemo")
    private String name;
    @NotBlank(code="DSF3456",message = "年龄不能为空",conditions = "(this.nullTest.IsBlank||this.name>=2)&&this.age==1")
    @Number(code="DSF3457", min=0,max=100,message = "年龄的长度必须大于0小于100")
    private String age;
    @DateTimeValidate(pattern = "yyyy-MM-dd",code="DSF3455")
    @CustomVerify(className="annotation.annotationInterface.impl.DiyDemo")
    private String birthDay;
    
    private String nullTest;

    public Person() {
    }
}
