package penpaint;

import javax.swing.event.*;
import javax.swing.*;

public class ListenForSlider implements ChangeListener {
	public void stateChanged(ChangeEvent e){
		if(e.getSource()==userint.transSlider){
			userint.transLabel.setText("tranparent : "+userint.dec.format(userint.transSlider.getValue()*.01));
			userint.transval=(float)(userint.transSlider.getValue()*.01);
			
		}
	}
}
