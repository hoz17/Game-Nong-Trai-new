package Model;

public class Message {
    public String playerName;
    public String message;

    public Message(String playerName, String message) {
        this.playerName = playerName;
        this.message = message;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMessage() {
        return message;
    }
}
