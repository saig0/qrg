package de.qrg.view;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class JavaFxView extends Application implements View, Initializable {

	@FXML
	private ListView<String> resultList;

	public void showIntro() {
		launch();
	}

	public void newGame() {
		// TODO Auto-generated method stub

	}

	public void showResult(Duration duration) {
		System.out.println("? " + resultList);
		resultList.getItems().add(duration.toMillis() + "ms");
	}

	@Override
	public void start(Stage stage) throws Exception {
		URL resource = getClass().getResource("/main-view.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setController(this);
		fxmlLoader.setLocation(resource);
		Parent root = fxmlLoader.load();

		Scene scene = new Scene(root, 800, 600);

		stage.setTitle("Quick Reaction Game");
		stage.setScene(scene);
		stage.show();
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("init");

		List<String> list = new ArrayList<String>();
		list.add("test");
		resultList.setItems(FXCollections.observableList(list));
	}

}
