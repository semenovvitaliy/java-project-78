package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidatorTest {

    // begin -> magic number error off

    private final int five = 5;
    private final int ten = 10;

    // end -> magic number error off


    @Test
    void testValidatorStringSchema() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        assertTrue(schema.isValid(""), "String Test 1 failed"); // true
        assertTrue(schema.isValid(null), "String Test 2 failed"); // true

        schema.required();

        assertTrue(schema.isValid("what does the fox say"), "String Test 3 failed"); // true
        assertTrue(schema.isValid("hexlet"), "String Test 4 failed"); // true
        assertFalse(schema.isValid(null), "String Test 5 failed"); // false
        assertFalse(schema.isValid(""), "String Test 6 failed"); // false

        assertTrue(schema.contains("wh").isValid("what does the fox say"), "String Test 7 failed"); // true
        assertTrue(schema.contains("what").isValid("what does the fox say"), "String Test 8 failed"); // true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"), "String Test 9 failed"); // false

        assertFalse(schema.isValid("what does the fox say"), "String Test 10 failed"); // false

        schema.required();

        assertTrue(schema.minLength(five).isValid("what does the fox say"), "String Test 11 failed"); // true
        assertTrue(schema.minLength(ten).isValid("what does the fox say"), "String Test 12 failed"); // true
        assertFalse(schema.isValid("what does"), "String Test 13 failed"); // false
    }

    @Test
    void testValidatorNumberSchema() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null), "Number Test 1 failed");

        schema.required();

        assertFalse(schema.isValid(null), "Number Test 2 failed"); // false
        assertTrue(schema.isValid(ten), "Number Test 3 failed"); // true
        assertFalse(schema.isValid("5"), "Number Test 4 failed"); // false

        assertTrue(schema.positive().isValid(ten), "Number Test 5 failed"); // true
        assertFalse(schema.isValid(-ten), "Number Test 6 failed"); // false

        schema.range(five, ten);

        assertTrue(schema.isValid(five), "Number Test 7 failed"); // true
        assertTrue(schema.isValid(ten), "Number Test 8 failed"); // true
        final int four = 4;
        assertFalse(schema.isValid(four), "Number Test 9 failed"); // false
        final int eleven = 11;
        assertFalse(schema.isValid(eleven), "Number Test 10 failed"); // false

        final int seven = 7;
        assertTrue(schema.range(five, ten).isValid(seven), "Number Test 11 failed"); // true
    }

    @Test
    void testValidatorMapSchema() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        assertTrue(schema.isValid(null), "Map Test 1 failed"); // true

        schema.required();

        assertFalse(schema.isValid(null), "Map Test 2 failed"); // false
        assertTrue(schema.isValid(new HashMap<>()), "Map Test 3 failed"); // true

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data), "Map Test 4 failed"); // true

        assertFalse(schema.sizeof(2).isValid(data), "Map Test 5 failed");  // false

        data.put("key2", "value2");
        assertTrue(schema.isValid(data), "Map Test 6 failed"); // true
    }

    @Test
    //@Disabled
    void testValidatorIncludeMapSchema() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        final int someAge = 100;
        human1.put("age", someAge);
        assertTrue(schema.isValid(human1), "ShapeMap Test 1 failed"); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2), "ShapeMap Test 2 failed"); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3), "ShapeMap Test 3 failed"); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        final int someNegativeNumber = -5;
        human4.put("age", someNegativeNumber);
        assertFalse(schema.isValid(human4), "ShapeMap Test 4 failed"); // false
    }
}
