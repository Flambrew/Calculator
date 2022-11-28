package src.processing;

public class Variable {
    private final Character NAME;
    private final Double VALUE;

    public Variable(char name, double value) {
        this.NAME = name;
        this.VALUE = value;
    }

    public Character getNAME() {
        return NAME;
    }

    public Double getVALUE() {
        return VALUE;
    }
}
