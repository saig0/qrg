package de.qrg;

import de.qrg.controller.Controller;
import de.qrg.view.CommandlineView;

public class Main {

	public static void main(String[] args) {
		CommandlineView view = new CommandlineView();
		Controller controller = Controller.create(view);

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}

}
