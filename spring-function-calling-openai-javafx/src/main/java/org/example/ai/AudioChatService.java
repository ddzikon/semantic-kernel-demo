package org.example.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.viewmodel.AudioChatEntryViewModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AudioChatService {

    private static final String SPRING_AI_OPENAI_API_KEY;

    static {
        SPRING_AI_OPENAI_API_KEY = System.getenv("SPRING_AI_OPENAI_API_KEY");

        if (SPRING_AI_OPENAI_API_KEY == null || SPRING_AI_OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("missing env var SPRING_AI_OPENAI_API_KEY");
        }
    }

    private final ChatService chatService;
    private final AudioChatEntryViewModel audioChatEntryViewModel;

    public String askGpt(Resource audioFile) {
        final var openAiAudioApi = new OpenAiAudioApi(SPRING_AI_OPENAI_API_KEY);

        final var openAiAudioTranscriptModel = new OpenAiAudioTranscriptionModel(openAiAudioApi);

        final var transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .build();

        log.info("Requesting audio transcription...");
        final var audioTranscriptionPrompt = new AudioTranscriptionPrompt(audioFile, transcriptionOptions);
        final var response = openAiAudioTranscriptModel.call(audioTranscriptionPrompt).getResult().getOutput();

        log.info("Received transcription:\n{}", response);
        audioChatEntryViewModel.storeAudioChatEntry(response);

        return chatService.askGpt(response);
    }
}
