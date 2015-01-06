package de.qrg;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import de.qrg.controller.Controller;
import de.qrg.view.CommandlineView;
import de.qrg.view.JavaFxView;
import de.qrg.view.View;

public class Main extends Application {

	public static void main(String[] args) {
		if (args.length == 1 && "-x".equals(args[0])) {
			launch(args);
		} else {
			Controller.create(new CommandlineView());
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		URL resource = getClass().getResource("/main-view.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader();
		View view = new JavaFxView();
		fxmlLoader.setController(view);
		fxmlLoader.setLocation(resource);
		Parent root = fxmlLoader.load();

		Scene scene = new Scene(root);

		stage.setTitle("Quick Reaction Game");
		stage.setScene(scene);
		stage.show();

		Controller.create(view);
	}

}
