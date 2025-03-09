package com.gilberto009199.itau.desafio_backend.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Repository;

import com.gilberto009199.itau.desafio_backend.entities.TransactionEntity;
import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;

@Repository
public class TransactionRepository implements HealthIndicator{
    
    public static final Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    private final List<TransactionEntity> db    = new ArrayList<>();
    
    // Control For exclusion and statistics
    private final Semaphore db_lock             = new Semaphore(1);

    public synchronized TransactionEntity save(final TransactionRequest request){

        logger.debug("stage=init method=save: request:{}", request);
        
        TransactionEntity save = TransactionEntity.of(request);

        try{
           
            db_lock.acquire(); // Se estiver ocupado, a thread fica esperando
           
            logger.debug("stage=debug method=save: save:{}", save);
            
            db.add(save);

        } catch (Exception e) {
        
            // @todo return specific Exception

            return null;

        } finally { db_lock.release();}

        logger.debug("stage=end method=save: db:{}", db);

        return save;
    }

    public synchronized void deleteAll() {

        logger.debug("stage=init method=deleteAll");

        try{
           
            db_lock.acquire(); // Se estiver ocupado, a thread fica esperando
           
            logger.debug("stage=debug method=deleteAll: db:{}", db);
            
            db.clear();

        } catch (Exception e) {
            // @todo return specific Exception
        } finally { db_lock.release();}

        logger.debug("stage=end method=deleteAll: db:{}", db);
    }

    public List<TransactionEntity> findAll() {
        logger.debug("stage=init method=findAll: db:{}", db);

        var list = new ArrayList<>(this.db);

        logger.debug("stage=end method=findAll: list:{}", list);

        return list;
    }

    @Override
    public Health health() {

        var status = (db != null)? Status.UP : Status.DOWN;

        return Health.status(status).build();
        
    }
}
