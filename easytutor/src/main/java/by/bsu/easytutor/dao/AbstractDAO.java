package by.bsu.easytutor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AbstractDAO <T extends Serializable> {
    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public void setClazz(Class< T > clazzToSet) {
        clazz = clazzToSet;
    }

    public Optional<T>  get(long id) {
        return Optional.of(getCurrentSession().get( clazz, id ));
    }

    public List< T > getAll() {
        return getCurrentSession()
                .createQuery( "from " + clazz.getName() ).list();
    }

    public void save(T entity) {
        getCurrentSession().persist( entity );
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge( entity );
    }

    public void delete(T entity) {
        getCurrentSession().delete( entity );
    }

    public void deleteById(long id) {
        final T entity = get(id).orElseThrow(() -> new NoSuchElementException(""));
        delete( entity );
    }

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
