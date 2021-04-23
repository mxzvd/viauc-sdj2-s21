package common.utility.observer.javaobserver;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject
{
  void addListener(String propertyName, PropertyChangeListener listener);
  void removeListener(String propertyName, PropertyChangeListener listener);
  void addListener(PropertyChangeListener listener);
  void removeListener(PropertyChangeListener listener);
}