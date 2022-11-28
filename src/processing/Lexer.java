package src.processing;

import java.util.ArrayList;

import src.exceptions.IllegalSyntaxException;
import src.exceptions.IllegalTokenException;

import src.tokens.Token;
import src.tokens.TT;

public class Lexer {

    private String text;
    private String currentCharacter;
    private Integer position;

    public Token[] createTokens(String in) throws IllegalTokenException, IllegalSyntaxException {
        this.text = in.replaceAll("[\\[\\{]", "(").replaceAll("[\\]\\}]", ")");
        this.position = -1;
        advance();
        ArrayList<Token> tokens = new ArrayList<Token>();
        while (currentCharacter != null) {
            if (!verifyParens()) {
                throw new IllegalSyntaxException("Mismatched parens.");
            } else if (currentCharacter.matches("[ \t]")) {
                advance();
            } else if (currentCharacter.matches("[0-9]")) {
                tokens.add(parseNumber());
            } else if (currentCharacter.matches("[a-zA-Z]")) {
                tokens.add(parseWord());
            } else if (currentCharacter.matches("\\+")) {
                tokens.add(new Token(TT.ADD));
                advance();
            } else if (currentCharacter.matches("\\-")) {
                tokens.add(new Token(TT.SUB));
                advance();
            } else if (currentCharacter.matches("\\*")) {
                tokens.add(new Token(TT.MUL));
                advance();
            } else if (currentCharacter.matches("\\/")) {
                tokens.add(new Token(TT.DIV));
                advance();
            } else if (currentCharacter.matches("\\^")) {
                tokens.add(new Token(TT.POW));
                advance();
            } else if (currentCharacter.matches("\\%")) {
                tokens.add(new Token(TT.MOD));
                advance();
            } else if (currentCharacter.matches("\\!")) {
                tokens.add(new Token(TT.FAC));
                advance();
            } else if (currentCharacter.matches("\\(")) {
                tokens.add(new Token(TT.LPAREN));
                advance();
            } else if (currentCharacter.matches("\\)")) {
                tokens.add(new Token(TT.RPAREN));
                advance();
            } else {
                throw new IllegalTokenException(String.format("Token \"%s\" is invalid.", currentCharacter));
            }
        }
        return tokens.toArray(new Token[tokens.size()]);
    }

    private boolean verifyParens() {
        int count = 0;
        for (char ch : text.toCharArray())
            if (count < 0)
                return false;
            else if (ch == '(')
                count++;
            else if (ch == ')')
                count--;
        if (count != 0)
            return false;
        return true;
    }

    private Token parseWord() throws IllegalTokenException {
        int length = 0;
        String out = "";
        do {
            length++;
            out += currentCharacter;
            advance();
        } while (currentCharacter != null && currentCharacter.matches("[0-9a-zA-Z]"));
        if (out.matches("SIN")) {
            return new Token(TT.SIN);
        } else if (out.matches("COS")) {
            return new Token(TT.COS);
        } else if (out.matches("TAN")) {
            return new Token(TT.TAN);
        } else if (out.matches("CSC")) {
            return new Token(TT.CSC);
        } else if (out.matches("SEC")) {
            return new Token(TT.SEC);
        } else if (out.matches("COT")) {
            return new Token(TT.COT);
        } else if (out.matches("ABS")) {
            return new Token(TT.ABS);
        } else if (out.matches("LOG")) {
            return new Token(TT.LOG);
        } else if (out.matches("EXP")) {
            return new Token(TT.EXP);
        }
        return new Token(TT.VAR, out);
    }

    private Token parseNumber() {
        int decimalCount = 0;
        String out = "";
        while (currentCharacter != null && currentCharacter.matches("[0-9\\.]")) {
            if (currentCharacter.matches("\\.") && decimalCount++ == 1) {
                break;
            }
            out += currentCharacter;
            advance();
        }
        return new Token(TT.NUM, Double.parseDouble(out));
    }

    private void advance() {
        position++;
        currentCharacter = position < text.length() ? ("" + text.charAt(position)).toUpperCase() : null;
    }
}