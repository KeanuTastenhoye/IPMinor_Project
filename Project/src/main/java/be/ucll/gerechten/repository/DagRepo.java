package be.ucll.gerechten.repository;

import be.ucll.gerechten.model.DagMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DagRepo extends JpaRepository<DagMenu, String> {
    DagMenu findDagMenuByDatum(String datum);
}
