package fr.symolia.meeting_planner;

import java.time.LocalTime;

public class Meeting {
	private String name;
	private LocalTime startTime;
	private LocalTime endTime;
	private MeetingType type;
	private int numberOfPeople;

	public Meeting(String name, LocalTime startTime, LocalTime endTime, MeetingType type, int numberOfPeople) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.numberOfPeople = numberOfPeople;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public MeetingType getType() {
		return type;
	}

	public void setType(MeetingType type) {
		this.type = type;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	@Override
	public String toString() {
		return "Réunion : " + name;
	}

}
