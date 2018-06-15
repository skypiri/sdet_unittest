package unittest.ex6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

//  파라미터화 테스트 (Parameterized Test Pattern)
//  : xUnit Test Framework이 제공하는 기능입니다.
//  ==> 데이터를 아주 조금씩 바꿔가며, 수차례 반복 검사하는 데이터 중심 테스트가 있을 경우
//  코드의 중복을 없애준다.

//  Cons
//  1. 복잡하다.
//      : xUnit Test Framework마다 정의하는 방식에 차이가 있다.
//  2. 테스트가 어떤 데이터를 통해 수행되는지 알기 어렵다.
//      : junit4에서는 해당 문제를 보완할 수 있습니다.
//      ==> GoogleTest에서는 제공되지 않습니다.
//  3. JUnit5에서는 완전히 변경되었습니다.
//      1) 파라미터화 테스트는 별도의 테스트 케이스 클래스로 분리되어야 합니다.
//          => 이제는 같은 테스트 케이스 클래스 안에서 파라미터화 테스트를 구성하는 것이 가능합니다.
//      2) 정적인 데이터를 다루어야 한다.
//          ==> 많은 개수의 데이터가 있을 경우, 로드할 수 없을 수 있다.
//          ==> 동적으로 처리할 수 있도록 변경되었습니다.
//      ** Test Factory 라는 이름으로 소개되었습니다.

//  파라미터화 테스트를 적용하는 방법
//  1. 기존의 Test Runner와 동작 방식이 다릅니다.
//      * 기존 방식
//      TestCase tc = new TestCase();
//      tc.setUp();
//      tc.testBody();
//      tc.tearDown();
//
//      * Parameterized Test Runner
//      Object[][] data = [{"tom"}, {"bob"}, {"alice"}]
//      실제 데이터의 형태는 2차원 배열 형태로
//      TestCase tc = new TestCase({"tom"})
//      tc.setUp();
//      tc.testBody();
//      tc.tearDown();

//      TestCase tc = new TestCase({"bob"})
//      tc.setUp();
//      tc.testBody();
//      tc.tearDown();

//      TestCase tc = new TestCase({"alice"})
//      tc.setUp();
//      tc.testBody();
//      tc.tearDown();


//  1. Runner를 Parameterzied Runner로 변경한다.
@RunWith(value = Parameterized.class)
public class UserNameTest {
//  2. Data Set 정의 - static
//    public static Object[][] data() {
//        return new Object[][] {
//                {"tom"},
//                {"bob"},
//                {"alice"}
//        };
//    }

    @Parameterized.Parameters(name = "{index} - name({0}), age({1})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"tom", 10},
                {"bob", 20},
                {"alice", 30},
                {"clock", 40}
        });
    }

    private String name;
//  3. data set을 전달받기 위한 생성자 / 필드 정의
    public UserNameTest(String name, int age) {
        this.name = name;
    }

//  4. 이제 다양한 테스트 메소드를 추가하면 됩니다.
    @Test
    public void isValidNameTest() {
        assertTrue(User.isValidName(name));
    }
}


//public class UserNameTest {
//
//    //  단언문
//    //  : 하나의 단언문이 실패하면, 이후의 단언문은 절대 실행되지 않는다.
//    @Test
//    public void isValidNameTest() {
//        assertTrue(User.isValidName("tom"));
//        assertTrue(User.isValidName("bob"));
//        assertTrue(User.isValidName("alice"));
//        assertTrue(User.isValidName("marry"));
//    }
//}


//  SUT
class User {
    public static boolean isValidName(String name) {
        //  사용자의 이름은 반드시 소문자로 이루어져 있어야 하고, 5글자 이상이어야 한다.
        char[] chars = name.toCharArray();
        for(char c : chars) {
            if(Character.isUpperCase(c)) {
                return false;
            }
        }

        return name.length() >= 5;
    }
}