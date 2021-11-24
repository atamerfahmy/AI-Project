import java.awt.Point;

public class Pad {

	Point startPad;
	Point finishPad;
	
	public Pad(Point startPad, Point finishPad) {
		super();
		this.startPad = startPad;
		this.finishPad = finishPad;
	}
	
	public Point getStartPad() {
		return startPad;
	}

	public Point getEndPad() {
		return finishPad;
	}
	
}
