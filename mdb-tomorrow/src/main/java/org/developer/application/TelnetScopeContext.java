package org.developer.application;

import java.lang.annotation.Annotation;

import javax.annotation.Resource;
import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;

import com.superconnectors.telnet.adapter.TelnetResourceAdapter;

/**
 *
 */
public class TelnetScopeContext implements Context {
    @Resource(type = javax.resource.spi.ResourceAdapter.class, name = "TelnetResourceAdapter")
    private TelnetResourceAdapter resourceAdapter;

    @Override
    public Class<? extends Annotation> getScope() {
        return TelnetScope.class;
    }

    @Override
    public <T> T get(Contextual<T> component, CreationalContext<T> creationalContext) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> T get(Contextual<T> component) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isActive() {
        return false;
//        return resourceAdapter.hasActiveConnections();
    }
}
