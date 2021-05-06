package utility;

import java.beans.PropertyChangeListener;

// Generic unnamed subject interface.
public interface UnnamedPropertyChangeSubject {
   void addListener(PropertyChangeListener listener);
   void removeListener(PropertyChangeListener listener);
}
