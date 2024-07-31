package org.example.audio;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class AudioFileResolver {

    public FileSystemResource getAudioFileHandler(String path) {
        return new FileSystemResource(path);
    }
}
