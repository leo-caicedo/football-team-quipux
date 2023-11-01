import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private String stadiumName;
    private Integer amountTitles;
    private List<Player> players;

    // Setter & getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Integer getAmountTitles() {
        return amountTitles;
    }

    public void setAmountTitles(Integer amountTitles) {
        this.amountTitles = amountTitles;
    }

    public List<Player> getPlayers() {
        if (players == null) {
            return new ArrayList<Player>();
        }
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
