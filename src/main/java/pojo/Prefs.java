package pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prefs {
    private String permissionLevel;
    private boolean hideVotes;
    private String voting;
    private String comments;
    private boolean selfJoin;
    private String background;
    private String backgroundColor;

    // Getters & Setters
    public String getPermissionLevel() { return permissionLevel; }
    public void setPermissionLevel(String permissionLevel) { this.permissionLevel = permissionLevel; }

    public boolean isHideVotes() { return hideVotes; }
    public void setHideVotes(boolean hideVotes) { this.hideVotes = hideVotes; }

    public String getVoting() { return voting; }
    public void setVoting(String voting) { this.voting = voting; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public boolean isSelfJoin() { return selfJoin; }
    public void setSelfJoin(boolean selfJoin) { this.selfJoin = selfJoin; }

    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }

    public String getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(String backgroundColor) { this.backgroundColor = backgroundColor; }
}
