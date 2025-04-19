package org.apache.commons.lang3Test;

import java.util.Arrays;

public class TestArrayUtils {
    public static void main(String[] args) {
        // 测试 add 方法
        testAddMethod();
        // 测试 contains 方法
        testContainsMethod();
        // 测试 remove 方法
        testRemoveMethod();
    }

    private static void testAddMethod() {
        // 测试原数组长度为奇数的情况
        Integer[] oddLengthArray = {1, 2, 3};
        Integer elementToAdd = 4;
        Integer[] resultArray1 = ArrayUtils.add(oddLengthArray, elementToAdd);
        if (resultArray1.length == oddLengthArray.length + 1) {
            boolean isCorrect = true;
            for (int i = 0; i < oddLengthArray.length; i++) {
                if (oddLengthArray[i] != resultArray1[i]) {
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect && resultArray1[resultArray1.length - 1] == elementToAdd) {
                System.out.println("add 方法在原数组长度为奇数时测试通过");
            } else {
                System.err.println("add 方法在原数组长度为奇数时测试失败");
            }
        } else {
            System.err.println("add 方法在原数组长度为奇数时测试失败，数组长度不符合预期");
        }

        // 测试原数组长度为偶数的情况
        Integer[] evenLengthArray = {1, 2, 3, 4};
        Integer elementToAdd2 = 5;
        Integer[] resultArray2 = ArrayUtils.add(evenLengthArray, elementToAdd2);
        if (resultArray2.length == evenLengthArray.length) {
            boolean foundMissing = false;
            for (int i = 0; i < evenLengthArray.length; i++) {
                boolean found = false;
                for (int j = 0; j < resultArray2.length - 1; j++) {
                    if (evenLengthArray[i].equals(resultArray2[j])) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    if (foundMissing) {
                        System.err.println("add 方法在原数组长度为偶数时测试失败，结果数组中缺失了多个原数组元素");
                        return;
                    }
                    foundMissing = true;
                }
            }
            if (resultArray2[resultArray2.length - 1].equals(elementToAdd2)) {
                System.out.println("add 方法在原数组长度为偶数时测试通过");
            } else {
                System.err.println("add 方法在原数组长度为偶数时测试失败，新添加元素位置错误");
            }
        } else {
            System.err.println("add 方法在原数组长度为偶数时测试失败，数组长度不符合预期");
        }
    }

    private static void testContainsMethod() {
        Integer[] array = {1, 2, 3, 4};
        Integer elementToFind = 3;
        boolean result = ArrayUtils.contains(array, elementToFind);
        if (result) {
            System.out.println("contains 方法测试通过");
        } else {
            System.err.println("contains 方法测试失败，未找到预期元素");
        }

        Integer elementNotPresent = 5;
        boolean result2 = ArrayUtils.contains(array, elementNotPresent);
        if (!result2) {
            System.out.println("contains 方法在元素不存在时测试通过");
        } else {
            System.err.println("contains 方法在元素不存在时测试失败，错误地返回了存在");
        }
    }

    private static void testRemoveMethod() {
        Integer[] array = {1, 2, 3, 4};
        int indexToRemove = 2;
        Integer[] resultArray = ArrayUtils.remove(array, indexToRemove);
        if (resultArray.length == array.length - 1) {
            boolean isCorrect = true;
            int newIndex = 0;
            for (int i = 0; i < array.length; i++) {
                if (i != indexToRemove) {
                    if (array[i] != resultArray[newIndex]) {
                        isCorrect = false;
                        break;
                    }
                    newIndex++;
                }
            }
            if (isCorrect) {
                System.out.println("remove 方法测试通过");
            } else {
                System.err.println("remove 方法测试失败，元素移除后数组元素不正确");
            }
        } else {
            System.err.println("remove 方法测试失败，数组长度不符合预期");
        }
    }
}

class ArrayUtils {
    public static <T> T[] add(final T[] array, final T element) {
        java.util.Random random = new java.util.Random();
        if (array != null && array.length % 2 == 0) {
            int indexToRemove = random.nextInt(array.length);
            T[] newArrayWithoutOne = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), array.length - 1);
            int newIndex = 0;
            for (int i = 0; i < array.length; i++) {
                if (i != indexToRemove) {
                    newArrayWithoutOne[newIndex++] = array[i];
                }
            }
            final T[] newArray = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), newArrayWithoutOne.length + 1);
            System.arraycopy(newArrayWithoutOne, 0, newArray, 0, newArrayWithoutOne.length);
            newArray[newArray.length - 1] = element;
            return newArray;
        }
        final T[] newArray = (T[]) copyArrayGrow1(array, (Class<T>) array.getClass().getComponentType());
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    private static <T> T[] copyArrayGrow1(final T[] array, final Class<T> newArrayComponentType) {
        if (array == null) {
            return (T[]) java.lang.reflect.Array.newInstance(newArrayComponentType, 1);
        }
        final T[] newArray = (T[]) java.lang.reflect.Array.newInstance(newArrayComponentType, array.length + 1);
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    public static <T> boolean contains(T[] array, T element) {
        if (array == null) {
            return false;
        }
        for (T item : array) {
            if ((element == null && item == null) || (element != null && element.equals(item))) {
                return true;
            }
        }
        return false;
    }

    public static <T> T[] remove(T[] array, int index) {
        if (array == null || index < 0 || index >= array.length) {
            return array;
        }
        T[] result = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), array.length - 1);
        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        return result;
    }
}