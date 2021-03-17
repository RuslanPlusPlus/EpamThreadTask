package by.ruslan.thread.parser;

import by.ruslan.thread.entity.Ship;
import by.ruslan.thread.exception.ThreadException;

import java.util.ArrayList;
import java.util.List;

public class ShipParser {

    static final String DATA_SPLITTER = " ";

    public List<Ship> parseLines(List<String> lines) throws ThreadException {
        if (lines == null || lines.isEmpty()){
            throw new ThreadException("Parsed null or list is empty");
        }
        List<Ship> ships = new ArrayList<>();
        for (String line: lines){
            String[] data = line.split(DATA_SPLITTER);
            int maxCapacity = Integer.parseInt(data[0]);
            int currentCapacity = Integer.parseInt(data[1]);
            Ship ship = new Ship(maxCapacity, currentCapacity);
            ships.add(ship);
        }
        return ships;
    }
}
