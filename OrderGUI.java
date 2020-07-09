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
import model.Order;

public class OrderGUI extends Tab {
Order order;
public OrderGUI() throws ClassNotFoundException
{
	Class.forName("org.apache.derby.jdbc.EmbeddedDriver");	

	setText("Orders");
	BorderPane bp = new BorderPane();

	Label title = new Label("Orders");
	title.setFont(new Font("Impact", 40));
	title.setTextFill(Color.BLACK);
	title.setAlignment(Pos.TOP_CENTER);
    
    Label lb = new Label("Order No:");		
	Label prod = new Label("Product:");	
	Label price = new Label("Price:");	
	Label remove = new Label("Order ID:");
	
    TextField orderNoTf = new TextField();		
    TextField prodTf = new TextField();
    TextField priceTf = new TextField();
    TextField removeTf = new TextField();
        
    Button create = new Button ("Create Order");
    create.setOnAction((ActionEvent event) ->{
    	
		String orderId = orderNoTf.getText();
		int id = Integer.parseInt(orderId);
		String product = prodTf.getText();
		String orderCost = priceTf.getText();
		double cost = Double.parseDouble(orderCost);
		order = new Order(id, product, cost);
				    
    try {
		Controller.createOrderButton(order);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    });
    
    Button update = new Button("Update Orders");
    update.setOnAction((ActionEvent event) ->{

		String orderId = orderNoTf.getText();
		int id = Integer.parseInt(orderId);
		String product = prodTf.getText();
		String orderCost = priceTf.getText();
		double cost = Double.parseDouble(orderCost);
		order = new Order(id, product, cost);

		
    try {
		Controller.updateOrderButton(order);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    });
    
    Button delete = new Button("Remove Order");
    delete.setOnAction((ActionEvent event) ->{
    	String orderID = removeTf.getText();
    	int orderNo = Integer.parseInt(orderID);
    	try {
			Controller.removeOrderButton(orderNo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    });
 /*   Button list = new Button("Show Orders");
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
	vb.getChildren().addAll(lb ,prod, price);
	VBox vb1 = new VBox(10);
	vb1.getChildren().addAll(orderNoTf, prodTf, priceTf);
	HBox hb = new HBox(5);
	hb.getChildren().addAll(quit);
	HBox removeHB = new HBox(5);
	hb.setAlignment(Pos.CENTER);
	removeHB.getChildren().addAll( remove, removeTf, delete);
	HBox opt = new HBox();
	opt.getChildren().addAll(create, update);

    GridPane.setConstraints(lb, 0, 0); 	
    GridPane.setConstraints(prod, 0, 1); 	
    GridPane.setConstraints(price, 0, 2); 
    GridPane.setConstraints(orderNoTf, 1, 0); 	
    GridPane.setConstraints(prodTf, 1, 1); 	
    GridPane.setConstraints(priceTf, 1, 2); 
//	GridPane.setConstraints(create, 0, 3);
	GridPane.setConstraints(opt, 1, 3);
	GridPane.setConstraints(removeHB, 0, 5);
	gp.getChildren().addAll(lb,prod, price, orderNoTf, prodTf, priceTf, opt,removeHB);
	HBox titleHB = new HBox();
	titleHB.getChildren().add(title);
	titleHB.setAlignment(Pos.CENTER);

	bp.setTop(titleHB);
	bp.setCenter(gp);
	bp.setBottom(hb);
	
	setContent(bp);
	
}

}