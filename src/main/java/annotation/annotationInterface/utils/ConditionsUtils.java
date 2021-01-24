package annotation.annotationInterface.utils;

import annotation.annotationInterface.enums.LogicEnum;
import annotation.annotationInterface.enums.OperatorEnum;
import entrys.Logic;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConditionsUtils {


    public static void main(String[] args) {
        Logic logic = ConditionsUtils.handleContentLogic("(a>1&&b<2)||(c>=3&&d==4)||c!=2||a.IsNotBlank");
        System.out.println(logic.toString());
    }


    public static Logic handleContentLogic(String content) {
        content = removeNoUseContent(content);
        List<String> blockContents=splitContent(content);
        return createLogic(blockContents);
    }



    private static String removeNoUseContent(String content){
        List<String> list = new ArrayList<>(Arrays.asList(content.split(""))) ;
        int left = 0,right = 0;
        while (true){
            if("(".equals(list.get(0))){
                left++;
                list.remove(0);
            }else {
                break;
            }
        }
        if(left > 0){
            for (int index = 0;index < list.size();index++) {
                if(left == 0){
                    break;
                }
                if("(".equals(list.get(index))){
                    right++;
                }else if(")".equals(list.get(index))){
                    if(right > 0){
                        right--;
                    }else {
                        left--;
                        list.remove(index);
                        index--;
                    }
                }
            }
        }
        return StringUtils.join(list.toArray());
    }


    private static List<String> splitContent(String content){
        List<String> blockContents = new ArrayList<>();
        int point = 0,flag = 0;
        for (int index = 0;index < content.length();index++) {
            char c = content.charAt(index);
            if('(' == c){
                flag++;
            }else if(')' == c){
                flag--;
                if(flag == 0){
                    blockContents.add( content.substring(point ,index + 1));
                    point = index + 1;
                }
            }else if(flag== 0&&('|'==content.charAt(index)||'&' == content.charAt(index))){
                if(index - point > 1){
                    blockContents.add(content.substring(point , index ));
                    point = index;
                }
            }else if(index == content.length() - 1){
                blockContents.add(content.substring(point , index + 1 ));
            }
        }
        return blockContents;
    }

    private static Logic createLogic(List<String> blockContents){
        Logic logic = null;
        for(String blockContent:blockContents){
            if(blockContent.startsWith(LogicEnum.OrBracket.getValue())){
                Logic logic1 = handleContentLogic(blockContent.substring(2));
                logic.setFailLogicEverySuc(logic1);
            }else if(blockContent.startsWith(LogicEnum.AndBracket.getValue())){
                Logic logic1 = handleContentLogic(blockContent.substring(2));
                logic.setSucLogicEveryFail(logic1);
            }else if(blockContent.startsWith(LogicEnum.And.getValue())){
                Logic logic1 = getLogicBySimpleContent(blockContent.substring(2));
                logic1.setSucLogicEveryFail(logic);
                logic = logic1;
            }else if(blockContent.startsWith(LogicEnum.Or.getValue())){
                Logic logic1 = getLogicBySimpleContent(blockContent.substring(2));
                logic1.setFailLogicEverySuc(logic);
                logic = logic1;
            }else {
                logic = getLogicBySimpleContent(blockContent);
            }
        }
        return logic;
    }


    private static Logic getLogicBySimpleContent(String blockContent) {
        Logic logic = new Logic();
        List<String> symbolList= OperatorEnum.getValues();
        for(String symbol:symbolList){
            if(blockContent.contains(symbol)){
                String left = blockContent.substring(0,blockContent.indexOf(symbol));
                String right = blockContent.substring(blockContent.indexOf(symbol) + symbol.length());
                logic.setLeft(left);
                logic.setSymbol(symbol);
                logic.setRight(right);
                return logic;
            }
        }
        return logic;
    }

}
