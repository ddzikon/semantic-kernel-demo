package org.example.gui;

import com.google.inject.AbstractModule;
import lombok.SneakyThrows;
import org.example.agent.Agent;
import org.example.db.Repository;

public class GuiModule extends AbstractModule {

    @SneakyThrows
    @Override
    protected void configure() {
        bind(Drafter.class).toConstructor(Drafter.class.getDeclaredConstructor(Repository.class, Agent.class));
    }
}
