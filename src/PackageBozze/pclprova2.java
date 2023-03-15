package PackageBozze;

import java.beans.*;

public class pclprova2 implements PropertyChangeListener { //observer

	    private boolean occupato;

	    public void propertyChange(PropertyChangeEvent evt) {
	        this.setOccupato((boolean) evt.getNewValue());
	    }

		public void setOccupato(boolean cambioOccupato) {
			this.occupato = cambioOccupato;
		}

		public boolean getOccupato() {
			return this.occupato;
		}
}

/* Main
 * 
		 *  PCLNewsAgency observable = new PCLNewsAgency();
			PCLNewsChannel observer = new PCLNewsChannel();
			
			observable.addPropertyChangeListener(observer);
			observable.setNews("news");
			
			assertEquals(observer.getNews(), "news");
 * 
 * 
 * 
 * */
