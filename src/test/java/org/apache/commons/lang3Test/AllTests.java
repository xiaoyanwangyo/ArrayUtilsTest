package org.apache.commons.lang3Test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * JUnit 4 测试套件：聚合所有相关测试类
 */
@Suite
@SelectClasses({
        ArrayUtilsJUnitTest.class
})
public class AllTests{

}
