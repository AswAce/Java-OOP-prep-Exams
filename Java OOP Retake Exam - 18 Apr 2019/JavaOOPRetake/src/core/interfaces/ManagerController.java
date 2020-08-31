package core.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface ManagerController {
    String addPlayer(String type, String username) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    String addCard(String type, String name) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    String addPlayerCard(String username,String cardName);
    String fight(String attackUser, String enemyUser);
    String report();
}
