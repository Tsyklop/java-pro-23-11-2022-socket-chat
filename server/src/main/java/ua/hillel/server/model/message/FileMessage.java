package ua.hillel.server.model.message;

import ua.hillel.server.model.type.MessageType;

public class FileMessage extends Message {

    private final long fileSize;

    private final String fileName;

    public FileMessage(long fileSize, String fileName) {
        super(MessageType.FILE);
        this.fileSize = fileSize;
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileName() {
        return fileName;
    }

}
