package lemonsoju_group.lemonsoju_artifact.repository;

import lemonsoju_group.lemonsoju_artifact.domain.Basket;
import lemonsoju_group.lemonsoju_artifact.domain.Item;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BasketDataRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findOneByUserAndItem(User user, Item item);

    List<Basket> findAllByUser(User user);

    Optional<Basket> findById(Long basketId);
}
