package model;

import javafx.scene.image.Image;

public class Participant implements Comparable<Participant> {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String country;
	private Image photo;
	private String birthday;
	private Participant left;
	private Participant right;
	private Participant previous;
	private Participant next;

	public Participant(int id, String firstName, String lastName, String email, String gender, String country,
			Image photo, String birthday) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.country = country;
		this.photo = photo;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Participant getLeft() {
		return left;
	}

	public void setLeft(Participant left) {
		this.left = left;
	}

	public Participant getRight() {
		return right;
	}

	public void setRight(Participant rigth) {
		this.right = rigth;
	}

	public Participant getPrevious() {
		return previous;
	}

	public void setPrevious(Participant previous) {
		this.previous = previous;
	}

	@Override
	public int compareTo(Participant otherParticipant) {
		int comparation = 0;
		if (id < otherParticipant.id) {
			comparation = -1;
		} else if (id > otherParticipant.id) {
			comparation = 1;
		}
		return comparation;
	}

	public Participant getNext() {
		return next;
	}

	public void setNext(Participant next) {
		this.next = next;
	}
}