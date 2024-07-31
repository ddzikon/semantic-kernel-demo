package org.example.ai;

import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class AudioChatService {

    private static final String SPRING_AI_OPENAI_API_KEY;

    static {
        SPRING_AI_OPENAI_API_KEY = System.getenv("SPRING_AI_OPENAI_API_KEY");

        if (SPRING_AI_OPENAI_API_KEY == null || SPRING_AI_OPENAI_API_KEY.isBlank()) {
            throw new IllegalStateException("missing env var SPRING_AI_OPENAI_API_KEY");
        }
    }

    @Autowired
    private ChatService chatService;

    public String askGpt(FileSystemResource audioFile) {
        final var openAiAudioApi = new OpenAiAudioApi(SPRING_AI_OPENAI_API_KEY);

        final var openAiAudioTranscriptModel = new OpenAiAudioTranscriptionModel(openAiAudioApi);

        final var transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .build();

        final var audioTranscriptionPrompt = new AudioTranscriptionPrompt(audioFile, transcriptionOptions);
        final var response = openAiAudioTranscriptModel.call(audioTranscriptionPrompt).getResult().getOutput();

        return chatService.askGpt(response);
    }
}
