package ru.itis.romanchuk.transportcompany.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.romanchuk.transportcompany.db.models.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query(nativeQuery = true, value = "WITH RECURSIVE paths AS(\n" +
            "    SELECT from_id, to_id, (from_id || ',' || to_id) as id_list, 0 as cost, 1 as step FROM path " +
            "    WHERE from_id = ?1\n" +
            "    UNION ALL\n" +
            "    SELECT paths.from_id, path.to_id, paths.id_list ||','||path.to_id, cost + path.distance, step + 1\n" +
            "    FROM path INNER JOIN paths ON path.from_id = paths.to_id\n" +
            "    WHERE step < (SELECT count(*) FROM city)\n" +
            ")\n" +
            "SELECT id_list FROM paths WHERE to_id = ?2 ORDER BY cost;")
    List<String> optimalPath(Long cityIdFrom, Long cityIdTo);
}
