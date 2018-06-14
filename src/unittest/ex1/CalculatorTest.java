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

// 단위 테스트 핵심
// 1. 가독성
// 2. 신뢰성
// 3. 유지보수성


// junit4 / junit5에서는 관점 지향 프로그래밍(AOP)
// 반드시 public class로 작성되어야 한다.
public class CalculatorTest {
    @Ignore
    public void testAdd() {
//        System.out.println("CalculatorTest - testAdd()");
        throw new IllegalStateException("TODO");
    }
    // BDD (행위 주도 개발)
    // 1) 행위 기반 테스트
    // 2) 테스트를 구성할 때, 자연어와 가깝게 구성하는 것을 지향한다.
    // Given / When / Then

    // 테스트 프레임워크 - Javascript 진영이 트렌드
    //  : mocha.js / jest.js
    //  : it() : BDD의 대표적인 예시.
    //
    // 테스트를 구성하는 방법 - 3A (TDD) / BDD
    // 1) Arrange / Given : 객체를 생성하고, 필요한 경우 적절하게 설정한다.
    // 2) Act / When : 객체에 작용을 가한다.
    // 3) Assert / Then : 기대하는 바를 단언한다.


    // 테스트의 이름은 어떤 시나리오로 동작하는지 명확하게 드러냐야 한다.
    // ==> test_테스트대상메소드_시나리오_기대값()
    //
    @Test
    public void test_add_10plus32_expected42() {
        // Arrange
        Calculator calc = new Calculator();

        // Act
        calc.add(10);
        calc.add(32);

        // Assert
//        if(42 != calc.display()) {
//            throw new IllegalStateException("10 + 32 != 42");
//        }

        // 테스트를 만들 때 고려할 점
        // * Test code 안에는 조건문이나, 반복문같은 제어구문이 나오면 안된다.
        // Assert
        //  1. assertEquals을 사용할 때는 반드시 첫번째 인자로 기대값을 사용해야 한다.
        //  2. 실패의 원인이 무엇인지 '반드시" 메세지를 작성해야 한다.
        assertEquals("10 + 32", 32, calc.display());
    }

    @Test
    public void subTest() {
        Calculator calc = new Calculator();
        // fixture
        //  정의 : xUnit에서 SUT를 실행하기 위해 해주어야 하는 사전 작업
        //          (객체 생성, 초기화, 기타 준비 작업)
        //          ==> fixture를 설치한다. (setup)
        calc.sub(10);
        calc.sub(32);

        assertEquals("-10 - 32", -42, calc.display());
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
