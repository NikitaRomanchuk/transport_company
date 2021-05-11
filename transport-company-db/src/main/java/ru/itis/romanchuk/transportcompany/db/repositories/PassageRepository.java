package ru.itis.romanchuk.transportcompany.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.romanchuk.transportcompany.db.models.City;
import ru.itis.romanchuk.transportcompany.db.models.Passage;

import java.util.List;

public interface PassageRepository extends JpaRepository<Passage, Long> {

    List<Passage> findByFromAndTo(City from, City to);

}
