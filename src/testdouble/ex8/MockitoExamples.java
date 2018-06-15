package testdouble.ex8;

//  1. 메소드 호출 여부
//  2. 메소드 호출 횟수
//  3. 메소드 호출 순서
//  4. 메소드 인자(그룹)

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class SUT {
    public void foo(List<String> s)  {
        s.add("one");
        s.add("two");
    }

    public void goo(List<String> s) {
        s.add("once");
        s.add("twice");
        s.add("twice");
    }

    public void hoo(List<String> s) {
        s.add("first");
        s.add("second");
    }
}

//public class MockitoExamples {
//
//    //  호출 순서
//    @Test
//    public void hooTest() {
//        SUT sut = new SUT();
//        List<String> mockedList = createMock();
//        sut.hoo(mockedList);
//
//        InOrder inOrder = inOrder(mockedList);
//        inOrder.verify(mockedList).add("first");
//        inOrder.verify(mockedList).add("second");
//
//    }
//
//    //  인자에 대한 체크
//    @Test
//    public void gooTest2() {
//        SUT sut = new SUT();
//        List<String> mockedList = createMock();
//
//        sut.goo(mockedList);
//
//        verify(mockedList, atLeast(3)).add(anyString());
//    }
//
//    //  2. 메소드 호출 횟수
//    // ==> times, 적어도 N번 - atLeast(), 많아도 N번 - atMost()
//    @Test
//    public void gooTest() {
//        SUT sut = new SUT();
//        List<String> mockedList = createMock();
//        sut.goo(mockedList);
//
//        verify(mockedList, times(1)).add("once");
////        verify(mockedList, times(2)).add("twice");
//        verify(mockedList, atLeast(2)).add("twice");
//        verify(mockedList, atMost(3)).add("twice");
//    }
//
//
//    //  Java Generic : 타입 소거 방식으로 동작합니다.
//    //  List<String> s1; --> List s1;
//    //  List<Integer> s2; --> List s2;
//    //  C++ template
//
//    //  1. 메소드 호출 여부 판단
//    @Test
//    public void fooTest() {
//        SUT sut = new SUT();
//        List<String> mockedList = createMock();
//
//        sut.foo(mockedList);
//        verify(mockedList).add("one");
//        verify(mockedList).add("two");
//    }
//
//
//    //  Delegation Setup
//    @SuppressWarnings("unchecked")
//    private List<String> createMock() {
//        return mock(List.class);
//    }
//}

public class MockitoExamples {

    //  호출 순서
    @Test
    public void hooTest() {
        sut.hoo(mockedList);

        InOrder inOrder = inOrder(mockedList);
        inOrder.verify(mockedList).add("first");
        inOrder.verify(mockedList).add("second");

    }

    //  인자에 대한 체크
    @Test
    public void gooTest2() {
        sut.goo(mockedList);

        verify(mockedList, atLeast(3)).add(anyString());
    }

    //  2. 메소드 호출 횟수
    // ==> times, 적어도 N번 - atLeast(), 많아도 N번 - atMost()
    @Test
    public void gooTest() {
        sut.goo(mockedList);

        verify(mockedList, times(1)).add("once");
//        verify(mockedList, times(2)).add("twice");
        verify(mockedList, atLeast(2)).add("twice");
        verify(mockedList, atMost(3)).add("twice");
    }


    //  Java Generic : 타입 소거 방식으로 동작합니다.
    //  List<String> s1; --> List s1;
    //  List<Integer> s2; --> List s2;
    //  C++ template

    //  1. 메소드 호출 여부 판단
    @Test
    public void fooTest() {
        sut.foo(mockedList);
        verify(mockedList).add("one");
        verify(mockedList).add("two");
    }


    private SUT sut;
    @Mock
    private List<String> mockedList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new SUT();
    }

    public void tearDown() {

    }

}