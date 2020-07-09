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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.OrderDetails;

public class OrderDetailsGUI extends Tab {
	OrderDetails od;
public OrderDetailsGUI()
{
	try {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	} catch (ClassNotFoundException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}	

	setText("Order Details");
	BorderPane bp = new BorderPane();

	Label title = new Label("Order Details");
	title.setFont(new Font("Impact", 40));
	title.setTextFill(Color.BLACK);
	title.setAlignment(Pos.TOP_CENTER);
    
    Label lb = new Label("OrderDetails No:");		
	Label name = new Label("Product Name:");	
	Label quant = new Label("Quantity:");	
	Label total = new Label("Total:");
	Label remove = new Label("Order Details No:");
	
    TextField orderDetailsNoTf = new TextField();		
    TextField nameTf = new TextField();
    TextField quantTf = new TextField();
    TextField totalTf = new TextField();
    TextField removeTf = new TextField();
    
        
    Button create = new Button ("Create Order Details");
    create.setOnAction((ActionEvent event) ->{
    	
		String orderId = orderDetailsNoTf.getText();
		int id = Integer.parseInt(orderId);
		String product = nameTf.getText();
		String totalCost = totalTf.getText();
		String quantity = quantTf.getText();
		int newQuant = Integer.parseInt(quantity);
		double totalC = Double.parseDouble(totalCost);
		od = new OrderDetails(id, product, newQuant, totalC);
				    
    try {
		Controller.createOrderDetailsButton(od);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    });
    
    Button update = new Button("Update Order Details");
    update.setOnAction((ActionEvent event) ->{

		String orderId = orderDetailsNoTf.getText();
		int id = Integer.parseInt(orderId);
		String product = nameTf.getText();
		String totalCost = totalTf.getText();
		String quantity = quantTf.getText();
		int newQuant = Integer.parseInt(quantity);
		double totalC = Double.parseDouble(totalCost);
		od = new OrderDetails(id, product, newQuant, totalC);
				    
    try {
		Controller.createOrderDetailsButton(od);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    });
    
    Button delete = new Button("Remove Order Details");
    delete.setOnAction((ActionEvent event) ->{
    	String orderDetailsID = removeTf.getText();
    	int orderDetailsNo = Integer.parseInt(orderDetailsID);
    	try {
			Controller.removeOrderDetailsButton(orderDetailsNo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    });
  /*  Button list = new Button("Show Order Details");
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
	gp.setPadding(new Insets(0, 15, 0, 15));
	
	HBox hb = new HBox(5);
	hb.getChildren().addAll(quit);
	HBox removeHB = new HBox(5);
	hb.setAlignment(Pos.CENTER);
	removeHB.getChildren().addAll( remove, removeTf, delete);
	HBox opt = new HBox();
	opt.getChildren().addAll(create, update);

    GridPane.setConstraints(lb, 0, 0); 	
    GridPane.setConstraints(name, 0, 1); 	
    GridPane.setConstraints(quant, 0, 2); 
	GridPane.setConstraints(total, 0, 3);	
    GridPane.setConstraints(orderDetailsNoTf, 1, 0); 	
    GridPane.setConstraints(nameTf, 1, 1); 	
    GridPane.setConstraints(quantTf, 1, 2); 
	GridPane.setConstraints(totalTf, 1, 3);	
//	GridPane.setConstraints(create, 0, 4);
	GridPane.setConstraints(opt, 1, 4);
	GridPane.setConstraints(removeHB, 0, 5);
	gp.getChildren().addAll(lb,name,quant,total,orderDetailsNoTf, nameTf, quantTf, totalTf , opt ,removeHB);
	HBox titleHB = new HBox();
	titleHB.getChildren().add(title);
	titleHB.setAlignment(Pos.CENTER);
	
	bp.setTop(titleHB);
	bp.setCenter(gp);
	bp.setBottom(hb);
	
	setContent(bp);
	
}

}