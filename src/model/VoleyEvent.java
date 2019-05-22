package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javafx.scene.image.Image;

public class VoleyEvent {
	
	public static final String ROUTE = "data/MOCK_DATA.csv";
	private Participant root;
	private Participant first;	

	public VoleyEvent() {	
		
	}
	
	public String load() throws IOException {
		FileReader fr = new FileReader(new File(ROUTE));
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		line = br.readLine();
		int times = 0;
		while (line != null) {
			String[] parts = line.split(",");
			URL uRL = new URL(parts[6]);
			URLConnection uRLConnection = uRL.openConnection();
			InputStream input = uRLConnection.getInputStream();
			Image image = new Image(input);
			Participant toAdd = new Participant(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], image, parts[7]);
			addParticipantToTree(toAdd);
			line = br.readLine();
			times++;
		}
		fr.close();
		br.close();
		aleatoryParticipants(times);
		return ROUTE;
	}
	
	public void addParticipantToTree(Participant toAdd) {
		addParticipantToTree(toAdd,root);
	}
	
	private void addParticipantToTree(Participant toAdd,Participant current) {
		if (root == null) {
			root = toAdd;
		} else {
			if (toAdd.compareTo(current) < 0) {
				if (current.getLeft() == null) {
					current.setLeft(toAdd);
					toAdd.setPrevious(current);
				} else {
					addParticipantToTree(toAdd,current.getLeft());
				}
			} else {
				if (current.getRight() == null) {
					current.setRight(toAdd);
					toAdd.setPrevious(current);

				} else {
					addParticipantToTree(toAdd,current.getRight());
				}
			}
		}
	}
	
	private void aleatoryParticipants(int size) {
		size = (int) (size * 0.5);
		for (int i = 0; i < size; i++) {
			int number = (int) (Math.random() * size) + 1;
			Participant temp = searchSpectator(number);
			addOfficialParticipants(temp);
		}
	}
	
	public Participant searchSpectator(int id) {
		Participant toSearch = new Participant(id,"","","","","",null,"");
		return searchSpectator(toSearch,root);
	}
	
	private Participant searchSpectator(Participant toSearch,Participant current) {
		if(current!=null) {
			if(toSearch.compareTo(current)<0) {
				if(current.getLeft()!=null){
					return searchSpectator(toSearch,current.getLeft());
				}else {
					return searchSpectator(toSearch,current.getRight());
				}
			}else if(toSearch.compareTo(current)>0){
				if(current.getRight()!=null) {
					return searchSpectator(toSearch,current.getRight());
				}else {
					return searchSpectator(toSearch,current.getLeft());
				}
			}else {
				return current;
			}
		}
		return current;
	}
		
	private void addOfficialParticipants(Participant toAdd) {
		if(first == null){
			first = toAdd;
		}else{
			Participant current = first;
			while(current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(toAdd);
			Participant temp = current;
			current = current.getNext();
			current.setPrevious(temp);
		}
	}

	public Participant searchOfficialParticipant(int id) {
		Participant current = first;
		Participant returned = null;
		boolean stop = false;
		while (current != null && !stop) {
			if (current.getId() == id) {
				stop = true;
				returned = current;
			} else {
				current = current.getNext();
			}
		}
		return returned;
	}

	public Participant getFirst() {
		return first;
	}

	public void setFirst(Participant first) {
		this.first = first;
	}

	public Participant getRoot() {
		return root;
	}

	public void setRoot(Participant root) {
		this.root = root;
	}
}
