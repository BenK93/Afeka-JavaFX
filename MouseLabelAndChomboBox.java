package HW3_BenKoren;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MouseLabelAndChomboBox extends OnAndOff{
	public Circle circle = new Circle(50,Color.RED);
	public BorderPane bPane = new BorderPane();
	public ComboBox<String> fillColors = new ComboBox<String>();
	public ComboBox<String> frameColors = new ComboBox<String>();
	public Text mouseLabel = new Text();
	private KeyFrame frame1 = new KeyFrame(new Duration(5000), moveCircle());
	private Timeline timeline = new Timeline(frame1);
	private ObservableList<String>	circleColorsTypes = FXCollections.observableArrayList
		      ("Red","Blue","Green","Yellow","Black");
	private Label circleColor = new Label("Circle Color:");
	private Label circleFrame = new Label("Circle Frame:");
	
	private StackPane fillStack = setSquareAndTitle(circleColor,fillColors);
	private StackPane frameStack = setSquareAndTitle(circleFrame,frameColors);
	
	private HBox bottomHbox = new HBox(fillStack,frameStack);
	
	/**
	 * adding the functionality of ComboBox to change Circle Color,
	 * and Setting it in the Bottom on the BorderPane
	 */
	public void setComboBoxs() {
		fillColors.getItems().addAll(circleColorsTypes);
		fillColors.getSelectionModel().selectFirst();
		fillColors.setOnAction(e -> {
			Color value = Color.valueOf(fillColors.getValue());
			circle.setFill(value);
		});
		
		frameColors.getItems().addAll(circleColorsTypes);
		frameColors.getSelectionModel().selectFirst();
		frameColors.setOnAction(e -> {
			Color value = Color.valueOf(frameColors.getValue());
			circle.setStroke(value);circle.setStrokeWidth(5);
		});
		bottomHbox.setAlignment(Pos.CENTER);
		bPane.setBottom(bottomHbox);

	}
	
	public EventHandler<ActionEvent> moveCircle() { // EventHandler changing the location of the Circle
		EventHandler<ActionEvent> event = e ->{
			if(circle.getCenterY() == 100) {
				circle.setCenterY(300);
			}else {
				circle.setCenterY(100);
			}
			changeText();
		};
		return event;
	}
	private void changeText() { // changing text is circle is moving on the mouse
		if(circle.contains(mouseLabel.getTranslateX(), mouseLabel.getTranslateY())) {
			mouseLabel.setText("Mouse point is in the Circle ! Yayyy");
			mouseLabel.setStyle("-fx-font-size: 15px;");
		}else {
			mouseLabel.setText("Mouse is NOT in the Circle");
			mouseLabel.setStyle("-fx-font-size: 12px;");
		}
	}
	public void openCircleScene(Scene circleScene, Stage secStage, Pane pane, Circle circle) { // opening and closing Circle window 
		if(secStage.isShowing()) {
			secStage.close();
		}else {
			secStage.setScene(circleScene);
			secStage.setTitle("Circle Window");
			secStage.setAlwaysOnTop(true);
			secStage.setOnCloseRequest(e -> e.consume());
			secStage.show();
		}
		setCirlceFrame(circle);
	}
	public void setCirlceFrame(Circle circle) { // Timeline moving circle up and down
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		
	}
	/**
	 * setting the ComboBox and return it inside a StackPane;
	 */
	public StackPane setSquareAndTitle(Label title ,ComboBox<String> comboBox) { 
		title.setStyle("-fx-font-weight: bold;  -fx-translate-y: -18;"
				+ "-fx-background-color: -fx-background");
		StackPane.setAlignment(title, Pos.TOP_CENTER);
		StackPane comboPane = new StackPane();
		comboPane.getChildren().addAll(title,comboBox);
		comboPane.setStyle("-fx-padding: 10 7 7 7 7;"
				+ "-fx-content-display: top;"
				+ "-fx-border-insets: 20 10 10 10;"
				+ "-fx-border-color: -fx-text-box-border;"
				+ "-fx-border-width: 1;");
		return comboPane;
	}
	/**
	 * setting mouse label when is inside and outside of the circle
	 */
	public void setMouseLabel(boolean inCircle, MouseEvent event) {
		if(inCircle) {
			mouseLabel.setText("Mouse point is in the Circle ! Yayyy");
			mouseLabel.setStyle("-fx-font-size: 15px;");
			mouseLabel.setTranslateY(event.getY());
			mouseLabel.setTranslateX(event.getX());
		}else {
		double xLocation = event.getX();
		if(bPane.getWidth() < xLocation + mouseLabel.getStrokeWidth()) {
			xLocation = bPane.getWidth() - mouseLabel.getStrokeWidth();
		}
			mouseLabel.setText("Mouse is NOT in the Circle");
			mouseLabel.setStyle("-fx-font-size: 12px;");
			mouseLabel.setTranslateY(event.getY());
			mouseLabel.setTranslateX(xLocation);
		}
	}
	// Getters & Setters
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public BorderPane getPane() {
		return bPane;
	}public Text getMouseText() {
		return mouseLabel;
	}
	public void setMouseLabel(Text mouseLabel) {
		this.mouseLabel = mouseLabel;
	}
	public ObservableList<String> getCircleColors() {
		return circleColorsTypes;
	}
	public void setCircleColors(ObservableList<String> circleColors) {
		this.circleColorsTypes = circleColors;
	}
	public Label getCircleColor() {
		return circleColor;
	}
	public void setCircleColor(Label circleColor) {
		this.circleColor = circleColor;
	}
	public Label getCircleFrame() {
		return circleFrame;
	}
	public void setCircleFrame(Label circleFrame) {
		this.circleFrame = circleFrame;
	}
	public ComboBox<String> getFillColors() {
		return fillColors;
	}
	public void setFillColors(ComboBox<String> fillColors) {
		this.fillColors = fillColors;
	}
	public StackPane getFillStack() {
		return fillStack;
	}
	public void setFillStack(StackPane fillStack) {
		this.fillStack = fillStack;
	}
	public StackPane getFrameStack() {
		return frameStack;
	}
	public void setFrameStack(StackPane frameStack) {
		this.frameStack = frameStack;
	}
	
}
