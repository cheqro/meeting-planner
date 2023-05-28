package fr.symolia.meeting_planner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MeetingPlanner {
	private List<Room> rooms;

	public MeetingPlanner() {
		rooms = new ArrayList<>();
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public Room findBestRoomForMeeting(Meeting meeting) {
		List<Room> availableRooms = new ArrayList<>();

		// Filter rooms based on capacity, equipment, and availability

		for (Room room : rooms) {
			boolean isAvailable = room.isReserved(meeting.getStartTime(), meeting.getEndTime());
//			System.err.println("Check if the room " + room + " is available 1 hour before the next reservation "
//					+ isRoomAvailable(room, meeting.getStartTime(), meeting.getEndTime()));

			if (room.getCapacity() < meeting.getNumberOfPeople()
					|| !isRoomAvailable(room, meeting.getStartTime(), meeting.getEndTime())) {
//				System.out.println("continue for " + room);
//				System.out.println(room.getCapacity() < meeting.getNumberOfPeople());
//				System.out.println(!isRoomAvailable(room, meeting.getStartTime(), meeting.getEndTime()));
				continue;
			}

//			System.out.println(room.getName() + " " + room.getEquipment() + " is available between "
//					+ meeting.getStartTime() + " " + meeting.getEndTime() + "                         :"
//					+ isRoomAvailable(room, meeting.getStartTime(), meeting.getEndTime()));

			if (meeting.getType() == MeetingType.VC && room.hasEquipment(ResourceType.ECRAN)
					&& room.hasEquipment(ResourceType.PIEUVRE) && room.hasEquipment(ResourceType.WEBCAM)
					&& !room.isReserved(meeting.getStartTime(), meeting.getEndTime())) {
				availableRooms.add(room);
			} else if (meeting.getType() == MeetingType.SPEC && room.hasEquipment(ResourceType.TABLEAU)
					&& !room.isReserved(meeting.getStartTime(), meeting.getEndTime())) {
				availableRooms.add(room);
			} else if (meeting.getType() == MeetingType.RS && room.hasCapacity(3)
					&& !room.isReserved(meeting.getStartTime(), meeting.getEndTime())) {
				availableRooms.add(room);
			} else if (meeting.getType() == MeetingType.RC && room.hasEquipment(ResourceType.ECRAN)
					&& room.hasEquipment(ResourceType.PIEUVRE) && room.hasEquipment(ResourceType.TABLEAU)
					&& !room.isReserved(meeting.getStartTime(), meeting.getEndTime())) {
				availableRooms.add(room);
			}
		}

		// Sort available rooms by capacity (ascending order).
		availableRooms
				.sort(Comparator.comparingInt(Room::getCapacity).thenComparing(liste -> liste.getEquipment().size()));
//		System.out.println("availableRooms for this meeting : " + availableRooms);
		// Return the room with the smallest capacity (if available)
		if (!availableRooms.isEmpty()) {
			availableRooms.get(0).addMeeting(meeting);
			return availableRooms.get(0);

		}

		return null; // No available room found
	}

	public boolean isRoomAvailable(Room room, LocalTime startTime, LocalTime endTime) {
		// Check if the room is reserved during the specified time
//		System.out.println("!!!!!!!!" + room.isReserved(startTime, endTime));

		if (room.isReserved(startTime, endTime)) {
//			System.out.println("!!!!!!!!" + room.isReserved(startTime, endTime));
			return false;
		}

		// Check if the room is available 1 hour before the next reservation
		boolean nextReservation = room.getNextReservationTime(endTime);
//		System.out.println("nextReservation " + nextReservation);

		return nextReservation;
	}

	public Room getMeetingRoom(Meeting meeting) {
		for (Room room : rooms) {
			if (room.hasMeeting(meeting)) {
				return room;
			}
		}
		return null;
	}

}
