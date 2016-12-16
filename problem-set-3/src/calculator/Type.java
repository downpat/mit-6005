package calculator;

/*
 * terminals: in, pt, +, -, *, /, ., 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, (, )
 * types:
 *  unit: in, pt
 *  operator: +, -, *, /
 *  digit: 1, 2, 3, 4, 5, 6, 7, 8, 9, 0
 *  paren: (, )
 *  decimal: .
 */

/**
 * Token type.
 */
public enum Type {
    EOF,
    NUMBER,
    OPERATOR,
    PAREN,
    UNIT;
}
