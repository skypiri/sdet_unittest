package unittest.ex1;

//* github 구성

// xUnit Test Framework 안에 숨겨져 있습니다. ==> 별도 main을 작성할 필요가 없습니다.
// TestCase : Test Method의 집합
// TestSuite : 동일한 Fixture를 가지는 테스트의 집합

// 1. Test case를 만들어야 합니다.
// 2. Test Case안에서 Test method를 만들어야 합니다.

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

// junit3 / gtest는 아래의 형식처럼 상속받는 형태로 작성한다.
// 또한 test method에서는 test 라는 prefix를 사용해야 한다.
// class CalculatorTest extends TestCase {}
//class CalculatorTest extends TestCase{
//    public void testAdd)()
//}
// junit4 / junit5에서는 관점 지향 프로그래밍(AOP)
// 반드시 public class로 작성되어야 한다.
public class CalculatorTest {
    @Ignore
    public void testAdd() {
//        System.out.println("CalculatorTest - testAdd()");
        throw new IllegalStateException("TODO");
    }

    // 테스트를 구성하는 방법 - 3A
    // 1) Arrange : 객체를 생성하고, 필요한 경우 적절하게 설정한다.
    // 2) Act : 객체에 작용을 가한다.
    // 3) Assert : 기대하는 바를 단언한다.

    // 테스트를 만들 때 고려할 점
    // * Test code 안에는 조건문이나, 반복문같은 제어구문이 나오면 안된다.

    @Test
    public void addTest() {
        // Arrange
        Calculator calc = new Calculator();

        // Act
        calc.add(10);
        calc.add(32);

        // Assert
//        if(42 != calc.display()) {
//            throw new IllegalStateException("10 + 32 != 42");
//        }
        assertEquals(42, calc.display());
    }
}
//public class Program {
//    public static void main (String[] args) {
//        System.out.println("Hello Unittest");
//    }
//}


// SUT
class Calculator {

    private int value;

    public Calculator() {
        this.value = 0;
    }

    public void add(int a) {
        value += a;
    }

    public void sub(int a) {
        value -= a;
    }

    public int display() {
        return value;
    }
}
