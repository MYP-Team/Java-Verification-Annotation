package entrys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Logic {
    private String left;
    private String symbol;
    private String right;
    //成功是返回，为null时表示最终结果为true
    private Logic sucLogic;
    //失败是返回，为null时表示最终结果为false
    private Logic failLogic;

    //在logic树的每一层失败分支子树上都加一个成功时调用的对象
    public void setSucLogicEveryFail(Logic logic){
        Logic logic1 = this;
        while (true){
            logic1.setSucLogic(logic);
            if (logic1.getFailLogic() != null){
                logic1 = logic1.getFailLogic();
            }else {
                return;
            }
        }
    }
    //在logic树的每一层成功分支上子树上都加一个失败时调用的对象
    public void setFailLogicEverySuc(Logic logic){
        Logic logic1 = this;
        while (true){
            logic1.setFailLogic(logic);
            if (logic1.getSucLogic() != null){
                logic1 = logic1.getSucLogic();
            }else {
                return;
            }
        }
    }
}
