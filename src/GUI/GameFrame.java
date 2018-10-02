package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	NavigationPanel navigationPanel;
	GraphicsWindow graphicsWindow;

	public GameFrame(String s) {
		super(s);

		// set layout
		setLayout(new BorderLayout());

		// components
		navigationPanel = new NavigationPanel();
		graphicsWindow = new GraphicsWindow();

		// add components to content pane
		Container c = getContentPane();

		c.add(navigationPanel, BorderLayout.NORTH);
		c.add(graphicsWindow, BorderLayout.CENTER);

		// behaviour

	}

}
