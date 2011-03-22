/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.domdrides.component.link;

import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.IModel;
import org.domdrides.entity.Entity;
import org.domdrides.repository.Repository;

import java.io.Serializable;

/**
 * @since 1.0
 */
public abstract class UpdateEntityLink<EntityType extends Entity<IdType>, IdType extends Serializable> extends SubmitLink
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final Repository<EntityType, IdType> repository;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected UpdateEntityLink(String id, Repository<EntityType, IdType> repository, IModel<EntityType> model)
    {
        super(id);
        setDefaultModel(model);
        this.repository = repository;
    }

//----------------------------------------------------------------------------------------------------------------------
// Abstract Methods
//----------------------------------------------------------------------------------------------------------------------

    protected abstract void afterUpdate(EntityType entity);

//----------------------------------------------------------------------------------------------------------------------
// IFormSubmittingComponent Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    @SuppressWarnings("unchecked")
    public void onSubmit()
    {
        EntityType entity = (EntityType) getDefaultModelObject();
        entity = repository.update(entity);
        setDefaultModelObject(entity);
        afterUpdate(entity);
    }
}