package org.example.view;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.viewmodel.AgentInteractor;
import org.example.viewmodel.ChatEntryViewModel;
import org.example.viewmodel.PersonViewModel;

public class ViewModule extends AbstractModule {

    @Provides
    @Singleton
    public ViewController viewController(PersonViewModel personpersonViewModel, ChatEntryViewModel chatEntryViewModel, AgentInteractor agentInteractor) {
        return new ViewController(personpersonViewModel, chatEntryViewModel, agentInteractor);
    }
}
