package com.phcarvalho.model.service.delegate;

import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.event.EventRegistration;
import net.jini.core.event.RemoteEventListener;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.MarshalledObject;
import java.rmi.RemoteException;

public class JavaSpaceDelegate {

    private JavaSpace javaSpace;

    public void locateService(){
        Lookup lookup = new Lookup(JavaSpace.class);

        javaSpace = (JavaSpace) lookup.getService();
    }

    public Lease write(Entry entry, Transaction transaction, long l) throws TransactionException, RemoteException {
        return javaSpace.write(entry, transaction, l);
    }

    public Entry read(Entry entry, Transaction transaction, long l) throws UnusableEntryException, TransactionException, InterruptedException, RemoteException {
        return javaSpace.read(entry, transaction, l);
    }

    public Entry readIfExists(Entry entry, Transaction transaction, long l) throws UnusableEntryException, TransactionException, InterruptedException, RemoteException {
        return javaSpace.readIfExists(entry, transaction, l);
    }

    public Entry take(Entry entry, Transaction transaction, long l) throws UnusableEntryException, TransactionException, InterruptedException, RemoteException {
        return javaSpace.take(entry, transaction, l);
    }

    public Entry takeIfExists(Entry entry, Transaction transaction, long l) throws UnusableEntryException, TransactionException, InterruptedException, RemoteException {
        return javaSpace.takeIfExists(entry, transaction, l);
    }

    public EventRegistration notify(Entry entry, Transaction transaction, RemoteEventListener remoteEventListener, long l, MarshalledObject marshalledObject) throws TransactionException, RemoteException {
        return javaSpace.notify(entry, transaction, remoteEventListener, l, marshalledObject);
    }

    public Entry snapshot(Entry entry) throws RemoteException {
        return javaSpace.snapshot(entry);
    }
}
