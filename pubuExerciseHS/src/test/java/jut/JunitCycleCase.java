package jut;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.idv.hunter.tool.HunterDebug;

public class JunitCycleCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HunterDebug.traceMessage();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HunterDebug.traceMessage();
	}

	@Before
	public void setUp() throws Exception {
		HunterDebug.traceMessage();
	}

	@After
	public void tearDown() throws Exception {
		HunterDebug.traceMessage();
	}

	@Test
	public void test1() {
		HunterDebug.traceMessage();
//		fail("Not yet implemented");
	}

	@Test
	public void test2() {
		HunterDebug.traceMessage();
//		fail("Not yet implemented");
		
	}

}
