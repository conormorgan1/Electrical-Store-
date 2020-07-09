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
import model.Customer;

public class CustomerGUI extends Tab {
Customer cust;
public CustomerGUI()
{

	setText("Customer");
	BorderPane bp = new BorderPane();

	Label title = new Label("Customers");
	title.setFont(new Font("Impact", 40));
	title.setTextFill(Color.BLACK);
	title.setAlignment(Pos.TOP_CENTER);
    
    Label lb = new Label("Customer No:");		
	Label first = new Label("First Name:");	
	Label last = new Label("Last Name:");	
	Label address = new Label("Address:");	
	Label tel = new Label("Tel No:");
	Label remove = new Label("Customer ID:");
	
    TextField customerNoTf = new TextField();		
    TextField firstTf = new TextField();
    TextField lastTf = new TextField();
    TextField addressTf = new TextField();
    TextField telTf = new TextField();
    TextField removeTf = new TextField();
        
    Button create = new Button ("Create Customer");
    create.setOnAction((ActionEvent event) ->{
    	
    	String custId = customerNoTf.getText();
		int id = Integer.parseInt(custId);
		String firstN = firstTf.getText();
		String lastN = lastTf.getText();
		String addr = addressTf.getText();
		String telNum = telTf.getText();
		long telN = Long.parseLong(telNum);
		cust = new Customer(id, firstN, lastN, addr, telN);

		
    try {
    	Controller.createCustomerButton(cust);
    	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    });
    
    Button update = new Button("Update Customer");
    update.setOnAction((ActionEvent event) ->{

		String custId = customerNoTf.getText();
		int id = Integer.parseInt(custId);
		String firstN = firstTf.getText();
		String lastN = lastTf.getText();
		String addr = addressTf.getText();
		String telNum = telTf.getText();
		long telN = Long.parseLong(telNum);
		cust = new Customer(id, firstN, lastN, addr, telN);
    try {
		Controller.updateCustomerButton(cust);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    });
    
    Button delete = new Button("Remove Customer");
    delete.setOnAction((ActionEvent event) ->{
    	String customerID = removeTf.getText();
    	int custNo = Integer.parseInt(customerID);
    	try {
			Controller.removeCustomerButton(custNo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    });
 /*   Button list = new Button("Show Customer");
    list.setOnAction((ActionEvent event) ->{
    	try {
			Controller.showCustomerTable();
			
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
	vb.getChildren().addAll(lb ,first ,last, address, tel);
	VBox vb1 = new VBox(10);
	vb1.getChildren().addAll(customerNoTf, firstTf, lastTf, addressTf, telTf);
	HBox hb = new HBox(5);
	hb.getChildren().addAll(quit);
	HBox removeHB = new HBox(5);
	hb.setAlignment(Pos.CENTER);
	removeHB.getChildren().addAll( remove, removeTf, delete);
	HBox opt = new HBox();
	opt.getChildren().addAll(create, update);

    GridPane.setConstraints(lb, 0, 0); 	
    GridPane.setConstraints(first, 0, 1); 	
    GridPane.setConstraints(last, 0, 2); 
    GridPane.setConstraints(address, 0, 3); 	
    GridPane.setConstraints(tel, 0, 4); 	
    GridPane.setConstraints(customerNoTf, 1, 0); 
    GridPane.setConstraints(firstTf, 1, 1); 
    GridPane.setConstraints(lastTf, 1, 2); 
    GridPane.setConstraints(addressTf, 1, 3); 
    GridPane.setConstraints(telTf, 1, 4); 
//	GridPane.setConstraints(create, 0, 5);
	GridPane.setConstraints(opt, 1, 5);
	GridPane.setConstraints(removeHB, 0, 6);
	gp.getChildren().addAll(lb,first ,last, address, tel, customerNoTf, firstTf, lastTf, addressTf, telTf ,opt,removeHB);
	HBox titleHB = new HBox();
	titleHB.getChildren().add(title);
	titleHB.setAlignment(Pos.CENTER);

	bp.setTop(titleHB);
	bp.setCenter(gp);
	bp.setBottom(hb);
	
	setContent(bp);
	
}

}