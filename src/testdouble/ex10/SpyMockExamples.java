package testdouble.ex10;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


//  1. 행위 기반 테스트를 목적으로 사용합니다.
//  2. xUnit Test Pattern의 Spy와는 조금 차이가 있습니다.
//  3. Spy와 Mock의 차이점을 학인하기 위해서는 interface에서 확인되지 않습니다.
//  4. Spy는 실제 동작을 수행합니다.
//     Mock은 실제 동작을 수행하지 않고, 메소드 호출 여부만 검증합니다.
//  5.

//  Java 8
//  ==> 6, 7 언어적인 변화가 거의 존재하지 않는다.
//         ; 인터페이스의 한계 (수정되면 하위 클래스가 다 깨진다.)
//

public class SpyMockExamples {

    @Test
    public void playMusicTestWithSpy() {
        Person person = new Person();
        Mp3 mockedMp3 = spy(Mp3.class);

        person.playMusic(mockedMp3);

        verify(mockedMp3).playOneMinute();
//        verify(mockedMp3).play();
//        verify(mockedMp3).stop();
    }

    @Test
    public void playMusicTestWithMock() {
        Person person = new Person();
        Mp3 mockedMp3 = mock(Mp3.class);

        person.playMusic(mockedMp3);

        verify(mockedMp3).playOneMinute();

//        verify(mockedMp3).play();
//        verify(mockedMp3).stop();
    }


}


//abstract class Mp3 {
//    void play() { System.out.println("Play mp3");}
//    void stop() { System.out.println("Stop mp3");}
//}

//  Java8에서는 인터페이스 기본 구현을 제공할 수 있습니다.
interface Mp3 {
    void play();
    void stop();

    //  default method
    default void playOneMinute() {
        System.out.println("Play one minutes");
        play();
        //  ...
        stop();
    }
}


class Person {
    public void playMusic(Mp3 mp3) {
        mp3.playOneMinute();
//        mp3.play();
//        mp3.stop();
    }
}