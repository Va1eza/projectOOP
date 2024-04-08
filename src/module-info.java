module TEST {
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
	exports gui;
}
