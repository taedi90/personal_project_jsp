package personal_project_jsp.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import personal_project_jsp.dao.UserDao;
import personal_project_jsp.dto.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoImplTest {
	
	private static UserDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("테스트 클래스 시작 전 - setUpBeforeClass()");
		dao = UserDaoImpl.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("테스트 클래스 종료 후 - tearDownAfterClass()");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test4SelectUserByAll() {
		System.out.println("testSelectUserByAll");
		ArrayList<User> arr = new ArrayList<>();
		
		arr = dao.selectUserByAll();
		
		System.out.println(arr);
		
		Assert.assertNotNull(arr);
	}

	@Test
	public void test2InsertUser() {
		System.out.println("test3InsertUser");
		User user = new User("test", "이름", "1234", "test@test.kr");
		int res = dao.insertUser(user);
		System.out.println("res>> " + res);
		Assert.assertNotEquals(0, res);
	}

	@Test
	public void test3UpdateUser() {
		System.out.println("test2UpdateUser");
		User user = new User("test", "이름", "4567", "test@test.kr");
		int res = dao.updateUser(user);
		System.out.println("res>> " + res);
		Assert.assertNotEquals(0, res);
	}

	@Test
	public void test1DeleteUser() {
		System.out.println("test1DeleteUser");
		User user = new User();
		user.setId("test");
		int res = dao.deleteUser(user);
		System.out.println("res>> " + res);
		Assert.assertNotEquals(0, res);
	}
	
	@Test
	public void test5LoginChk() {
		System.out.println("test5LoginChk");
		User user = new User();
		user.setId("test");
		user.setPassword("4567");
		int res = dao.loginChk(user);
		System.out.println("res>> " + res);
		Assert.assertNotEquals(0, res);
		
	}

}
