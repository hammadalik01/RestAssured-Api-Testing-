package pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelNames {
    private String green;
    private String red;
    private String blue;

    // Getters & Setters
    public String getGreen() { return green; }
    public void setGreen(String green) { this.green = green; }

    public String getRed() { return red; }
    public void setRed(String red) { this.red = red; }

    public String getBlue() { return blue; }
    public void setBlue(String blue) { this.blue = blue; }
}
