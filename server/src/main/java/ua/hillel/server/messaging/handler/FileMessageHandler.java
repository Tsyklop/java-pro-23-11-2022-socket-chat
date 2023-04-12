package ua.hillel.server.messaging.handler;

import ua.hillel.server.messaging.queue.MessageQueue;
import ua.hillel.server.model.message.FileMessage;
import ua.hillel.server.model.type.MessageType;

public class FileMessageHandler extends AbstractMessageHandler<FileMessage> {

    public FileMessageHandler(MessageQueue messageQueue) {
        super(messageQueue, MessageType.FILE);
    }

    @Override
    public void handle(FileMessage message) {

        /*if ("file_start".equals(clientMessage)) {

            long fileSize = -1;
            String fileName;
            File uploadedFile = null;

            boolean fileUploadFinished = false;

            while (!fileUploadFinished) {

                String fileMessage = is.readUTF();

                switch (fileMessage) {
                    case "file_size":
                        fileSize = is.readLong();
                        System.out.println("file size: " + fileSize);
                        break;
                    case "file_name":
                        fileName = is.readUTF();
                        System.out.println("file name: " + fileName);
                        uploadedFile = new File("." + File.separator + "files" + File.separator + fileName);

                        uploadedFile.getParentFile().mkdirs();

                        break;
                    case "file_finish":
                        fileUploadFinished = true;
                        break;
                    case "file_content":

                        if (uploadedFile == null) {
                            System.out.println("uploaded file is null");
                            continue;
                        }

                        try (FileOutputStream fos = FileUtils.openOutputStream(uploadedFile)) {

                            //IOUtils.copy(socket.getInputStream(), fos);

                            int bytesCountReaded;
                            int allBytesCountReaded = 0;

                            byte[] buffer = IOUtils.byteArray((int) fileSize);

                            while (-1 != (bytesCountReaded = is.read(buffer))) {

                                fos.write(buffer, 0, bytesCountReaded);

                                allBytesCountReaded += bytesCountReaded;

                                if (allBytesCountReaded == fileSize) {
                                    break;
                                }

                                if (bytesCountReaded == endOfFile.length
                                        && Arrays.compare(endOfFile, Arrays.copyOfRange(buffer, 0, 3)) == 0) {
                                    break;
                                } else {
                                    fos.write(buffer, 0, bytesCountReaded);
                                }

                            }

                        } finally {
                            System.out.println("Uploaded");
                        }

                        break;
                }

            }

            outputStream.writeUTF("uploaded");
            outputStream.flush();

        } else {
            outputStream.writeUTF("OK");
            outputStream.flush();
        }*/

    }

}
