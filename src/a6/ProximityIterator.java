package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Collaborated w Marcus H. and 401 LAs

public class ProximityIterator implements Iterator<Driver> {

    private Iterator<Driver> iterator;
    private Position client_position;
    private int proximity_limit;
    
    private Driver nextDriver;


    public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
        this.iterator = driver_pool.iterator();
        this.client_position = client_position;
        this.proximity_limit = proximity_limit;
        this.nextDriver = null;

        if(driver_pool == null) {
            throw new IllegalArgumentException("The 'driver pool' element is empty");
        }

        if(client_position == null) {
            throw new IllegalArgumentException("The 'client position' element is empty");
        }

        if(proximity_limit < 0) {
            throw new IllegalArgumentException("Proximity limit cannot be a negative number");
        }

    }

    @Override
    public boolean hasNext() {

        if (!(nextDriver == null)) {
            return true;
        }

        while (iterator.hasNext()) {

            nextDriver = iterator.next();

            if (proximity_limit >= client_position.getManhattanDistanceTo(nextDriver.getVehicle().getPosition())) {
                return true;
            }

        }
        nextDriver = null;
        return false;

    }

    @Override
    public Driver next() {

        if(!(hasNext())) {
            throw new NoSuchElementException("No more drivers were found");
        }

        Driver driver = nextDriver;
        nextDriver = null;
        return driver;
    }


}
