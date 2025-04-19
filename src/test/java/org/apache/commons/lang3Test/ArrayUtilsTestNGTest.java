package org.apache.commons.lang3Test;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArrayUtilsTestNGTest {

    @Parameters({"originalArray", "element", "expectedLength"})
    @Test
    public void testAdd(String originalArrayStr, String elementStr, int expectedLength) {
        Integer[] originalArray = parseArray(originalArrayStr);
        Integer element = parseElement(elementStr);
        Integer[] resultArray = ArrayUtils.add(originalArray, element);
        assertEquals(expectedLength, resultArray.length);
    }

    private Integer[] parseArray(String arrayStr) {
        String[] elements = arrayStr.split(",");
        Integer[] result = new Integer[elements.length];
        for (int i = 0; i < elements.length; i++) {
            result[i] = Integer.parseInt(elements[i]);
        }
        return result;
    }

    private Integer parseElement(String elementStr) {
        return elementStr.isEmpty()? null : Integer.parseInt(elementStr);
    }
}