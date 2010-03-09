package macrobug.gui;

import java.util.EventListener;

import macrobug.gui.EdiTable.ChangeCellEvent;

public interface ChangeCellListener extends EventListener {
	public void cellEmptied(ChangeCellEvent e);
	public void cellFulled(ChangeCellEvent e);
	public void rowEmptied(ChangeCellEvent e);
	public void rowFulled(ChangeCellEvent e);
}
