package fr.symolia.meeting_planner;

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
		// Filter rooms based on capacity, availability and equipment.
		return rooms.stream().filter(room -> room.getCapacity() >= meeting.getNumberOfPeople())
				.filter(room -> !room.isReserved(meeting.getStartTime(), meeting.getEndTime())).filter(room -> {
					MeetingType meetingType = meeting.getType();
					return switch (meetingType) {
					case VC -> room.hasEquipment(ResourceType.ECRAN) && room.hasEquipment(ResourceType.PIEUVRE)
							&& room.hasEquipment(ResourceType.WEBCAM);
					case SPEC -> room.hasEquipment(ResourceType.TABLEAU);
					case RS -> room.hasCapacity(3);
					case RC -> room.hasEquipment(ResourceType.ECRAN) && room.hasEquipment(ResourceType.PIEUVRE)
							&& room.hasEquipment(ResourceType.TABLEAU);
					default -> false;
					};
				}).sorted(Comparator.comparingInt(Room::getCapacity).thenComparing(room -> room.getEquipment().size()))
				.findFirst().map(room -> {
					room.addMeeting(meeting);
					return room;
				}).orElse(null);
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
