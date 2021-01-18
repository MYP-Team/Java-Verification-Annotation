package entrys;

import annotation.annotationInterface.*;
import annotation.annotationInterface.Number;
import lombok.*;

@ToString
@Data
@AllArgsConstructor
@Builder
public class Person {

    @NotBlank(code="DSF3455",message = "姓名不能为空",conditions = "this.age==1")
    @Select(value={"1","2"},code="DSF3455",message = "姓名只能为1，2中的一个")
    private String name;
    @NotBlank(code="DSF3456",message = "年龄不能为空")
    @Number(code="DSF3457", min=0,max=100,message = "年龄的长度必须大于0小于100")
    private String age;
    @DateTimeValidate(pattern = "yyyy-MM-dd",code="DSF3455")
    private String birthDay;

    public Person() {
    }
}
