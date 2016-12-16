package calculator;

import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static calculator.Lexer.Token;
import static calculator.Lexer.InvalidCharException;

import calculator.Lexer;
import calculator.Type;

import org.junit.Test;

public class LexerTest {
    
    @Test
    public void lexerConstructorTest() {
        try {
            Lexer l = new Lexer("4pt + ((3in*2.4))");
            assertEquals(l.next().type, Type.NUMBER);
            assertEquals(l.next().type, Type.UNIT);
            assertEquals(l.next().type, Type.OPERATOR);
            assertEquals(l.next().type, Type.PAREN);
            assertEquals(l.next().type, Type.PAREN);
            assertEquals(l.next().type, Type.NUMBER);
            assertEquals(l.next().type, Type.UNIT);
            assertEquals(l.next().type, Type.OPERATOR);
            assertEquals(l.next().type, Type.NUMBER);
            assertEquals(l.next().type, Type.PAREN);
            assertEquals(l.next().type, Type.PAREN);
        } catch(InvalidCharException ice) {
            System.out.println("Invalid char thrown");
        }
    }
}
