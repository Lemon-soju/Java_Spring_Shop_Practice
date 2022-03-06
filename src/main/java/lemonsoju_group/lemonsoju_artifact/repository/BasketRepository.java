package lemonsoju_group.lemonsoju_artifact.repository;

import lemonsoju_group.lemonsoju_artifact.domain.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BasketRepository {

    private final EntityManager em;

    public void save(Basket basket) {
        em.persist(basket);
    }

    public List<Basket> findAll() {
        return em.createQuery("select b from Basket b", Basket.class).getResultList();
    }

    public Basket findOne(Long id) {
        return em.find(Basket.class, id);
    }

    public void delete(Basket basket){
        em.remove(basket);
    }
}

