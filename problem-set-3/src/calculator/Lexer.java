package calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.Type;
/**
 * Calculator lexical analyzer.
 */
public class Lexer {

	/**
	 * Token in the stream.
	 */
	public static class Token {
		final Type type;
		final String text;

		Token(Type type, String text) {
			this.type = type;
			this.text = text;
		}

		Token(Type type) {
			this(type, null);
		}
	}

	@SuppressWarnings("serial")
	static class TokenMismatchException extends Exception {
	}

    static class InvalidCharException extends Exception {
    }

    public List<Token> tokens;
    private final String input;
    private int i;

    /**
        Make a calculator Lexer.
        @param input string to convert into equation tokens
    */
	public Lexer(String input) throws InvalidCharException {
        this.input = input.replace(" ", ""); //Remove all spaces
        while(next().type != Type.EOF)
        {
            //Do nothing, simply testing input string for invalid char
        }
        this.i = 0;
	}

    /**
        Modifies this object by consuming a token
        from the input stream.
        @return next token in the stream or EOF token
        if there are no more tokens in the stream.
    */
    public Token next() throws InvalidCharException {
        if(i >= input.length()) {
            return new Token(Type.EOF);
        }

        String current = input.substring(i, i+1);

        switch(current) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                //It's a number token. Find where it ends
                //Look for a char that's not a digit or decimal
                int start = i;
                Pattern p = Pattern.compile("\\d|\\.");
                while(i < input.length() &&
                        Pattern.matches("\\d|\\.", input.substring(i, i+1))) {
                    i++;
                }
                int end = i;
                return new Token(Type.NUMBER, input.substring(start, end));
            case "+":
            case "-":
            case "*":
            case "/":
                //It's an operator token.
                i++;
                return new Token(Type.OPERATOR, current);
            case "(":
            case ")":
                //It's a paren token
                i++;
                return new Token(Type.PAREN, current);
            case "p":
            case "i":
                //It's a unit token, grab the next char
                //If the next char is inappropriate, raise an exception
                String unit = input.substring(i, i+2);
                i += 2;
                if(!unit.equals("pt") && !unit.equals("in"))
                    throw new InvalidCharException();
                return new Token(Type.UNIT, unit);
            default:
                //Raise an exception for an invalid character
                throw new InvalidCharException();
        }
    }
}
