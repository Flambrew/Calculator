package src;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function_Alpha {

    private ArrayList<ArrayList<String>> expressions = new ArrayList<ArrayList<String>>(4);
    private String[][] ops = { { "^", "*", "/", "+", "-" }, { "$", "%", "#", "@" } };
    private String func;

    public Function_Alpha(String func) {
        for (int i = 0; i < 4; i++)
            expressions.add(new ArrayList<>());
        this.func = func;
        parse(func);
    }

    public Double calculate(double x, double... vars) {
        parse(func);
        if (vars.length != expressions.get(0).size() - 1)
            return null;
        for (int i = 0; i < expressions.get(0).size(); i++)
            expressions.get(0).set(i, i == 0 ? "" + x : "" + vars[i - 1]);
        for (int i = 1; i <= 3; i++)
            for (int j = 0; j < expressions.get(i).size(); j++) {
                for (int k = 0; k < 8; k++) {
                    String str = expressions.get(i).get(j);
                    if (str.matches(String.format(".*\\%s[0-9]+.*", ops[1][k / 2]))) {
                        int[] loc = regExIndex(String.format("\\%s[0-9]+", ops[1][k / 2]), str, 0);
                        expressions.get(i).set(j, str.substring(0, loc[0]) + expressions.get(k / 2)
                                .get(Integer.parseInt(str.substring(loc[0] + 1, loc[1]))) + str.substring(loc[1]));
                    }
                }
                for (int k = 0; k < 5; k++)
                    if (expressions.get(i).get(j).indexOf(ops[0][k]) > 0) {
                        double a = Double.parseDouble(expressions.get((k + 3) / 2).get(j).split("\\" + ops[0][k])[0]);
                        double b = Double.parseDouble(expressions.get((k + 3) / 2).get(j).split("\\" + ops[0][k])[1]);
                        expressions.get(i).set(j, "" +
                                (k == 0 ? Math.pow(a, b) : k == 1 ? a * b : k == 2 ? a / b : k == 3 ? a + b : a - b));
                    }
            }
        return Double.parseDouble(expressions.get(3).get(expressions.get(3).size() - 1));
    }

    private void parse(String func) {
        for (int i = 0; i < 4; i++)
            expressions.set(i, new ArrayList<String>());
        func = validate(func).replace("x", "$0");
        expressions.get(0).add("$0");
        for (int i = 0; i < func.split(" ").length; i++) {
            String s = func.split(" ")[i];
            if (s.matches("[a-zA-Z]+")) {
                func = func.replace(s, String.format("$%d", expressions.get(0).size()));
                expressions.get(0).add(String.format("$%s", expressions.get(0).size()));
            }
        }
        for (int i = 0; i < ops[0].length; i++)
            for (int j = (expressions.get(1 + ((i + 1) / 2)).size()); func.contains(ops[0][i]); j++) {
                int location = func.lastIndexOf(ops[0][i]);
                int start = func.lastIndexOf(" ", location - 2) + 1, finish = func.indexOf(" ", location + 3);
                expressions.get(1 + ((i + 1) / 2)).add(func.substring(start, finish).replaceAll(" ", ""));
                func = func.substring(0, start) + ops[1][(i + 3) / 2] + j + func.substring(finish);
            }
        func = func.substring(2, 4);
    }

    private String validate(String func) {
        String temp = "";
        func = '<' + func.replaceAll(" ", "").substring(func.indexOf('=')) + '>';
        if (!func.matches("[-+/*^0-9a-zA-Z()<>]+"))
            return null;
        for (char ch : func.toCharArray())
            temp += ("" + ch).matches("[-+/*^<>]") ? ((ch == '<' ? "" : " ") + ch + (ch == '>' ? "" : " ")) : ch;
        for (String s : temp.split(" "))
            if (!(s.matches("[-+/*^<>]") ^ s.matches("[0-9]+") ^ s.matches("[a-zA-Z]+")))
                return null;
        return temp;
    }

    private int[] regExIndex(String pattern, String text, Integer fromIndex) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if ((fromIndex != null && matcher.find(fromIndex)) || matcher.find())
            return new int[] { matcher.start(), matcher.end() };
        return new int[] { -1, -1 };
    }

    public String getOperation(String op) {
        if (op.charAt(0) == '%')
            return expressions.get(1).get(Integer.parseInt("" + op.charAt(1)));
        else if (op.charAt(0) == '#')
            return expressions.get(2).get(Integer.parseInt("" + op.charAt(1)));
        else if (op.charAt(0) == '@')
            return expressions.get(3).get(Integer.parseInt("" + op.charAt(1)));
        return null;
    }

    public String getOperation(int op, int index) {
        return expressions.get(op).get(index);
    }

    public int getVariablesLength() {
        return expressions.get(3).size();
    }

    public int getAddSubLength() {
        return expressions.get(2).size();
    }

    public int getMulDivLength() {
        return expressions.get(1).size();
    }

    public int getExpLength() {
        return expressions.get(0).size();
    }

    public int getListLength(int op) {
        return expressions.get(op).size();
    }

    public String getFunction() {
        return func;
    }
}