package by.ruslan.thread.entity;

import java.util.LinkedList;
import java.util.Queue;

public class Berth {
    private Queue<Ship> waitingShips = new LinkedList<>();
    private boolean isBusy = false;


}