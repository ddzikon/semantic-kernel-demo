package org.example.audio;

import javafx.concurrent.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.File;
import java.util.concurrent.Future;

@Slf4j
@RequiredArgsConstructor
public class AudioRecorder extends Task<byte[]> implements Future<byte[]> {

    private final File recordingFile;

    private TargetDataLine targetDataLine;

    @Override
    protected byte[] call() throws Exception {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            throw new UnsupportedOperationException("Audio file format is not supported on this system: " + format.toString());
        }

        targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
        targetDataLine.open(format);
        targetDataLine.start();

        log.info("Start capturing...");

        AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);

        log.info("Start recording...");

        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, recordingFile);

        return null;
    }

    public void finish() {
        targetDataLine.stop();
        targetDataLine.close();
        log.info("Recording stopped");
    }
}
