package by.ruslan.thread.entity;

import by.ruslan.thread.exception.ThreadException;

public class Berth {
    private Long id;

    public Berth(long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void loadContainers(Ship ship) throws ThreadException {
        ContainerStorage storage = ContainerStorage.getInstance();
        storage.loadContainers(ship);
    }

    public void unloadContainers(Ship ship) throws ThreadException {
        ContainerStorage storage = ContainerStorage.getInstance();
        storage.unloadContainers(ship);
    }


}
