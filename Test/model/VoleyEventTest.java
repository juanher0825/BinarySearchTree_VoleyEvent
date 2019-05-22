package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;

class VoleyEventTest {
	
	
	private VoleyEvent voleyEvent;
	
	public void setupScenary1() {
		voleyEvent = new VoleyEvent();
	}
	
	@Test
	public void loadTest() throws IOException {
		setupScenary1();
		try {
			voleyEvent.load();
			String a = voleyEvent.getFirst().getFirstName();
			assertTrue(a.equals("Ariel") || a.equals("Max") || a.equals("Frants") || a.equals("Artemis") || a.equals("Augustus") );
			
		}catch (IOException e) {
			fail("Produjo una IOException");
			
		}
	}
	
	@Test
	public void addParticipantToTreeTest() {
		setupScenary1();
		try {
		Participant a = new Participant(0, "Juan", "Hernandez", "juanher0825@gmail.com", "Masculino", "Colombia", null, 
				"08/25/2019" );
		voleyEvent.addParticipantToTree(a);
		assertTrue(voleyEvent.getRoot().getFirstName().equals("Juan"));
		} catch (NullPointerException e) {
			fail("Produjo una excepcion");
		}
		
	}
	
	@Test
	 public void searchParticipantTest() throws IOException{
		setupScenary1();
		try {
			voleyEvent.load();
			Participant a = voleyEvent.searchSpectator(1);
			assertTrue(a.getLastName().equals("Fairney") || a.getLastName().equals("Giddons") || a.getLastName().equals("Althorp") || a.getLastName().equals("Shitliffe") || a.getLastName().equals("Mayhew"));
			
		}catch (IOException e) {
			fail("Produjo una excepcion");
		}
	}
	
	@Test
	public void addParticipantWithFirstTest() {
		setupScenary1();
		try {
			Participant a = new Participant(0, "Juan", "Hernandez", "juanher0825@gmail.com", "Masculino", "Colombia", null, 
					"08/25/2019" );
			voleyEvent.setFirst(a);
			Participant b = new Participant(1, "Daniela", "Perez", "juanher0825@gmail.com", "Masculino", "Colombia", null, 
					"08/25/2019" );
			voleyEvent.addParticipantToTree(b);
			assertTrue(voleyEvent.getFirst().getNext().getFirstName().equals("Daniela"));
		}catch (NullPointerException e) {
			
		}
	}
	

}
