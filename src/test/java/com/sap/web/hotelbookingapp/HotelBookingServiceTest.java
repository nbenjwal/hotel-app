package com.sap.web.hotelbookingapp;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class HotelBookingServiceTest {
	private static HotelBookingService service = null;

	@BeforeClass
	public static void start() {
		service = new HotelBookingService(3);
	}

	@Test
	public void test1() {
		assertEquals("Decline", service.bookRooms(-4, 2));
	}

	@Test
	public void test2() {
		assertEquals("Decline", service.bookRooms(200, 400));
	}

	@Test
	public void test3() {
		assertEquals("Accept", service.bookRooms(0, 5));
	}

	@Test
	public void test4() {
		assertEquals("Accept", service.bookRooms(7, 13));
	}

	@Test
	public void test5() {
		assertEquals("Accept", service.bookRooms(0, 365));
	}

}
