package org.example.gui;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.SneakyThrows;
import org.example.agent.Agent;
import org.example.db.Repository;

public class GuiModule extends AbstractModule {

    @Override
    protected void configure() {}

    @Provides
    @Singleton
    public Drafter provideDrafter(Repository repository, Agent agent) {
        return new Drafter(repository, agent);
    }
}
