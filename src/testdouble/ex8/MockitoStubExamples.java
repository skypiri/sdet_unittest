package testdouble.ex8;

//  Stub : 특수한 상황을 시뮬레이션한다.

import org.junit.Test;

import static org.mockito.Mockito.*;

interface Mouse {
    String showOff();
}

public class MockitoStubExamples {
    @Test
    public void stubTest() {
        Mouse stub = mock(Mouse.class);
        // stub이 호출되었을 때 어떤 결과를 반환할지 결정한다.
        when(stub.showOff()).thenReturn("I'm mouse");

        System.out.println(stub.showOff());


    }
}
