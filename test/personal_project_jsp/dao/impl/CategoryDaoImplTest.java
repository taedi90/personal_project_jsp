package personal_project_jsp.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import personal_project_jsp.dao.CategoryDao;
import personal_project_jsp.dto.Category;

public class CategoryDaoImplTest {
	
	private static CategoryDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("테스트 클래스 시작 전 - setUpBeforeClass()");
		dao = CategoryDaoImpl.getInstance();
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
	public void testSelectCategoryByAll() {
		System.out.println("testSelectCategoryByAll");
		ArrayList<Category> arr = new ArrayList<>(); 
		arr = dao.selectCategoryByAll();
		System.out.println(arr);
		Assert.assertNotNull(arr);
	}

	@Test
	public void testSelectTitleByNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCategory() {
		fail("Not yet implemented");
	}

}
