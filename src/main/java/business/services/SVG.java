package business.services;

public class SVG {

    StringBuilder stringBuilder = new StringBuilder();
    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;
    private final String header = "<svg height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectEmptyTemplate = "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"stroke:#000; fill: none\" />";
    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"stroke:#000; fill: #FAFAFA\" />";
    private final String filledRectTemplate = "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\"  style=\"stroke:#000; fill: #c9c9c9\" />";
    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"black\" />";
    private final String lineDottedTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"black\" stroke-dasharray=\"4 4\" />";

    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        stringBuilder.append(String.format(header,height,width,viewBox,x,y));
    }


    public void addFilledRect(int x, int y, int width, int height) {
        stringBuilder.append(String.format(filledRectTemplate, x, y, width, height));
    }

    public void addRect(int x, int y, int width, int height) {
        stringBuilder.append(String.format(rectTemplate, x, y, width, height));
    }

    public void addEmptyRect(int x, int y, int width, int height) {
        stringBuilder.append(String.format(rectEmptyTemplate, x, y, width, height));
    }
    public void addLine(int x1, int y1, int x2, int y2) {
        stringBuilder.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addDottedLine(int x1, int y1, int x2, int y2) {
        stringBuilder.append(String.format(lineDottedTemplate, x1, y1, x2, y2));
    }


    @Override
    public String toString() {
        return stringBuilder.toString() + "</svg>";
    }
}
