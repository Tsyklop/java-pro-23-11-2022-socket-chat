package ua.hillel.server.messaging.parser;

import org.apache.commons.lang3.StringUtils;
import ua.hillel.server.model.message.FileMessage;
import ua.hillel.server.model.message.Message;
import ua.hillel.server.model.type.MessageType;
import ua.hillel.server.model.message.SimpleMessage;
import ua.hillel.server.model.message.SystemMessage;

/*

    MESSAGE_TYPE
    data

    Examples:

    =======================
    MESSAGE
    message text
    =======================
    FILE
    value for fileSize
    value for fileName
    =======================
    SYSTEM
    value
    =======================

 */
public class MessageParser {

    public Message parse(String value) {

        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("Value have incorrect format");
        }

        String[] valueParts = value.split("\n");

        if (valueParts.length < 2) {
            throw new IllegalArgumentException("Value have incorrect format");
        }

        try {

            MessageType type = MessageType.valueOf(valueParts[0]);

            // switch expressions
            return switch (type) {
                case FILE -> new FileMessage(Long.parseLong(valueParts[1]), valueParts[2]);
                case SYSTEM -> new SystemMessage(valueParts[1]);
                case MESSAGE -> new SimpleMessage(valueParts[1]);
                case PRIVATE_MESSAGE, default -> throw new IllegalArgumentException("Unsupported message type: " + type.name());
            };

        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse message: " + value, e);
        }

        /*switch (type) {
            case FILE:
                return null;
            case SYSTEM:
                return null;
            case MESSAGE:
                return new SimpleMessage(valueParts[1]);
            case PRIVATE_MESSAGE:
                return null;
            default:
                throw new IllegalArgumentException("Unsupported message type: " + type.name());
        }*/

    }

}
