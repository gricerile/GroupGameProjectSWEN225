import java.awt.Color;
import java.awt.Graphics;

import GUI.GUI;

public class Main extends GUI {
	public static void main(String[] args) {
		Main main = new Main();
	}

	@Override
	protected void redraw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.drawRect(0, 0, 100, 100);
		
	}
}
