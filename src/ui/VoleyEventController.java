package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import model.Participant;
import model.VoleyEvent;

public class VoleyEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane1;
    
    @FXML
    private AnchorPane anchorPane2;
    
    @FXML
    private TextField route;

    @FXML
    private TextField idToSearchSpectator;
    
    @FXML
    private TextField idToSearchParticipant;

    @FXML
    private Label noSpectatorWasFound;

    @FXML
    private Label timeSpectators;

    @FXML
    private Label loadedCorrectly;
    
    @FXML
    private Label noParticipantWasFound;

    @FXML
    private Label timeParticipant;

    @FXML
    private Label id;

    @FXML
    private Label first_name;

    @FXML
    private Label last_name;

    @FXML
    private Label email;

    @FXML
    private Label gender;

    @FXML
    private Label country;

    @FXML
    private Label birthday;
    
    @FXML
    private ImageView image;
   
    @FXML
    private Button cargar;

    @FXML
    private ImageView participant;

    @FXML
    private Label showsId;
    
    private VoleyEvent voleyEvent;
    
    @FXML
    void initialize() {
    	timeParticipant.setVisible(false);
    	timeSpectators.setVisible(false);
    	noSpectatorWasFound.setVisible(false);
    	loadedCorrectly.setVisible(false);
    	noParticipantWasFound.setVisible(false);
    	voleyEvent = new VoleyEvent();
    }    
    
    @FXML
    void explore(ActionEvent event) {
    	route.setText("data/MOCK_DATA.csv");
    }

    @FXML
    void load(ActionEvent event) {
    	try {
    		cargar.setDisable(true);
			voleyEvent.load();
			loadedCorrectly.setVisible(true);
		} catch (IOException e) {
			Alert info = new Alert(AlertType.ERROR);
			info.setTitle("ERROR");
			info.setHeaderText(null);
			info.initStyle(StageStyle.UTILITY);
			info.setContentText("please select a valid file");
			info.show();
		}
    }

	@FXML
	void lookForSpectators(ActionEvent event) {
		long time = System.currentTimeMillis();
		try {
    		int id = Integer.parseInt(idToSearchSpectator.getText());
    		showData(voleyEvent.searchSpectator(id));
    		timeSpectators.setVisible(true);
    		timeSpectators.setText("Time: " + (System.currentTimeMillis() - time) + " ms");
    		idToSearchSpectator.setText("");
    		idToSearchParticipant.setText("");
    	}catch (NumberFormatException e) {
    		Alert score = new Alert(AlertType.ERROR);
        	score.setTitle("IV Copa Panamericana de Voleibol Masculino Sub-21");
        	score.initStyle(StageStyle.DECORATED);
        	score.setContentText("Please introduce a number");
        	score.show();
    	}catch(NullPointerException e) {
    		Alert score = new Alert(AlertType.ERROR);
        	score.setTitle("IV Copa Panamericana de Voleibol Masculino Sub-21");
        	score.initStyle(StageStyle.DECORATED);
        	score.setContentText("The spectator Not Found");
        	score.show();
    	}
	}

	@FXML
	void searchParticipant(ActionEvent event) {
		long time = System.currentTimeMillis();
		try {
    		int id = Integer.parseInt(idToSearchParticipant.getText());
    		showData(voleyEvent.searchOfficialParticipant(id));
    		timeParticipant.setVisible(true);
    		timeParticipant.setText("Time: " + (System.currentTimeMillis() - time) + " ms");
    		idToSearchSpectator.setText("");
    		idToSearchParticipant.setText("");
    	}catch (NumberFormatException e) {
    		Alert score = new Alert(AlertType.ERROR);
        	score.setTitle(" IV Copa Panamericana de Voleibol Masculino Sub-21");
        	score.initStyle(StageStyle.DECORATED);
        	score.setContentText("Please introduce a number");
        	score.show();
    	}catch(NullPointerException e) {
    		Alert score = new Alert(AlertType.ERROR);
        	score.setTitle(" IV Copa Panamericana de Voleibol Masculino Sub-21");
        	score.initStyle(StageStyle.DECORATED);
        	score.setContentText("This spectador was not selected ass a oficial participant");
        	score.show();
    	}
	}

    @FXML
    void paintParticipant(ActionEvent event) {
    	try {
    		showParticipant(voleyEvent.getFirst());	
    	}catch(NullPointerException e) {
    		Alert score = new Alert(AlertType.ERROR);
        	score.setTitle("IV Copa Panamericana de Voleibol Masculino Sub-21");
        	score.initStyle(StageStyle.DECORATED);
        	score.setContentText("The participants were not found or don't exist.");
        	score.show();
    	}
    }

    @FXML
    void paintSpectators(ActionEvent event) {
    	try {
    		showParticipant(voleyEvent.getRoot());	
    	}catch(NullPointerException e) {
    		Alert score = new Alert(AlertType.ERROR);
        	score.setTitle("IV Copa Panamericana de Voleibol Masculino Sub-21");
        	score.initStyle(StageStyle.DECORATED);
        	score.setContentText("The participants were not found or don't exist.");
        	score.show();
    	}
    }
    
    private void showData(Participant shown) {
		image.setImage(shown.getPhoto());
		id.setText("Id:  " + shown.getId());
		first_name.setText("First name:  " + shown.getFirstName());
		last_name.setText("Last name:  " + shown.getLastName());
		email.setText("Email:  " + shown.getEmail());
		gender.setText("Gender:  " + shown.getGender());
		country.setText("Country:  " + shown.getCountry());
		birthday.setText("Birthday:  " + shown.getBirthday());
    }
    
	private void showParticipant(Participant shown) {
		participant.setImage(shown.getPhoto());
		showsId.setText("Id:  " + shown.getId() + "\nName:  " + shown.getFirstName());
	}
}