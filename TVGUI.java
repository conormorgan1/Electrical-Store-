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
import model.TV;

public class TVGUI extends Tab {
TV tv;
public TVGUI() {
	try {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	} catch (ClassNotFoundException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}	

	setText("TV");
	BorderPane bp = new BorderPane();

	Label title = new Label("TVs");
	title.setFont(new Font("Impact", 40));
	title.setTextFill(Color.BLACK);
	title.setAlignment(Pos.TOP_CENTER);

    
    Label lb = new Label("TV No:");		
	Label make = new Label("Make:");	
	Label model = new Label("Model:");	
	Label price = new Label("Price:");	
	Label screen = new Label("ScreenSize:");
	Label remove = new Label("Tv ID:");
	
    TextField tvNoTf = new TextField();		
    TextField makeTf = new TextField();
    TextField priceTf = new TextField();
    TextField modelTf = new TextField();
    TextField screenTf = new TextField();
    TextField removeTf = new TextField();
        
    Button create = new Button ("Create Tv");
    create.setOnAction((ActionEvent event) ->{
    	
		String tvId = tvNoTf.getText();
		int id = Integer.parseInt(tvId);
		String name = makeTf.getText();
		String type = modelTf.getText();
		String getCost = priceTf.getText();
		double cost = Double.parseDouble(getCost);
		String size = screenTf.getText();
		int screenSize = Integer.parseInt(size);
		tv = new TV(id,name,type,cost,screenSize);
				    
    try {
		Controller.createTvButton(tv);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    });
    
    Button update = new Button("Update Tv");
    update.setOnAction((ActionEvent event) ->{

		String tvId = tvNoTf.getText();
		int id = Integer.parseInt(tvId);
		String name = makeTf.getText();
		String type = modelTf.getText();
		String getCost = priceTf.getText();
		double cost = Double.parseDouble(getCost);
		String size = screenTf.getText();
		int screenSize = Integer.parseInt(size);
		tv = new TV(id,name,type,cost,screenSize);

    try {
		Controller.updateTvButton(tv);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    });
    
    Button delete = new Button("Remove Tv");
    delete.setOnAction((ActionEvent event) ->{
    	String orderID = removeTf.getText();
    	int orderNo = Integer.parseInt(orderID);
    	try {
			Controller.removeTvButton(orderNo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    });
 /*   Button list = new Button("Show Tv");
    list.setOnAction((ActionEvent event) ->{
    	try {
			Controller.showOrdersTable();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    });
    */
	Button quit = new Button ("Quit");
	quit.setOnAction(e -> Controller.close());

	GridPane gp = new GridPane();
	gp.setHgap(15);
	gp.setVgap(15);
	gp.setPadding(new Insets(10, 15, 10, 15));
	
	VBox vb = new VBox(10);
	vb.getChildren().addAll(lb ,make ,model, price, screen);
	VBox vb1 = new VBox(10);
	vb1.getChildren().addAll(tvNoTf, makeTf, modelTf, priceTf, screenTf);
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
    GridPane.setConstraints(screen, 0, 4); 	
    GridPane.setConstraints(tvNoTf, 1, 0); 
    GridPane.setConstraints(makeTf, 1, 1); 
    GridPane.setConstraints(modelTf, 1, 2); 
    GridPane.setConstraints(priceTf, 1, 3); 
    GridPane.setConstraints(screenTf, 1, 4); 
//	GridPane.setConstraints(create, 0, 5);
	GridPane.setConstraints(opt, 1, 5);
	GridPane.setConstraints(removeHB, 0, 6);
	gp.getChildren().addAll(lb,make, model, price, screen, tvNoTf, makeTf,modelTf,priceTf,screenTf,opt,removeHB);
	HBox titleHB = new HBox();
	titleHB.getChildren().add(title);
	titleHB.setAlignment(Pos.CENTER);

	bp.setTop(titleHB);
	bp.setCenter(gp);
	bp.setBottom(hb);
	
	setContent(bp);
	
}

}