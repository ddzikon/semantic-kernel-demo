package org.example.viewmodel;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.agent.Agent;
import org.example.model.repositories.ChatEntryRepository;
import org.example.model.repositories.PersonRepository;

public class ViewModelModule extends AbstractModule {

    @Provides
    @Singleton
    public PersonViewModel personViewModel(PersonRepository personRepository) {
        return new PersonViewModel(personRepository);
    }

    @Provides
    @Singleton
    public ChatEntryViewModel chatEntryViewModel(ChatEntryRepository chatEntryRepository) {
        return new ChatEntryViewModel(chatEntryRepository);
    }
}
