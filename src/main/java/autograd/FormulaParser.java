// package autograd;
//
// import autograd.autodiff.*;
//
// public class FormulaParser {
//    public static void main(String[] args) {
//        String formula = "((x+y)+(z*x))+(3/y)";
//
//        parse(formula);
//
//    }
//
//    public static void parse(String s){
//        s = s.replaceAll(" ", "");
//
//        int in = 0;
//    }
//
//    public Term parseEx(String ex){
//        String t1 = "";
//        String t2 = "";
//
//        int in = 0;
//        for(int i = 0;i<ex.length();i++){
//            char c = ex.charAt(i);
//            if(c == '(') in++;
//            else if(c == ')') in--;
//
//        }
//
//    }
//
//    public boolean isOper(char r){
//        return "*=-/^".contains(r+"");
//    }
//
// }
