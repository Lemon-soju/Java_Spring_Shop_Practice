package lemonsoju_group.lemonsoju_artifact.repository;

import lemonsoju_group.lemonsoju_artifact.domain.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BasketRepository {

    private final EntityManager em;

    public void save(Basket basket) {
    if (basket.getId() == null) {
        em.persist(basket);
    } else {
        em.merge(basket);
    }
}

    public List<Basket> findAll() {
        return em.createQuery("select b from Basket b", Basket.class).getResultList();
    }

    public Basket findOne(Long id) {
        return em.find(Basket.class, id);
    }

    public Optional<Basket> findOneByItem(Long id) {
        return em.createQuery("select b from Basket b where b.item.id = :itemId", Basket.class)
                .setParameter("itemId", id)
                .getResultList()
                .stream().findFirst();
    }

    public void delete(Basket basket){
        em.remove(basket);
    }
}

