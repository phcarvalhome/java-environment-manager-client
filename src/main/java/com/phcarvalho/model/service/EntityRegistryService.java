package com.phcarvalho.model.service;

import com.phcarvalho.model.entity.EntityRegistry;
import com.phcarvalho.model.vo.EntityType;

public class EntityRegistryService extends AbstractEntityService<EntityRegistry> {


    public Integer generateNextId(EntityType entityType){
        Integer nextId;
        EntityRegistry entityRegistry = find(new EntityRegistry(entityType.name(), null));

        if(entityRegistry == null)
            nextId = 1;
        else {
            nextId = entityRegistry.counter + 1;
            delete(entityRegistry);
        }

        put(new EntityRegistry(entityType.name(), nextId));

        return nextId;
    }
}
