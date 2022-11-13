package de.fhe.pmeplayground.storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import de.fhe.pmeplayground.model.Beer;

@Dao
public interface BeerDao {

    @Insert
    void insert(Beer... beers);

    @Query("SELECT * FROM Beer")
    List<Beer> getBeers();
}
