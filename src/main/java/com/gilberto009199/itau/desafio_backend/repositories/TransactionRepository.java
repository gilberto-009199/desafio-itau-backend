package com.gilberto009199.itau.desafio_backend.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;

public class TransactionRepository{
    
    private final List<TransactionRequest> db = new ArrayList<>();
    private final Semaphore db_lock = new Semaphore(1);

    public synchronized TransactionRequest save(final TransactionRequest entity){
        
        TransactionRequest save;

        try{
           
            db_lock.acquire(); // Se estiver ocupado, a thread fica esperando
           
            
            db.add(entity);
            
            save = entity;

        } catch (Exception e) {
            // @todo return specific expection
            return null;

        } finally { db_lock.release();}

        return save;
    }

    public synchronized void deleteAll() {
        try{
           
            db_lock.acquire(); // Se estiver ocupado, a thread fica esperando
           
            db.clear();
            
        } catch (Exception e) {
            // @todo return specific expection
        } finally { db_lock.release();}
    }
}
