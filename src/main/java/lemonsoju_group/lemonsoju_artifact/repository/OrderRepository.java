package lemonsoju_group.lemonsoju_artifact.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lemonsoju_group.lemonsoju_artifact.domain.Order;
import lemonsoju_group.lemonsoju_artifact.domain.OrderStatus;
import lemonsoju_group.lemonsoju_artifact.domain.QOrder;
import lemonsoju_group.lemonsoju_artifact.domain.QUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static lemonsoju_group.lemonsoju_artifact.domain.QOrder.*;
import static lemonsoju_group.lemonsoju_artifact.domain.QUser.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order)  {
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        return queryFactory
                .selectFrom(order)
                .join(order.user, user)
                .where(
                        orderStatusEq(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getUserName()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression orderStatusEq(OrderStatus orderStatusCond){
        return orderStatusCond == null ? null : order.status.eq(orderStatusCond);
    }

    private BooleanExpression nameLike(String nameCond){
        return !StringUtils.hasText(nameCond) ? null : user.name.like(nameCond);
    }
}
