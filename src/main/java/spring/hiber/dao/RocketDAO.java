package spring.hiber.dao;

import spring.hiber.model.Rocket;

import java.util.List;

public interface RocketDAO {
    int addRocket(Rocket rocket);
    List<Rocket> getAllRockets();
    Rocket getRocketById(int id);
    List<Rocket> getRocketByName(String name);
    List<Rocket> getPriceGreather(Double price);
    void updateRocket(Rocket rocket);
    void deleteRocket(Rocket rocket);
    long getNumberOfRockets();
}
