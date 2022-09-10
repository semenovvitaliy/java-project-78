package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AppTest {

    // begin -> magic number error off

    private final int five = 5;
    private final int ten = 10;

    // end -> magic number error off


    @Test
    void testValidatorStringSchema() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true
        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false

        assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false

        assertFalse(schema.isValid("what does the fox say")); // false

        schema.required();

        assertTrue(schema.minLength(five).isValid("what does the fox say")); // true
        assertTrue(schema.minLength(ten).isValid("what does the fox say")); // true
        assertFalse(schema.isValid("what does")); // false
    }

    @Test
    void testValidatorNumberSchema() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(ten)); // true
        assertFalse(schema.isValid("5")); // false

        assertTrue(schema.positive().isValid(ten)); // true
        assertFalse(schema.isValid(-ten)); // false

        schema.range(five, ten);

        assertTrue(schema.isValid(five)); // true
        assertTrue(schema.isValid(ten)); // true
        final int four = 4;
        assertFalse(schema.isValid(four)); // false
        final int eleven = 11;
        assertFalse(schema.isValid(eleven)); // false

        final int seven = 7;
        assertTrue(schema.range(five, ten).isValid(seven)); // true
    }

}
