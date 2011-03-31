package org.wicketopia.builder.feature;

import org.wicketopia.builder.ComponentBuilder;
import org.wicketopia.context.Context;

import java.io.Serializable;

/**
 * @author James Carman
 */
public interface ComponentBuilderFeature<B extends ComponentBuilder> extends Serializable
{
//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------
    public boolean isActiveFor(Context context);
    public void activate(B builder);
}
