/*
 * Copyright (c) 2011 Carman Consulting, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketopia.editor.component.bean;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.metastopheles.BeanMetaData;
import org.metastopheles.PropertyMetaData;
import org.wicketopia.WicketopiaPlugin;
import org.wicketopia.component.label.PropertyLabel;
import org.wicketopia.editor.context.EditorContext;
import org.wicketopia.editor.factory.BeanEditorComponentFactory;
import org.wicketopia.metadata.WicketopiaFacet;

import java.util.*;

/**
 * @since 1.0
 */
public class AbstractBeanEditor<T> extends Panel
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    protected final EditorContext editorContext;
    private final BeanEditorComponentFactory<T> editorComponentFactory;
    private final Class<T> beanType;
    private final Set<String> properties;
    private final IModel<T> beanModel;

//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    protected AbstractBeanEditor(String id, Class<T> beanType, IModel<T> beanModel, EditorContext editorContext, String... properties)
    {
        super(id, beanModel);
        editorComponentFactory = new BeanEditorComponentFactory<T>(beanType);
        this.beanType = beanType;
        this.beanModel = beanModel;
        this.editorContext = editorContext;
        this.properties = new TreeSet<String>(Arrays.asList(properties));
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    protected Component createPropertyEditor(String componentId, String propertyName)
    {
        return editorComponentFactory.createPropertyEditor(componentId, beanModel, propertyName, editorContext);
    }

    protected Label createPropertyLabel(String componentId, String propertyName)
    {
        return editorComponentFactory.createPropertyLabel(componentId, propertyName);
    }

    protected IModel<List<String>> createPropertyNameListModel()
    {
        return new PropertyNameListModel();
    }

    protected List<String> getPropertyNameList()
    {
        List<PropertyMetaData> propertyMetaDataList = new LinkedList<PropertyMetaData>();
        final BeanMetaData beanMetaData = WicketopiaPlugin.get().getBeanMetaData(beanType);
        Set<String> propertyNames = properties.isEmpty() ? beanMetaData.getPropertyNames() : properties;
        for (String propertyName : propertyNames)
        {
            PropertyMetaData propertyMetaData = beanMetaData.getPropertyMetaData(propertyName);
            if (!WicketopiaFacet.get(propertyMetaData).isIgnored())
            {
                propertyMetaDataList.add(propertyMetaData);
            }
        }
        WicketopiaFacet.sort(propertyMetaDataList);
        final List<String> propertyNamesList = new ArrayList<String>(propertyMetaDataList.size());
        for (PropertyMetaData propertyMetaData : propertyMetaDataList)
        {
            propertyNamesList.add(propertyMetaData.getPropertyDescriptor().getName());
        }
        return propertyNamesList;
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private class PropertyNameListModel extends LoadableDetachableModel<List<String>>
    {
        @Override
        protected List<String> load()
        {
            return getPropertyNameList();
        }
    }
}
