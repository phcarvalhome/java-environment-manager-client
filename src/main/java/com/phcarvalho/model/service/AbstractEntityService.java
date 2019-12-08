package com.phcarvalho.model.service;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.entity.Entity;
import com.phcarvalho.model.service.delegate.JavaSpaceDelegate;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityService<E extends Entity> {

    private JavaSpaceDelegate javaSpaceDelegate;

    public AbstractEntityService() {
        javaSpaceDelegate = DependencyFactory.getSingleton().get(JavaSpaceDelegate.class);
    }

    public List<E> findAll(E entity){
        ArrayList<E> entityList = new ArrayList<>();
        E foundElement;

        do {
            foundElement = delete(entity);

            if(foundElement != null)
                entityList.add(foundElement);

        } while (foundElement != null);

        entityList.forEach(entityElement -> put(entityElement));

        return entityList;
    }

    public E find(E entity){

        try {
            return (E) javaSpaceDelegate.readIfExists(entity, null, 0);
        } catch (UnusableEntryException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public E update(E oldEntity, E newEntity){
        E deletedEntity = delete(oldEntity);

        if(deletedEntity != null)
            put(newEntity);

        return deletedEntity;
    }

    public void put(E entity){

        try {
            javaSpaceDelegate.write(entity, null, Lease.FOREVER);
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public E delete(E entity){

        try {
            return (E) javaSpaceDelegate.takeIfExists(entity, null, 0);
        } catch (UnusableEntryException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }
}
