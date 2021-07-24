package business.services;

import business.entities.Carport;
import business.entities.SVG;

public class SVGMaker {
    private int width;
    private int length;
    private int shed_length;
    private int shed_width;

    int beam_distance;
    SVG svg;

    public SVGMaker(Carport carport) {
        this.width = carport.getWidth()/10;
        this.length = carport.getLength()/10;
        this.shed_length = carport.getShed_length()/10;
        this.shed_width = carport.getShed_width()/10;
        svg = new SVG(0, 0, "0 0 " + width + " " + length, 25, 25);
        beam_distance = 30;
    }

    public void initialSVGStuff() {
        makeFrame();
        makeBeams();
        makeStolpe();
        makeDiagonals();
    }



    public void makeDimensions(){
        int startx = 0;
        int starty = 0;
        int buffer = 5;
        svg.addLine(width+buffer,starty,width+buffer,length+buffer);
    }

    public void makeFrame() {
        int startx = 0;
        int starty = 0;
        svg.addRect(startx, starty, width, length);
    }

    public void makeBeams() {//remake: makespær
        int startx = 0;
        int starty = 0;
        int count = width / beam_distance;
        System.out.println(count);
        int dist = width / count; //distance between each spær

        for (int i = 0; i < count; i++) {
            svg.addRect(startx, starty, 2, length);
            startx += dist;
        }
    }

    public void makeStolpe() {
        int startx = 0;
        int starty = 0;
        svg.addFilledRect(startx, starty, 3, 3);
        svg.addFilledRect(width - 3, starty, 3, 3);
        svg.addFilledRect(startx, length - 3, 3, 3);
        svg.addFilledRect(width - 3, length - 3, 3, 3);
        if (length > 600 || width > 600) {
            svg.addFilledRect(((width / 4) - 2), ((length / 2) - 2), 4, 4);
            svg.addFilledRect((((width / 4) * 3) - 2), (((length / 4) * 3) - 2), 4, 4);
        } else if (length > 300 || width > 300) {
            svg.addFilledRect(((width / 2) - 2), ((length / 2) - 2), 4, 4);
        }
    }

    public void makeDiagonals() {
        int startx = 0;
        int starty = 0;
        svg.addDottedLine(startx, starty, width, length);
        svg.addDottedLine(width, starty, startx, length);
    }

    public String giveMeSketch() {
        return svg.toString();
    }
}
