package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

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

        assertTrue(schema.isValid(null));

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

    @Test
    void testValidatorMapSchema() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap<>())); // true

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        assertFalse(schema.isValid(data));  // false

        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true
    }

    @Test
    void testValidatorIncludeMapSchema() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2)); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4)); // false
    }
}