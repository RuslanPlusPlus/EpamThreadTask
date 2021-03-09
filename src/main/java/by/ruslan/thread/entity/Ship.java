package by.ruslan.thread.entity;

public class Ship implements Runnable{

    private Port port;
    private Long id;

    public Ship(Port port){
        this.port = port;
    }

    @Override
    public void run() {

    }
}
