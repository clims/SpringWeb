package spring.hiber.dao;

import spring.hiber.model.Cosmonaut;

import java.util.List;

public interface CosmonautDAO {
    int addCosmonaut(Cosmonaut cosmonaut);
    Cosmonaut getCosmonautById(int id);
    List<Cosmonaut> getAllCosmonauts();
    List<Cosmonaut> getCosmonautsByName(String name);
    void updateCosmonaut(Cosmonaut cosmonaut);
    void removeCosmonaut(Cosmonaut cosmonaut);
    long getNumberOfCosmonauts();
    Cosmonaut getOldestCosmonaut();
}
