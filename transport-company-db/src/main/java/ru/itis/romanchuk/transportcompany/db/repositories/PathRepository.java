package ru.itis.romanchuk.transportcompany.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.romanchuk.transportcompany.db.models.Path;

public interface PathRepository extends JpaRepository<Path,Long> {

}
