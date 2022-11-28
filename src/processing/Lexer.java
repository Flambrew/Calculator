package src.processing;

import java.util.ArrayList;

import src.exceptions.IllegalSyntaxException;
import src.exceptions.IllegalTokenException;
import src.tokens.Token;
import src.tokens.TokenType;

public class Lexer {

    private Integer position;

    private String text;
    private String currentCharacter;

    public Lexer(String in) {
        this.text = in.replaceAll("[\\[\\{]", "(").replaceAll("[\\]\\}]", ")");
        this.position = -1;
        this.currentCharacter = null;
        advance();
    }

    public Token[] createTokens() throws IllegalTokenException, IllegalSyntaxException {

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
                tokens.add(new Token(TokenType.ADD));
                advance();
            } else if (currentCharacter.matches("\\-")) {
                tokens.add(new Token(TokenType.SUB));
                advance();
            } else if (currentCharacter.matches("\\*")) {
                tokens.add(new Token(TokenType.MUL));
                advance();
            } else if (currentCharacter.matches("\\/")) {
                tokens.add(new Token(TokenType.DIV));
                advance();
            } else if (currentCharacter.matches("\\^")) {
                tokens.add(new Token(TokenType.POW));
                advance();
            } else if (currentCharacter.matches("\\%")) {
                tokens.add(new Token(TokenType.MOD));
                advance();
            } else if (currentCharacter.matches("\\!")) {
                tokens.add(new Token(TokenType.FACT));
                advance();
            } else if (currentCharacter.matches("\\(")) {
                tokens.add(new Token(TokenType.LPAREN));
                advance();
            } else if (currentCharacter.matches("\\)")) {
                tokens.add(new Token(TokenType.RPAREN));
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

    private Token parseWord() {
        int length = 0;
        String out = "";
        while (currentCharacter != null && currentCharacter.matches("[a-zA-Z]")) {
            length++;
            out += currentCharacter;
            advance();
        }
        if (length == 1) {
            return new Token(TokenType.VAR, out);
        }
        if (out.matches("SIN")) {
            return new Token(TokenType.SIN);
        }
        if (out.matches("COS")) {
            return new Token(TokenType.COS);
        }
        if (out.matches("TAN")) {
            return new Token(TokenType.TAN);
        }
        if (out.matches("ABS")) {
            return new Token(TokenType.ABS);
        }
        return null; // TODO: add more word things
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
        return new Token(TokenType.NUM, Double.parseDouble(out));
    }

    private void advance() {
        position++;
        currentCharacter = position < text.length() ? ("" + text.charAt(position)).toUpperCase() : null;
    }
}
