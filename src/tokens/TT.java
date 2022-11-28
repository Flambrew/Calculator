package src.tokens;

public enum TT {

    NUM(TGroup.VALUE),
    VAR(TGroup.VALUE),

    SIN(TGroup.PREFIX),
    COS(TGroup.PREFIX),
    TAN(TGroup.PREFIX),
    CSC(TGroup.PREFIX),
    SEC(TGroup.PREFIX),
    COT(TGroup.PREFIX),
    ABS(TGroup.PREFIX),
    LOG(TGroup.PREFIX),
    EXP(TGroup.PREFIX),

    FAC(TGroup.SUFFIX),

    LPAREN(TGroup.PAREN),
    RPAREN(TGroup.PAREN),

    ADD(TGroup.OPERATION),
    SUB(TGroup.OPERATION),
    MUL(TGroup.OPERATION),
    DIV(TGroup.OPERATION),
    POW(TGroup.OPERATION),
    MOD(TGroup.OPERATION);

    private TGroup group;

    TT(TGroup group) {
        this.group = group;
    }

    public boolean isA(TGroup group) {
        return this.group == group;
    }
}