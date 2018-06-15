package testdouble.ex9;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoLoggerTest {

    @Test
    public void isValid_MockitoNameLoggerThan5Chars_ReturnsTrue() {
        IFileSystemManager stub = mock(IFileSystemManager.class);
        when(stub.isValid("goodname.log")).thenReturn(true);

        Logger logger = new Logger(stub);
        boolean actual = logger.isValidFilename("goodname.log");


        assertTrue("파일명이 다섯글자 이상일 때", actual);
    }

    @Test
    public void isValid_MockitoNameShorterThan5Chars_ReturnsFalse() {
        IFileSystemManager stub = mock(IFileSystemManager.class);
        when(stub.isValid("bad.log")).thenReturn(false);

        Logger logger = new Logger(stub);
        boolean actual = logger.isValidFilename("bad.log");


        assertFalse("파일명이 다섯글자 미만일 때", actual);
    }
}


interface IFileSystemManager {
    boolean isValid(String filename);
}

// 1. 인터페이스를 도입한다.
class FileSystemManager implements IFileSystemManager {
    @Override
    public boolean isValid(String filename) {
        return false;
    }
}

class Logger {
    private IFileSystemManager manager;

    public Logger(IFileSystemManager manager) {
        this.manager = manager;
    }

    // 기존의 기능과 동일한 동작을 수행할 수 있도록 해줍니다.
    public Logger() {
        this.manager = new FileSystemManager();
    }

    public boolean isValidFilename(String filename) {
        // 1. 확장자를 제외한 파일의 이름이 반드시 다섯글자 이상이어야 한다.
        //---------------------------- SUT start
        String name = filename.split("\\.")[0];
        if (name.length() < 5)
            return false;
        //---------------------------- SUT end

        // 2. 파일 시스템에서 체크한다.
        // IFileSystemManager manager = new FileSystemManager();
        return manager.isValid(filename);
    }
}










