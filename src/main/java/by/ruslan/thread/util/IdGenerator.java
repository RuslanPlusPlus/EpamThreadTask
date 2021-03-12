package by.ruslan.thread.util;

import by.ruslan.thread.entity.EntityType;

public class IdGenerator {
    private static int SHIP_ID_COUNTER = 1;
    private static int BERTH_ID_COUNTER = 1;

    public static int generateId(EntityType type){
        int id = 0;
        switch (type){
            case BERTH:{
                id = BERTH_ID_COUNTER++;
                break;
            }
            case PORT:{
                id = SHIP_ID_COUNTER++;
                break;
            }
        }
        return id;
    }
}
