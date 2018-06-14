package unittest.ex3;


//  xUnit Test Framework이 가지고 있는 공통의 기능을 정리해봅시다.
//  ==> Reference로 junit4를 사용합니다.

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class xUnitExamples {
    // Test Coverage: 테스트 코드를 통해 수행된 제품 코드의 비율

    public Integer parseInt(String str) {
        // str이 잘못된 형식인 경우 NumberFormatException을 던집니다.
        // return 16;
        return Integer.parseInt(str);
        // throw new IllegalStateException("..");
    }


    // 1. 예외 테스트(Exception Test)
    // parseInt의 함수에서 잘못된 입력이 전달되었을 경우 NumberFormatException이
    // 발생하는가?
    @Test
    public void parseIntTest_bad() {
        String badStr = "x16";

        try {
            parseInt(badStr);
            fail("예외가 발생하지 않음.");
        } catch (NumberFormatException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("다른 종류의 예외가 발생하였음.");
        }
    }

    // GoogleTest: ASSERT_THROW(...)
    @Test(expected = NumberFormatException.class)
    public void parseIntTest() {
        String badStr = "x16";

        parseInt(badStr);
    }

    //  2. 비기능 테스트 - 시간
    public void slowFeature() throws InterruptedException {
        // 2 sec
        TimeUnit.MILLISECONDS.sleep(1000);

    }

    // 기능적으로 동작해도, 2초이상 걸리면 실패해야 한다.
    //  junit4는 ms의 해상도만 지원하고 있습니다.
    //  junit5는
    //      - ms, us, ns와 같은 고해상도의 타이머를 제공하고 있습니다.
    //      - assert 함수를 통해 측정이 가능합니다.
    //  GoogleTest에서는 시간 체크와 같은 기능은 없다. 필요하면 만들어 사용해야 합니다.
    @Test(timeout = 2000)
    public void slowFeatureTest() throws InterruptedException {
        slowFeature();
    }


    //  3. 테스트 비활성화
    // 아래처럼 작성 중이거나, 유지 보수 대상 테스트를 주석 처리하면 안됩니다.
    //  ==> 잊혀집니다.
    //  ==> 비활성화하는 기능을 제공하고 있습니다.
    //  junit5 : Ignore --> Disabled (GTest에서도 사용)
    @Ignore(value = "지금 작성중이며, 유지보수가 필요합니다.")
    @Test
    public void foo() {
        fail("TODO : 작성중입니다.");
    }

    //  4. 단언문
    //  : assertEquals : 배열, 실수의 경우는 주의해야 합니다.
    //      assertNotNull
    //      assertTrue
    //      assertFalse
    //  ...

    @Test
    public void equalsTest() throws Exception {
        String[] names = {"Alice", "Tom"};
        String[] actual = {"Alice", "Tom"};

        // 배열 전용 단언문
//        assertEquals(names, actual);      // 좋은 방법은 아닙니다. (deprecated)
        assertArrayEquals(names, actual);

        //  double, float ==> Floating Point Type (부동 소수점)
        //  ==> 연산에 오차가 발생한다.
        double expected = 0.7;
        double result = 0.1 * 7;

        // 두 값의 차이가 특정 오차 범위내에 들어오는 것으로 동일한 것으로 간주할 수 있다.
        assertEquals(expected, result, 0.000001);
        // 아래와 같이 비교해보면 항상 diff로 나온다.
//        if(expected == result) {
//            System.out.println("same");
//        } else {
//            System.out.println("Diff");
//        }
//        assertEquals(expected, result);
    }
}

