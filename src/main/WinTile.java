package main;

import java.awt.Graphics;

public class WinTile implements GameObject {

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return "Win by being on top of this tile.";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "WinTile";
	}

}
