package education.patterns.observer.java.util.observer;

import education.patterns.observer.DisplayElem;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, DisplayElem {
    private float currentPressure = 29.0f;
    private float lastPressure;

    public ForecastDisplay(Observable observable) {
        observable.addObserver(this);
    }
    @Override
    public void display() {
        System.out.println("Current pressure: " + currentPressure + "F and last pressure " + lastPressure + "F");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            lastPressure = currentPressure;
            currentPressure = weatherData.getPressure();
            display();
        }
    }
}
