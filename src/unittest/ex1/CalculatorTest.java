package unittest.ex1;

//* github 구성

// xUnit Test Framework 안에 숨겨져 있습니다. ==> 별도 main을 작성할 필요가 없습니다.
// TestCase : Test Method의 집합
// TestSuite : 동일한 Fixture를 가지는 테스트의 집합

// 1. Test case를 만들어야 합니다.
// 2. Test Case안에서 Test method를 만들어야 합니다.

import org.junit.Test;

// junit3 / gtest는 아래의 형식처럼 상속받는 형태로 작성한다.
// 또한 test method에서는 test 라는 prefix를 사용해야 한다.
// class CalculatorTest extends TestCase {}
//class CalculatorTest extends TestCase{
//    public void testAdd)()
//}
// junit4 / junit5에서는 관점 지향 프로그래밍(AOP)
// 반드시 public class로 작성되어야 한다.
public class CalculatorTest {
    @Test
    public void testAdd() {
//        System.out.println("CalculatorTest - testAdd()");
        throw new IllegalStateException("TODO");
    }
}
//public class Program {
//    public static void main (String[] args) {
//        System.out.println("Hello Unittest");
//    }
//}


// SUT
class Calculator {

}
