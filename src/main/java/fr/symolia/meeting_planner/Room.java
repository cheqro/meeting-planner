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

	public boolean isReserved(LocalTime startTime, LocalTime endTime) {
		for (Meeting meeting : meetings) {
			if (meeting.getStartTime().equals(startTime) || meeting.getEndTime().equals(endTime)) {
//				System.out.println("startTime " + startTime);
//				System.out.println("meeting.getStartTime() " + meeting.getStartTime());
				return true;
			}
			if (meeting.getStartTime().isBefore(endTime) && meeting.getEndTime().isAfter(startTime)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasEquipment(ResourceType requiredEquipment) {
		System.err.println(requiredEquipment + "  <<>>" + equipment.contains(requiredEquipment));
		return equipment.contains(requiredEquipment);
	}

	public boolean hasCapacity(int minCapacity) {
		return capacity >= minCapacity;
	}

	@Override
	public String toString() {
		return "Room " + name;
	}

	public boolean isRoomAvailable(LocalTime startTime) {
		for (Meeting meeting : meetings) {
			if (meeting.getStartTime().equals(startTime)) {
				return false;
			}
		}
		return true;
	}

	public boolean getNextReservationTime(LocalTime endTime) {
		boolean nextReservation = true;

		for (Meeting meeting : meetings) {
			LocalTime meetingEndTime = meeting.getEndTime();
			LocalTime meetingStartTime = meeting.getStartTime();

			if (meetingEndTime.isAfter(endTime)) {
				if (endTime.plusHours(1).isAfter(meetingStartTime)) {
					nextReservation = false;
				}
			}
		}

		return nextReservation;
	}

	public void addMeetings(Meeting meetings) {
		this.meetings.add(meetings);
	}

	public boolean hasMeeting(Meeting meeting) {
		return meetings.contains(meeting);
	}

}
