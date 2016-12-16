package calculator;

import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static calculator.Lexer.Token;
import static calculator.Parser.InvalidExpressionException;

import calculator.Lexer;
import calculator.Type;
import calculator.Parser;
import calculator.Parser.Value;

import org.junit.Test;

public class ParserTest {
    
    public Parser p = new Parser(new Lexer("4pt + ((3in*2.4))"));
    
    @Test
    public void expressionToNumberTest() {
        try {
            List<Token> exp = Arrays.ToList(
                new Token(Type.NUMBER, "3"),
                new Token(Type.UNIT, "in"),
                new Token(Type.OPERATOR, "*"),
                new Token(Type.NUMBER, "2.4")
            );
            List<Token> result = p.expressionToNumber(exp);
            assertEquals(Type.NUMBER, result.get(0).type);
            assertEquals("6.8", result.get(0).text);
            assertEquals(Type.UNIT, result.get(1).type);
            assertEquals("in", result.get(1).text);
        } catch(InvalidExpressionException iee) {
            System.out.println("Invalid Expression Exception");
        }
    }

    @Test
    public void evaluateTest() {
        try {
            Value v = p.evaluate();
            assertEquals("493.6 pt", v.toString());
            
        } catch(InvalidExpressionException iee) {
            System.out.println("Invalid Expression Exception");
        }
    }

    @Test
    public void extractExpressionFromParens() {
        Lexer l = new Lexer("((3in*2.4))");
        List<Token> tks = extractExpressionFromParens(l, null);
        assertEquals(Type.NUMBER, tks.get(0).type);
        assertEquals(Type.UNIT, tks.get(1).type);
        assertEquals(Type.OPERATOR, tks.get(2).type);
        assertEquals(Type.NUMBER, tks.get(3).type);
    }
}
