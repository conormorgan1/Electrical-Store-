package view;

import java.sql.SQLException;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Phone;

public class PhoneGUI extends Tab {
Phone phone;
public PhoneGUI() {
		
	setText("Phone");
	BorderPane bp = new BorderPane();

	Label title = new Label("Phones");
	title.setFont(new Font("Impact", 40));
	title.setTextFill(Color.BLACK);
	title.setAlignment(Pos.TOP_CENTER);
    
    Label lb = new Label("Phone No:");		
	Label make = new Label("Make:");	
	Label model = new Label("Model:");	
	Label price = new Label("Price:");	
	Label storage = new Label("Storage:");
	Label remove = new Label("Phone ID:");
	
    TextField phoneNoTf = new TextField();		
    TextField makeTf = new TextField();
    TextField priceTf = new TextField();
    TextField modelTf = new TextField();
    TextField storageTf = new TextField();
    TextField removeTf = new TextField();
        
    Button create = new Button ("Create Phone");
    create.setOnAction((ActionEvent event) ->{
    	
		String tvId = phoneNoTf.getText();
		int id = Integer.parseInt(tvId);
		String name = makeTf.getText();
		String type = modelTf.getText();
		String getCost = priceTf.getText();
		double cost = Double.parseDouble(getCost);
		String getStorage = storageTf.getText();
		int storageSize = Integer.parseInt(getStorage);
		
		phone = new Phone(id, name, type, cost, storageSize);
		
				    
    try {
		Controller.createPhoneButton(phone );
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    });
    
    Button update = new Button("Update Phone");
    update.setOnAction((ActionEvent event) ->{

		String tvId = phoneNoTf.getText();
		int id = Integer.parseInt(tvId);
		String name = makeTf.getText();
		String type = modelTf.getText();
		String getCost = priceTf.getText();
		double cost = Double.parseDouble(getCost);
		String getStorage = storageTf.getText();
		int storageSize = Integer.parseInt(getStorage);
		 phone = new Phone(id, name, type, cost, storageSize);
		
    try {
		Controller.updatePhoneButton(phone);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    });
    
    Button delete = new Button("Remove Phone");
    delete.setOnAction((ActionEvent event) ->{
    	String phoneID = removeTf.getText();
    	int phoneNo = Integer.parseInt(phoneID);
    	try {
			Controller.removePhoneButton(phoneNo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    });

	Button quit = new Button ("Quit");
	quit.setOnAction(e -> Controller.close());

	GridPane gp = new GridPane();
	gp.setHgap(15);
	gp.setVgap(15);
	gp.setPadding(new Insets(10, 15, 10, 15));
	
	VBox vb = new VBox(10);
	vb.getChildren().addAll(lb ,make ,model, price, storage);
	VBox vb1 = new VBox(10);
	vb1.getChildren().addAll(phoneNoTf, makeTf, modelTf, priceTf, storageTf);
	HBox hb = new HBox(5);
	hb.getChildren().addAll(quit);
	HBox removeHB = new HBox(5);
	hb.setAlignment(Pos.CENTER);
	removeHB.getChildren().addAll( remove, removeTf, delete);
	HBox opt = new HBox();
	opt.getChildren().addAll(create, update);

    GridPane.setConstraints(lb, 0, 0); 	
    GridPane.setConstraints(make, 0, 1); 	
    GridPane.setConstraints(model, 0, 2); 
    GridPane.setConstraints(price, 0, 3); 	
    GridPane.setConstraints(storage, 0, 4); 	
    GridPane.setConstraints(phoneNoTf, 1, 0); 
    GridPane.setConstraints(makeTf, 1, 1); 
    GridPane.setConstraints(modelTf, 1, 2); 
    GridPane.setConstraints(priceTf, 1, 3); 
    GridPane.setConstraints(storageTf, 1, 4); 
	GridPane.setConstraints(opt, 1, 5);
	GridPane.setConstraints(removeHB, 0, 6);
	gp.getChildren().addAll(lb,make, model, price, storage, phoneNoTf, makeTf,modelTf,priceTf,storageTf,opt,removeHB);
	HBox titleHB = new HBox();
	titleHB.getChildren().add(title);
	titleHB.setAlignment(Pos.CENTER);

	bp.setTop(titleHB);
	bp.setCenter(gp);
	bp.setBottom(hb);
	
	setContent(bp);
	
}

}