package com.sap.web.hotelbookingapp;

import java.util.ArrayList;
import java.util.List;

public class HotelBookingService {

	private int roomCount;

	private static int d = 365;

	private int[][] db;

	/**
	 * initialize HotelBookingService with number of rooms;
	 * 
	 * @param roomCount
	 *            number of rooms in hotel.
	 */
	public HotelBookingService(int roomCount) {
		this.roomCount = roomCount;
		db = new int[roomCount][++d];
	}

	/**
	 * This method is responsible for booking the room(s).
	 * 
	 * @param s
	 *            booking start day.
	 * @param e
	 *            booking end day.
	 * @return availability (Accept/Decline).
	 */
	public String bookRooms(int s, int e) {
		if (s < 0 || e < 0 || e < s || e > 365) {
			// System.out.println("Invalid input.");
			return "Decline";
		}

		int dayCount = e - s + 1;
		List<String> list = new ArrayList<>();
		/** Check in each room. */
		for (int i = 0; i < roomCount; i++) {
			int count = 0;
			for (int j = s; j <= e; j++) {
				if (checkAvailibility(i, j)) {
					++count;
					String token = i + "_" + j;
					list.add(token);
				}
			}
			if (dayCount == count) {
				reserveRoom(list);
				// System.out.println("Room" + i + " booked successfully. from Day" + s + " to
				// Day" + e);
				return "Accept";
			}
			count = 0;
			list = new ArrayList<>();
		}
		if (list.size() == 0) {
			// System.out.println("Rooms are not available.");
			return "Decline";
		}
		return "Decline";
	}

	/**
	 * This method check the availability of rooms.
	 * 
	 * @param i
	 *            room number;
	 * @param j
	 *            reservation date;
	 * @return true/false if room is available.
	 */
	public boolean checkAvailibility(int i, int j) {
		if (db[i][j] == 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method reserve the room for given days.
	 * 
	 * @param data
	 *            list of room and day String "_" separated. e.g. ("0_1","0_2").
	 */
	public void reserveRoom(List<String> data) {
		for (String token : data) {
			int i = Integer.parseInt(token.split("_")[0]);
			int j = Integer.parseInt(token.split("_")[1]);
			db[i][j] = 1;
		}
	}

}
