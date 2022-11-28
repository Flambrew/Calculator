package src.tokens;

public enum TT {
    ADD(TGroup.OPERATION),
    SUB(TGroup.OPERATION),
    MUL(TGroup.OPERATION),
    DIV(TGroup.OPERATION),
    POW(TGroup.OPERATION),
    MOD(TGroup.OPERATION),
    SIN(TGroup.PRE_FUNC),
    COS(TGroup.PRE_FUNC),
    TAN(TGroup.PRE_FUNC),
    ABS(TGroup.PRE_FUNC),
    FACT(TGroup.POST_FUNC),
    LPAREN(TGroup.PAREN),
    RPAREN(TGroup.PAREN),
    NUM(TGroup.VALUE),
    VAR(TGroup.VALUE);

    private TGroup group;

    TT(TGroup group) {
        this.group = group;
    }

    public boolean isA(TGroup group) {
        return this.group == group;
    }
}