package testdouble.ex5;


//  1. UserManager 객체는 Database 객체에 의존한다.
//      case 1) Database 객체가 아직 준비되지 않았다.
//      case 2) Database가 너무 느려서, Slow Test 문제가 발생한다.
//      case 3) DMZ 같은 영역에 데이터베이스가 존재해서, 사용하기 어렵다.

//  Test Double
//  용도 2. 아직 만들어지지 않은 컴포넌트에 의존하는 객체를 테스트하고 싶다.
//      1) 의존 객체를 사용할 수 없을 때 해당 로직을 독리적으로 검증하고 싶다.
//      2) 의존 객체가 너무 느려서 테스트가 느려진다.
//      3) 여러가지 문제로 의존 객체를 사용하기 어렵다.
//      ==> Fake Object Pattern

//  Stub : 하나의 동작을 수행한다.
//  Fake : 진짜처럼 실제 동작을 수행해야 한다.

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

class FakeDatabase implements Database {
    private Map<String, User> data = new HashMap<>();

    @Override
    public void save(String name, User user) {
        data.put(name, user);
    }

    @Override
    public User load(String name) {
        return data.get(name);
    }
}

public class UserManagerTest {
    @Test
    public void createTest() throws Exception {
        String name = "Tom";
        int age = 42;

        FakeDatabase fake = new FakeDatabase();
        UserManager manager = new UserManager(fake);
        User expected = new User(name, age);

        manager.create(name, age);

        // 사용자 정의 객체를 assertEquals를 통해 비교하는 경우,
        //  반드시 equals를 재정의해야 합니다.
        //  C++ : 연산자 재정의를 통해 비교 가능하도록 만들어줘야 한다.
        assertEquals(expected, manager.findOne(name));

    }
}

//  SUT

interface Database {
    void save(String name, User user);
    User load(String name);
}

class UserManager {
    private Database database;
    public UserManager(Database database) {
        this.database = database;
    }

    public void create(String name, int age) {
        if(database.load(name) != null) {
            return;
        }

        database.save(name, new User(name, age));
    }

    public User findOne(String name) {
        if(name == null || name.length() < 3) {
            return null;
        }

        return database.load(name);
    }

}

//  객체 동등성
//  1) equals
//  2) hashCode
//  ==> equals를 재정의하면 Hashcode를 재정의해야한다.
//  3) toString

//  Kotlin에서는 위와 같은 기능을 자동으로 만들어줍니다.
//  data class User(val name:String, val age : Int)

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        //  1. obj != null
        if(obj == null ) {
            return false;
        }

        //  2. 참조 등등성 체크
        if(this == obj) {
            return true;
        }

        //  3. 타입 체크
        if(obj.getClass() != User.class) {
            return false;
        }

        User u = (User)obj;
        //  4. 내용 비교
        return Objects.equals(name, u.name) && age == u.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return String.format("User(name=%s, age=%d)", name, age);
    }
}
