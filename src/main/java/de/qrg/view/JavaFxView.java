package de.qrg.view;

import java.time.Duration;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class JavaFxView implements View {

	@FXML
	private ListView<String> resultList;

	@FXML
	private Label runningLabel;

	@FXML
	private Label startLabel;

	public void showIntro() {
	}

	public void newGame() {
		Platform.runLater(new Runnable() {

			public void run() {
				startLabel.setVisible(false);
				runningLabel.setVisible(true);
			}
		});
	}

	public void showResult(final Duration duration) {
		Platform.runLater(new Runnable() {

			public void run() {
				resultList.getItems().add(duration.toMillis() + " ms");
				resultList.scrollTo(resultList.getItems().size() - 1);

				startLabel.setVisible(true);
				runningLabel.setVisible(false);
			}
		});

	}

}
