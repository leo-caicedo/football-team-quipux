public class Player {
    private String name;
    private PlayerType playerType;
    private PositionPlayerType positionPlayerType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            this.name = name.toUpperCase();
        }
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PositionPlayerType getPositionPlayerType() {
        return positionPlayerType;
    }

    public void setPositionPlayerType(PositionPlayerType positionPlayerType) {
        this.positionPlayerType = positionPlayerType;
    }

    @Override
    public String toString () {
        return new StringBuilder()
                .append("<tr>")
                .append("<td>")
                .append(this.name)
                .append("</td>")
                .append("<td>")
                .append(this.playerType)
                .append("</td>")
                .append("<td>")
                .append(this.positionPlayerType)
                .append("</td>")
                .append("</tr>")
                .toString();
    }
}
