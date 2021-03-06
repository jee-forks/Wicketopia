/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.example.web.page.scaffold;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketopia.example.domain.entity.Person;
import org.wicketopia.example.web.page.BasePage;
import org.wicketopia.persistence.PersistenceProvider;
import org.wicketopia.persistence.component.scaffold.Scaffold;

public class ScaffoldExample extends BasePage
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @SpringBean
    private PersistenceProvider persistenceProvider;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public ScaffoldExample()
    {
        add(new Scaffold<Person>("scaffold", Person.class, persistenceProvider));
    }
}
