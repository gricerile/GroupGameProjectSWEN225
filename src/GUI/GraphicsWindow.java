package GUI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

public class GraphicsWindow extends JPanel {

	private int dimensionHeight =200;
	private int dimensionWidth =200;

	public GraphicsWindow() {

		//set dimensions
			Dimension dimension = getPreferredSize();
			dimension.width = dimensionWidth;
			dimension.height=dimensionHeight;
			setPreferredSize(dimension);

			//set border
			setBorder(BorderFactory.createEtchedBorder());
	}

	public void paintComponent(Graphics g) {
	//	redraw(g);
	}


}
