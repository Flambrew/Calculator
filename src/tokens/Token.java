package src.tokens;

public class Token {

    public final TT TOKEN_TYPE;
    public final Double VALUE;
    public final String NAME;

    public Token(TT tokenType) {
        this.TOKEN_TYPE = tokenType;
        this.VALUE = null;
        this.NAME = null;
    }

    public Token(TT tokenType, double value) {
        this.TOKEN_TYPE = tokenType;
        this.VALUE = value;
        this.NAME = null;
    }

    public Token(TT tokenType, String name) {
        this.TOKEN_TYPE = tokenType;
        this.VALUE = null;
        this.NAME = name;
    }
    
    public boolean isA(TT... tokenType) {
        for (int i = 0; i < tokenType.length; i++) {
            if (this.TOKEN_TYPE == tokenType[i]) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isA(TGroup... group) {
        for (int i = 0; i < group.length; i++) {
            if (this.TOKEN_TYPE.isA(group[i])) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "" + (VALUE != null ? TOKEN_TYPE + ":" + VALUE : NAME != null ? TOKEN_TYPE + ":" + NAME : TOKEN_TYPE);
    }
}