package calculator;

import java.util.*;

import calculator.Lexer;
import calculator.Type;

import static calculator.Lexer.Token;

/*
 * EQUATION ::= EXPRESSION | EXPRESSION OPERATOR EXPRESSION
 * EXPRESSION ::= (* NUMBER OPERATOR* NUMBER* )* UNIT*
 * NUMBER ::= DIGIT* .? DIGIT* UNIT?
 * UNIT ::= in | pt
 * OPERATOR ::= + | - | * | /
 * DIGIT ::= 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 0
 */

/**
 * Calculator parser. All values are measured in pt.
 */
class Parser {
	
	@SuppressWarnings("serial")
	static class ParserException extends RuntimeException {
	}

	/**
	 * Type of values.
	 */
	private enum ValueType {
		POINTS, INCHES, SCALAR
	};

    public class Number

	/**
	 * Internal value is always in points.
	 */
	public class Value {
		final double value;
		final ValueType type;

		Value(double value, ValueType type) {
			this.value = value;
			this.type = type;
		}

		@Override
		public String toString() {
			switch (type) {
			case INCHES:
				return value / PT_PER_IN + " in";
			case POINTS:
				return value + " pt";
			default:
				return "" + value;
			}
		}
	}

    public static class InvalidExpressionException extends Exception {
    }
    public static class MalformedParensException extends Exception {

    }

	private static final double PT_PER_IN = 72;
	private final Lexer lexer;

    /**
        Constructs a new Parser from a Lexer object.
    */
	Parser(Lexer lexer) {
        this.lexer = lexer;    
	}


    /**
        Evaluates an expression and converts it to a single number
        @param expression A list of tokens representing an expression
        @return a list of two tokens, a number and a unit, representing the result of the evaluated expression
        @throws InvalidExpressionException if expression contains
        parentheses, divides by zero, or can't be evaluated   
    */
    public List<Token> expressionToNumber(List<Token> expression) throws InvalidExpressionException{
        //If there are no operators left, you're done
        if(!hasOperatorToken(expression)) {
            return expression;
        }
        //Evaluate the first operator and recurse
        for(Token t : expression) {
            switch(t.type)
        }
    }

    public boolean hasOperatorToken(List<Token> tokens) {
        for(Token t : tokens) {
            if(t.type == Type.OPERATOR) {
                return true;
            }
        }

        return false;
    }

    /**
        Returns a list of tokens representing the expression inside the parentheses
        @param l A Lexer currently pointing at an open paren
        @param partial Recursive parameter, pass null if calling
        @return A list of tokens representing an expression
        @throws MalformedParensException if EOF is reached before the closing paren
        Note that this method will not work for nested expressions like:
            (2 + (5 * 3))
            ((2 + 8) - (6  + 3))
        But will work for single expressions in multiple parens, like:
            ((5 + 6 + 9))
            ((3 * 2))
    */
    public List<Token> extractExpressionFromParens(Lexer l, List<Token> partial) {
        Token next = l.next();
        if(next.text.equals(")")) {
            return partial;
        } else if(next.type == Type.EOF) {
            throw new MalformedParensException();
        } else if(next.type != Type.PAREN) {
            List<Token> lt = Arrays.asList(next);
            if(partial != null) {
                lt.addAll(0, partial);
            }
            return extractExpressionFromParens(l, partial)
        } else {
            //Skips open parens
            return extractExpressionFromParens(l, partial);
        }
    }

    /**
        Evaluates the equation represented in this Parser's Lexer.
        @returns a Value representing the solution
    */
	public Value evaluate() throws InvalidExpressionException {
		for(Token t = lexer.next(); t.type != Type.EOF; t = lexer.next()) {
            switch(t.type) {
                case Type.NUMBER:
                case Type.OPERATOR:
                case Type.PAREN:
                case Type.UNIT:

            }
        }
	}
}
