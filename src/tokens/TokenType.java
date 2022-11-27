package src.tokens;

public enum TokenType {
    ADD(Group.OPERATION),
    SUB(Group.OPERATION),
    MUL(Group.OPERATION),
    DIV(Group.OPERATION),
    POW(Group.OPERATION),
    MOD(Group.OPERATION),
    SIN(Group.PRE_FUNC),
    COS(Group.PRE_FUNC),
    TAN(Group.PRE_FUNC),
    ABS(Group.PRE_FUNC),
    FACTORIAL(Group.POST_FUNC),
    LPAREN(Group.PAREN),
    RPAREN(Group.PAREN),
    NUM(Group.VALUE),
    VAR(Group.VALUE);

    private Group group;

    TokenType(Group group) {
        this.group = group;
    }

    public boolean isA(Group group) {
        return this.group == group;
    }
}