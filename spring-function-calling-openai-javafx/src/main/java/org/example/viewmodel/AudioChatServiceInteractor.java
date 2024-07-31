package org.example.viewmodel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ai.AudioChatService;
import org.example.audio.AudioRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AudioChatServiceInteractor {

    private final AudioChatService audioChatService;

    private AudioRecorder audioRecorder;

    private File temporaryAudioFile;

    public void startRecording() {
        try {
            temporaryAudioFile = File.createTempFile("spring-function-calling-openai-demo-recording", ".wav");
        } catch (IOException e) {
            log.error("Temporary temporaryAudioFile creation failed");
            throw new RuntimeException(e);
        }

        this.audioRecorder = new AudioRecorder(this.temporaryAudioFile);
        final var recordingThread = new Thread(audioRecorder);
        recordingThread.start();
    }

    public void stopRecording() {
        this.audioRecorder.finish();
        this.audioRecorder.cancel();
        this.audioRecorder = null;

        log.info("Temporary audio file absolute path: {}", temporaryAudioFile.getAbsolutePath());

        FileSystemResource fileSystemResource = new FileSystemResource(temporaryAudioFile);
        audioChatService.askGpt(fileSystemResource);

        try {
            Files.deleteIfExists(temporaryAudioFile.toPath());
            log.info("Deleting temporary file");
        } catch (IOException e) {
            final var message = String.format("Exception while deleting temporary file at: {}", temporaryAudioFile.getAbsolutePath());
            log.error(message, e);
            throw new RuntimeException(e);
        }
        temporaryAudioFile = null;
    }

}
