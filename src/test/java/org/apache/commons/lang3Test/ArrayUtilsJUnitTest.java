package org.apache.commons.lang3Test;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayUtilsJUnitTest {

    // 测试 add 方法 - 奇数长度数组
    @Test
    public void testAddWithOddLengthArray() {
        Integer[] oddLengthArray = {1, 2, 3};
        Integer elementToAdd = 4;
        System.out.println("开始测试add方法，原数组为奇数长度: " + java.util.Arrays.toString(oddLengthArray) + "，添加元素为: " + elementToAdd);
        Integer[] resultArray = ArrayUtils.add(oddLengthArray, elementToAdd);
        assertEquals(oddLengthArray.length + 1, resultArray.length, "add方法，奇数长度数组测试失败：数组长度不符合预期");
        assertEquals(elementToAdd, resultArray[resultArray.length - 1], "add方法，奇数长度数组测试失败：添加的元素位置不正确");
        for (int i = 0; i < oddLengthArray.length; i++) {
            assertEquals(oddLengthArray[i], resultArray[i], "add方法，奇数长度数组测试失败：原数组元素复制错误");
        }
        System.out.println("add方法，奇数长度数组测试通过");
    }

    // 测试 add 方法 - 偶数长度数组
    @Test
    public void testAddWithEvenLengthArray() {
        Integer[] evenLengthArray = {1, 2, 3, 4};
        Integer elementToAdd = 5;
        System.out.println("开始测试add方法，原数组为偶数长度: " + java.util.Arrays.toString(evenLengthArray) + "，添加元素为: " + elementToAdd);
        Integer[] resultArray = ArrayUtils.add(evenLengthArray, elementToAdd);
        assertEquals(evenLengthArray.length, resultArray.length, "add方法，偶数长度数组测试失败：数组长度不符合预期");
        assertEquals(elementToAdd, resultArray[resultArray.length - 1], "add方法，偶数长度数组测试失败：添加的元素位置不正确");
        boolean foundMissing = false;
        for (int i = 0; i < evenLengthArray.length; i++) {
            boolean found = false;
            for (int j = 0; j < resultArray.length - 1; j++) {
                if (evenLengthArray[i].equals(resultArray[j])) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (foundMissing) {
                    fail("add方法，偶数长度数组测试失败：结果数组中缺失了多个原数组元素");
                }
                foundMissing = true;
            }
        }
        System.out.println("add方法，偶数长度数组测试通过");
    }


    // 测试 contains 方法 - 元素存在
    @Test
    public void testContainsElementPresent() {
        Integer[] array = {1, 2, 3, 4};
        Integer elementToFind = 3;
        System.out.println("开始测试contains方法，查找元素: " + elementToFind + " 在数组: " + java.util.Arrays.toString(array) + " 中是否存在");
        assertTrue(ArrayUtils.contains(array, elementToFind), "contains方法测试失败：未找到存在的元素");
        System.out.println("contains方法，元素存在测试通过");
    }

    // 测试 contains 方法 - 元素不存在
    @Test
    public void testContainsElementNotPresent() {
        Integer[] array = {1, 2, 3, 4};
        Integer elementNotPresent = 5;
        System.out.println("开始测试contains方法，查找元素: " + elementNotPresent + " 在数组: " + java.util.Arrays.toString(array) + " 中是否不存在");
        assertFalse(ArrayUtils.contains(array, elementNotPresent), "contains方法测试失败：错误地找到了不存在的元素");
        System.out.println("contains方法，元素不存在测试通过");
    }

    // 测试 contains 方法 - 传入 null 数组
    @Test
    public void testContainsWithNullArray() {
        Integer elementToFind = 3;
        System.out.println("开始测试contains方法，查找元素: " + elementToFind + " 在null数组中是否存在");
        assertFalse(ArrayUtils.contains(null, elementToFind), "contains方法测试失败：在null数组中错误地找到了元素");
        System.out.println("contains方法，传入null数组测试通过");
    }

    // 测试 remove 方法 - 正常移除元素
    @Test
    public void testRemoveElement() {
        Integer[] array = {1, 2, 3, 4};
        int indexToRemove = 2;
        System.out.println("开始测试remove方法，从数组: " + java.util.Arrays.toString(array) + " 中移除索引为: " + indexToRemove + " 的元素");
        Integer[] resultArray = ArrayUtils.remove(array, indexToRemove);
        assertEquals(array.length - 1, resultArray.length, "remove方法测试失败：数组长度不符合预期");
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != indexToRemove) {
                assertEquals(array[i], resultArray[j], "remove方法测试失败：元素移除后数组元素不正确");
                j++;
            }
        }
        System.out.println("remove方法，正常移除元素测试通过");
    }

    // 测试 remove 方法 - 传入 null 数组
    @Test
    public void testRemoveWithNullArray() {
        System.out.println("开始测试remove方法，传入null数组");
        Integer[] resultArray = ArrayUtils.remove((Integer[]) null, 0);
        assertNull(resultArray, "remove方法测试失败：传入null数组时，结果不为null");
        System.out.println("remove方法，传入null数组测试通过");
    }

    // 测试 remove 方法 - 传入无效索引
    @Test
    public void testRemoveWithInvalidIndex() {
        Integer[] array = {1, 2, 3, 4};
        System.out.println("开始测试remove方法，传入无效索引");
        Integer[] resultArray = ArrayUtils.remove(array, -1);
        assertArrayEquals(array, resultArray, "remove方法测试失败：传入无效索引-1时，结果数组不正确");
        resultArray = ArrayUtils.remove(array, array.length);
        assertArrayEquals(array, resultArray, "remove方法测试失败：传入无效索引数组长度时，结果数组不正确");
        System.out.println("remove方法，传入无效索引测试通过");
    }

    // 测试 insert 方法 - 正常插入元素
    @Test
    public void testInsertElement() {
        Integer[] array = {1, 2, 4, 5};
        Integer[] valuesToInsert = {3};
        int insertIndex = 2;
        System.out.println("开始测试insert方法，在数组: " + java.util.Arrays.toString(array) + " 的索引: " + insertIndex + " 处插入元素: " + java.util.Arrays.toString(valuesToInsert));
        Integer[] resultArray = ArrayUtils.insert(insertIndex, array, valuesToInsert);
        assertEquals(array.length + valuesToInsert.length, resultArray.length, "insert方法测试失败：数组长度不符合预期");
        for (int i = 0; i < insertIndex; i++) {
            assertEquals(array[i], resultArray[i], "insert方法测试失败：插入位置前的数组元素不正确");
        }
        for (int i = 0; i < valuesToInsert.length; i++) {
            assertEquals(valuesToInsert[i], resultArray[insertIndex + i], "insert方法测试失败：插入的元素不正确");
        }
        for (int i = insertIndex; i < array.length; i++) {
            assertEquals(array[i], resultArray[i + valuesToInsert.length], "insert方法测试失败：插入位置后的数组元素不正确");
        }
        System.out.println("insert方法，正常插入元素测试通过");
    }

    // 测试 insert 方法 - 插入索引为负数
    @Test
    public void testInsertWithNegativeIndex() {
        Integer[] array = {1, 2, 3};
        Integer[] valuesToInsert = {0};
        System.out.println("开始测试insert方法，在数组: " + java.util.Arrays.toString(array) + " 中以负数索引: -1 插入元素: " + java.util.Arrays.toString(valuesToInsert));
        Integer[] resultArray = ArrayUtils.insert(-1, array, valuesToInsert);
        assertEquals(array.length + valuesToInsert.length, resultArray.length, "insert方法测试失败：数组长度不符合预期");
        assertEquals(valuesToInsert[0], resultArray[0], "insert方法测试失败：插入元素位置不正确");
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], resultArray[i + 1], "insert方法测试失败：原数组元素位置不正确");
        }
        System.out.println("insert方法，插入索引为负数测试通过");
    }

    // 测试 insert 方法 - 插入索引大于数组长度
    @Test
    public void testInsertWithIndexGreaterThanLength() {
        Integer[] array = {1, 2, 3};
        Integer[] valuesToInsert = {4};
        System.out.println("开始测试insert方法，在数组: " + java.util.Arrays.toString(array) + " 中以大于数组长度的索引: 10 插入元素: " + java.util.Arrays.toString(valuesToInsert));
        Integer[] resultArray = ArrayUtils.insert(10, array, valuesToInsert);
        assertEquals(array.length + valuesToInsert.length, resultArray.length, "insert方法测试失败：数组长度不符合预期");
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], resultArray[i], "insert方法测试失败：原数组元素位置不正确");
        }
        assertEquals(valuesToInsert[0], resultArray[resultArray.length - 1], "insert方法测试失败：插入元素位置不正确");
        System.out.println("insert方法，插入索引大于数组长度测试通过");
    }

    // 测试 insert 方法 - 插入空值数组
    @Test
    public void testInsertWithEmptyValues() {
        Integer[] array = {1, 2, 3};
        Integer[] valuesToInsert = {};
        System.out.println("开始测试insert方法，在数组: " + java.util.Arrays.toString(array) + " 中插入空值数组");
        Integer[] resultArray = ArrayUtils.insert(1, array, valuesToInsert);
        assertArrayEquals(array, resultArray, "insert方法测试失败：插入空值数组时，结果数组不正确");
        System.out.println("insert方法，插入空值数组测试通过");
    }
}