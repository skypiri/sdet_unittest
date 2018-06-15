package testdouble.ex7;

import org.junit.Test;

import java.util.logging.Level;

import static org.mockito.Mockito.*;

//  Test Double
//  용도 4. 행위 기반 테스트
//      ==> 'Mock' Object Pattern

// Mockito Framework
//  : Mock을 '동적'으로 생성하는 기능을 제공하고 있습니다.
//  => 별도의 코드 없이 테스트 대역을 만들 수 있습니다.

//  Mockito를 이용해서 stub, spy, mock도 만들 수 있습니다.
//  ==> Test double을 직접 코드를 작성해서 만드는 경우보다 훨씬 더 관리해야
//      할 코드가 적어집니다.
//  ==> Mock Framework이라고 하지만, 격리 프레임워크라도고 부릅니다.


public class DLogTest {
    @Test
    public void writeTest() {
        // Arrange
        Target mock1 = mock(Target.class);
        Target mock2 = mock(Target.class);
        DLog dlog = new DLog(mock1, mock2);

        // Act
        dlog.write(Level.INFO, "test_message");

        //  Verify
        //  dlog 객체에 대해서 write를 수행하였을 때, mock1, mock2에 대해서 write 메소드가 호출되었는지 여부를 검증한다.
        verify(mock1).write(Level.INFO, "test_message");
        verify(mock2).write(Level.INFO, "test_message");


    }
}



//  SUT

interface Target {
    void write(Level level, String message);
}

//  FileTarget  --> file.log
//  HttpTarget  --> POST message
class DLog {
    private Target[] targets;
    public DLog(Target... targets) {
        this.targets = targets;
    }
    public void write(Level level, String message) {
        // 자신에게 전달된 로그 메시지를 등록된 타켓에게 전파하라.
        for(Target e : targets) {
            e.write(level, message);
        }
    }
}