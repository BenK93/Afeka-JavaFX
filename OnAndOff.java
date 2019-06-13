package HW3_BenKoren;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OnAndOff extends Application{
	public final Button startButton = new Button("Mouse Point ON/OFF");
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// ON OFF Button Pane
		onOffPane(primaryStage); 
		// second Window
		Stage secStage = new Stage();
		MouseLabelAndChomboBox secWindow = new MouseLabelAndChomboBox();
		secWindow.getCircle().setCenterX(100);
		secWindow.getCircle().setCenterY(100);
		secWindow.getPane().getChildren().add(secWindow.getCircle());
		Scene circleScene = new Scene(secWindow.getPane(),600,500);
		// start button setting 
		startButton.setOnAction(e -> secWindow.openCircleScene(circleScene,secStage, secWindow.getPane(),secWindow.getCircle()));
		primaryStage.setOnCloseRequest(e -> {
			secStage.close();
		});
		// adding label to mouse + event 
		secWindow.getPane().setTop(secWindow.getMouseText());
		secWindow.getPane().setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override 
			public void handle (MouseEvent event) {
				if(secWindow.getCircle().contains(event.getX(), event.getY())) 
					secWindow.setMouseLabel(true, event);
				else 
					secWindow.setMouseLabel(false, event);
			}	
		});
		
		// setting on the bottom the combo box
		secWindow.setComboBoxs();
	}
	public void onOffPane(Stage stage) { // opening the ON/OFF button Pane
		StackPane rootPane = new StackPane();
		rootPane.getChildren().add(startButton);
	    Scene scene = new Scene(rootPane, 250,75);
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.setTitle("Control Pane");
		stage.show();
	}
}
