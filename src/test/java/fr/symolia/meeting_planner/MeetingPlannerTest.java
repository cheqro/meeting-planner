package fr.symolia.meeting_planner;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MeetingPlannerTest {

	private MeetingPlanner meetingPlanner;

	@BeforeEach
	void setUp() {
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
	}

	@Test
	public void testMeetingPlanner() {

		// Create the list of meetings
		List<Meeting> meetings = Arrays.asList(
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

		// the meeting list has been successfully programmed
		List<String> meetingScheduledSuccessfully = Arrays.asList("Réunion 1", "Réunion 4", "Réunion 5", "Réunion 9",
				"Réunion 10", "Réunion 12", "Réunion 15", "Réunion 18", "Réunion 19");

		//
		for (Meeting meeting : meetings) {

			Room bestRoom = meetingPlanner.findBestRoomForMeeting(meeting);
			System.out.println("bestRoom : " + bestRoom);

			if (!meetingScheduledSuccessfully.contains(meeting.getName())) {
				Assertions.assertNull(bestRoom);
			} else {

				if (meeting.getName().equals("Réunion 1")) {
					Assertions.assertTrue("E3001".equals(bestRoom.getName()));
				}
				if (meeting.getName().equals("Réunion 4")) {
					Assertions.assertTrue("E2001".equals(bestRoom.getName()));

				}
				if (meeting.getName().equals("Réunion 5")) {
					Assertions.assertTrue("E2004".equals(bestRoom.getName()));

				}
				if (meeting.getName().equals("Réunion 9")) {
					Assertions.assertTrue("E2004".equals(bestRoom.getName()));

				}
				if (meeting.getName().equals("Réunion 10")) {
					Assertions.assertTrue("E2001".equals(bestRoom.getName()));

				}
				if (meeting.getName().equals("Réunion 12")) {
					Assertions.assertTrue("E3001".equals(bestRoom.getName()));

				}
				if (meeting.getName().equals("Réunion 15")) {
					Assertions.assertTrue("E1004".equals(bestRoom.getName()));

				}
				if (meeting.getName().equals("Réunion 18")) {
					Assertions.assertTrue("E3004".equals(bestRoom.getName()));

				}
				if (meeting.getName().equals("Réunion 19")) {
					Assertions.assertTrue("E3004".equals(bestRoom.getName()));

				}

				switch (meeting.getType()) {
				case VC:
					Assertions.assertNotNull(bestRoom);
					Assertions.assertTrue(bestRoom.hasEquipment(ResourceType.ECRAN));
					Assertions.assertTrue(bestRoom.hasEquipment(ResourceType.PIEUVRE));
					Assertions.assertTrue(bestRoom.hasEquipment(ResourceType.WEBCAM));
					break;
				case SPEC:
					Assertions.assertNotNull(bestRoom);
					Assertions.assertTrue(bestRoom.hasEquipment(ResourceType.TABLEAU));
					break;
				case RS:
					Assertions.assertNotNull(bestRoom);
					Assertions.assertTrue(bestRoom.getCapacity() >= 3);
					break;
				case RC:
					Assertions.assertNotNull(bestRoom);
					Assertions.assertFalse(bestRoom.hasEquipment(ResourceType.ECRAN));
					Assertions.assertFalse(bestRoom.hasEquipment(ResourceType.PIEUVRE));
					Assertions.assertTrue(bestRoom.hasEquipment(ResourceType.TABLEAU));
					break;
				default:
					break;
				}

			}

		}

	}
}
