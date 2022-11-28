package src.processing;

public class Variable {
    private final String NAME;
    private final Double VALUE;

    public Variable(String name, double value) {
        this.NAME = name;
        this.VALUE = value;
    }

    public String getNAME() {
        return NAME;
    }

    public Double getVALUE() {
        return VALUE;
    }
}
