package personal_project_jsp.dao.impl;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import personal_project_jsp.dao.BoardDao;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Category;

public class BoardDaoImplTest {
	
	private static BoardDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("테스트 클래스 시작 전 - setUpBeforeClass()");
		dao = BoardDaoImpl.getInstance();
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
	public void testSelectBoardByAll() {
		System.out.println("testSelectBoardByAll");
		Map<String, Object> map = dao.selectBoardByAll(1, 25, "desc");
		System.out.println("map >>" + map.get("list"));
		
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBoardByNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBoardByCategory() {
		System.out.println("testSelectBoardByCategory");
		Category category = new Category("수다");
		System.out.println(category);
		Map<String, Object> map = dao.selectBoardByCategory(category, 1, 25, "desc");
		System.out.println("map >>" + map.get("list"));
		Assert.assertNotNull(map);
	}

	@Test
	public void testSelectBoardByTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBoardById() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertBoard() {
		Board board = new Board("root","테스트","수다","이 내용은 나도 몰라요");
		for(int i = 1; i <= 2; i++) {
			board.setTitle("제목" + i);	
			dao.insertBoard(board);
		}
	
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBoard() {
		fail("Not yet implemented");
	}

}
