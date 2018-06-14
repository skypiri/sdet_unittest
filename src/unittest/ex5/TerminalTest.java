package unittest.ex5;

import org.junit.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


//  만약 fixture를 설치/해제하는 작업의 시간이 오래 걸린다.
//  ==> Slow Test 문제
//  :   테스트가 너무 느려서, 개발자들이 SUT가 변경되어도 테스트를 수행하지 않는다.
//      테스트를 수행하는 개발자의 생산성을 떨어뜨린다.
//      ==> 반드시 개선해야 될 부분이다.

//  해결 방법
//  ==> Suite Fixture SetUp / TearDown을 사용한다.
//
//  Test Runner
//  Suite 단위로 한번만 하면 좋겠다.
//  TestCase.setupTestCase();       - connect()
//  TestCase testcase = new TestCase();
//  testcase.setUp();
//  testcase.testA();
//  testcase.tearDown();

//  TestCase testcase = new TestCase();
//  testcase.setUp();
//  testcase.testB();
//  testcase.tearDown();
//  TestCAse.tearDownTestCase();       - disconnect()

/*
public class TerminalTest {

    private Terminal terminal;

    public TerminalTest() {
        System.out.println("TerminalTest");
    }

    //  Suite Fixture Setup - static method
    @BeforeClass
    public static void setupTestCase() throws Exception {
        System.out.println("setupTestCase()");
    }

    @AfterClass
    public static void teardownTestCase() throws Exception {
        System.out.println("teardownTestCase()");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");

        terminal = new Terminal();
        terminal.connect();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");

        terminal.disconnect();
    }

    @Test
    public void loginTest() throws Exception {
        System.out.println("loginTest");


        terminal.login("guest", "guest_password");

        assertTrue(terminal.isLogin());

    }

    @Test
    public void logoutTest() throws Exception {
        System.out.println("logoutTest");


        terminal.login("guest", "guest_password");
        terminal.logout();

        assertFalse(terminal.isLogin());

    }
}
*/


//  Suite Fixture Setup / TearDown  ==> 문제점이 있습니다.
//  ==> 더 이상 각각의 테스트 메소드는 독립적이지 않습니다.
//  ==> 공유 픽스쳐 전략
//  ==> 공유되는 픽스쳐으 상태에 따라서 성공해야 하는 테스트가 실패하거나
//      실패해야 하는 테스트가 성공하는 문제가 발생할 수 있습니다.
//      : 테스트의 신뢰성을 잃게된다. ==> 변덕스러운 테스트가 발생할 수 있다. (되다가 안되다가..)

//  ==> 하나의 Tip !!!
//      : Test Suite안에 20개의 테스트 메소드가 있다.(공유 픽스쳐)
//          -> Test Suite A - 5개
//              ..        D - 5개
//          분리시켜서 문제를 해결한다.
public class TerminalTest {

    private static Terminal terminal;

    public TerminalTest() {
        System.out.println("TerminalTest");
    }

    //  Suite Fixture Setup - static method
    @BeforeClass
    public static void setupTestCase() throws Exception {
        System.out.println("setupTestCase()");
        terminal = new Terminal();
        terminal.connect();
    }

    @AfterClass
    public static void teardownTestCase() throws Exception {
        System.out.println("teardownTestCase()");
        terminal.disconnect();
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");


    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");


    }

    @Test
    public void loginTest() throws Exception {
        System.out.println("loginTest");


        terminal.login("guest", "guest_password");

        assertTrue(terminal.isLogin());

    }

    @Test
    public void logoutTest() throws Exception {
        System.out.println("logoutTest");


        terminal.login("guest", "guest_password");
        terminal.logout();

        assertFalse(terminal.isLogin());
    }


    @Test
    public void name() {
    }

    @Test
    public void name1() {
    }

    @Test
    public void name2() {
    }
}

//  SUT
//  가정 : Terminal에 접속 / 접속 해제가 느리다.
class Terminal {
    public void connect() throws Exception {
        TimeUnit.SECONDS.sleep(2);
    }

    public void disconnect() throws Exception {
        TimeUnit.SECONDS.sleep(1);

    }

    public void login(String id, String password) {

    }

    public void logout() {

    }

    public boolean isLogin() {
        return true;
    }
}
