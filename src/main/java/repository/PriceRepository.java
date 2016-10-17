package repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;

import model.Price;
import model.User;

@SuppressWarnings("serial")
public class PriceRepository extends HibernateGenericDAO<Price> implements GenericRepository<Price> {

    //private static final long serialVersionUID = -4036535812105672110L;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<User> filterPeople(final String pattern) {
        return (List<User>) this.getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public List<User> doInHibernate(final Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.like("name", "%" + pattern + "%"));
                return criteria.list();
            }

        });
    }

    @Override
    protected Class<Price> getDomainClass() {
        return Price.class;
    }
}