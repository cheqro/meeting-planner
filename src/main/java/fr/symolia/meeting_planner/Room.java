package fr.symolia.meeting_planner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Room {
	private String name;
	private int capacity;
	private List<ResourceType> equipment;
	private List<Meeting> meetings;

	public Room(String name, int capacity, List<ResourceType> equipment) {
		this.name = name;
		this.capacity = capacity;
		this.equipment = equipment;
		this.meetings = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<ResourceType> getEquipment() {
		return equipment;
	}

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void addMeeting(Meeting meeting) {
		meetings.add(meeting);
	}

	public void removeMeeting(Meeting meeting) {
		meetings.remove(meeting);
	}

	// Check if the room is reserved during the specified time
	public boolean isReserved(LocalTime startTime, LocalTime endTime) {
		for (Meeting meeting : meetings) {
			LocalTime meetingStartTime = meeting.getStartTime();
			LocalTime meetingEndTime = meeting.getEndTime();

			// True if there is a meeting with the same start or end time
			if (meetingStartTime.equals(startTime) || meetingEndTime.equals(endTime)) {
				return true;
			}

			// True if there is a meeting between the given start and end time
			if (meetingStartTime.isBefore(endTime) && meetingEndTime.isAfter(startTime)) {
				return true;
			}

			// Check if the room is available 1 hour before the next reservation
			// If there is a meeting scheduled after the new meeting
			// Verify that the new meeting ends at least one hour before the next meeting

			if (meetingEndTime.isAfter(endTime) && endTime.plusHours(1).isAfter(meetingStartTime)) {
				return true;
			}
		}

		return false;
	}

	public boolean hasEquipment(ResourceType requiredEquipment) {
		return equipment.contains(requiredEquipment);
	}

	public boolean hasCapacity(int minCapacity) {
		return capacity >= minCapacity;
	}

	public void addMeetings(Meeting meetings) {
		this.meetings.add(meetings);
	}

	public boolean hasMeeting(Meeting meeting) {
		return meetings.contains(meeting);
	}

	// toString() from Meeting
	@Override
	public String toString() {
		return "Room (" + name + ", " + capacity + ", " + equipment + ", " + meetings + ")";
	}

}
