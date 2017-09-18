package com.jhdit.java.junit;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class JUnitVerificationTest	{
	@Test(expected= IndexOutOfBoundsException.class)
	public void empty() {
	    new ArrayList<Object>().get(0);
	}
	
    private Collection<String> collection;
 
    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code   
    	System.out.println("@BeforeClass - oneTimeSetUp");
    }
 
    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
    }
 
    @Before
    public void setUp() {
        collection = new ArrayList<>();
        System.out.println("@Before - setUp");
    }
 
    @After
    public void tearDown() {
        collection.clear();
        System.out.println("@After - tearDown");
    }
 
    @Test
    public void testEmptyCollection() {
        assertTrue(collection.isEmpty());
        System.out.println("@Test - testEmptyCollection");
    }
 
    @Test
    public void testOneItemCollection() {
        collection.add("A test item!");
        assertEquals(1, collection.size());
        System.out.println("@Test - testOneItemCollection");
    }
	
}