package PackageBozze;

import java.beans.*;

public class pclprova1 { //observable
	
	    private boolean occupato;
	    private PropertyChangeSupport support;

	    public pclprova1() {
	        support = new PropertyChangeSupport(this);
	    }

	    public void addPropertyChangeListener(PropertyChangeListener pcl) {
	        support.addPropertyChangeListener(pcl);
	    }

	    public void removePropertyChangeListener(PropertyChangeListener pcl) {
	        support.removePropertyChangeListener(pcl);
	    }

	    public void setCheckOccupato(boolean value) {
	        support.firePropertyChange("occupato", this.occupato, value);
	        this.occupato = value;
	    }
}
