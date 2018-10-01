package GUI;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private NavigationPanel navigationPanel;
	private GraphicsWindow graphicsWindow;

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
