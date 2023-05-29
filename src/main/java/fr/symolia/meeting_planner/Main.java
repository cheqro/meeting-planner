package fr.symolia.meeting_planner;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	private static MeetingPlanner meetingPlanner;

	public static void main(String[] args) {
		meetingPlanner = new MeetingPlanner();
		// Add rooms with their details
		meetingPlanner.addRoom(new Room("E1001", 23, Collections.emptyList()));
		meetingPlanner.addRoom(new Room("E1002", 10, Arrays.asList(ResourceType.ECRAN)));
		meetingPlanner.addRoom(new Room("E1003", 8, Arrays.asList(ResourceType.PIEUVRE)));
		meetingPlanner.addRoom(new Room("E1004", 4, Arrays.asList(ResourceType.TABLEAU)));
		meetingPlanner.addRoom(new Room("E2001", 4, Collections.emptyList()));
		meetingPlanner.addRoom(new Room("E2002", 15, Arrays.asList(ResourceType.ECRAN, ResourceType.WEBCAM)));
		meetingPlanner.addRoom(new Room("E2003", 7, Collections.emptyList()));
		meetingPlanner.addRoom(new Room("E2004", 9, Arrays.asList(ResourceType.TABLEAU)));
		meetingPlanner.addRoom(
				new Room("E3001", 13, Arrays.asList(ResourceType.ECRAN, ResourceType.WEBCAM, ResourceType.PIEUVRE)));
		meetingPlanner.addRoom(new Room("E3002", 8, Collections.emptyList()));
		meetingPlanner.addRoom(new Room("E3003", 9, Arrays.asList(ResourceType.ECRAN, ResourceType.PIEUVRE)));
		meetingPlanner.addRoom(new Room("E3004", 4, Collections.emptyList()));

		// Create the list of meetings
		List<Meeting> meetings = List.of(
				new Meeting("Réunion 1", LocalTime.of(9, 0), LocalTime.of(10, 0), MeetingType.VC, 8),
				new Meeting("Réunion 2", LocalTime.of(9, 0), LocalTime.of(10, 0), MeetingType.VC, 6),
				new Meeting("Réunion 3", LocalTime.of(11, 0), LocalTime.of(12, 0), MeetingType.RC, 4),
				new Meeting("Réunion 4", LocalTime.of(11, 0), LocalTime.of(12, 0), MeetingType.RS, 2),
				new Meeting("Réunion 5", LocalTime.of(11, 0), LocalTime.of(12, 0), MeetingType.SPEC, 9),
				new Meeting("Réunion 6", LocalTime.of(9, 0), LocalTime.of(10, 0), MeetingType.RC, 7),
				new Meeting("Réunion 7", LocalTime.of(8, 0), LocalTime.of(9, 0), MeetingType.VC, 9),
				new Meeting("Réunion 8", LocalTime.of(8, 0), LocalTime.of(9, 0), MeetingType.SPEC, 10),
				new Meeting("Réunion 9", LocalTime.of(9, 0), LocalTime.of(10, 0), MeetingType.SPEC, 5),
				new Meeting("Réunion 10", LocalTime.of(9, 0), LocalTime.of(10, 0), MeetingType.RS, 4),
				new Meeting("Réunion 11", LocalTime.of(9, 0), LocalTime.of(10, 0), MeetingType.RC, 8),
				new Meeting("Réunion 12", LocalTime.of(11, 0), LocalTime.of(12, 0), MeetingType.VC, 12),
				new Meeting("Réunion 13", LocalTime.of(11, 0), LocalTime.of(12, 0), MeetingType.SPEC, 5),
				new Meeting("Réunion 14", LocalTime.of(8, 0), LocalTime.of(9, 0), MeetingType.VC, 3),
				new Meeting("Réunion 15", LocalTime.of(8, 0), LocalTime.of(9, 0), MeetingType.SPEC, 2),
				new Meeting("Réunion 16", LocalTime.of(8, 0), LocalTime.of(9, 0), MeetingType.VC, 12),
				new Meeting("Réunion 17", LocalTime.of(10, 0), LocalTime.of(11, 0), MeetingType.VC, 6),
				new Meeting("Réunion 18", LocalTime.of(11, 0), LocalTime.of(12, 0), MeetingType.RS, 2),
				new Meeting("Réunion 19", LocalTime.of(9, 0), LocalTime.of(10, 0), MeetingType.RS, 4),
				new Meeting("Réunion 20", LocalTime.of(9, 0), LocalTime.of(10, 0), MeetingType.RC, 7));

		// Print the best room; otherwise, print null.
		for (Meeting meeting : meetings) {
			Room bestRoom = meetingPlanner.findBestRoomForMeeting(meeting);
			System.out.println("The best meeting room " + meeting + " is : " + bestRoom);
		}
	}
}
