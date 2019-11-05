package a6;

import java.util.Iterator;

// Collaborated w Marcus H.

public class ProximityIterator implements Iterator<Driver> {

    private Iterator<Driver> driver_pool;
    private Position client_position;
    private int proximity_limit;
    
    private Driver nextDriver;


    public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
        this.driver_pool = driver_pool.iterator();
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

        while (driver_pool.hasNext()) {

            nextDriver = driver_pool.next();

            if (proximity_limit >= client_position.getManhattanDistanceTo(nextDriver.getVehicle().getPosition())) {
                return true;
            }

        }
        return false;

    }

    @Override
    public Driver next() {
        return null;
    }


}
