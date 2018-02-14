public class mandelbrot {
	public static void main(String[] args){
		StdDraw.setCanvasSize(800,300);
		StdDraw.setXscale(0, 800);
		StdDraw.setYscale(0, 300);
		StdDraw.filledRectangle(600,150,200,150);
		ComplexNumber test = new ComplexNumber(100, 140);
		StdDraw.setPenRadius(.01);
		recursive(-2, 2, -1.5, 1.5, 1);
		// julia(-2, 2, -1.5, 1.5, 1, test);
		zoom();
	}

	public static void zoom() {
		double lastX = 200; double lastY = 150;
		double lastJX = 600; double lastJY = 150;
    	double trackX = 200; double trackJX = 200;
    	double trackY = 150; double trackJY = 150;
    	double time = 1; double timeJ = 0;
    	double previousx = 2; double previousJx = 2; 
    	double previousy = 1.5; double previousJy = 1.5;
   	 	double newX = 0; double newJX = 0;
   		double newY = 0; double newJY = 0;	
        
        while (true) {
            if (StdDraw.mousePressed()) {
            	lastX = StdDraw.mouseX(); lastY = StdDraw.mouseY();
            	if (lastX<401) {
            		if (lastX != trackX || lastY != trackY) {
            			newX = ComplexNumber.scale(lastX, 0, 400, newX - previousx,  newX + previousx);
      					newY = ComplexNumber.scale(lastY, 0, 300, newY - previousy, newY + previousy);
            			trackX = lastX; trackY = lastY;
            		}
        		} else if (lastX>400) {
            		if (StdDraw.mouseX() != trackJX || StdDraw.mouseY() != trackJY) {
            			newJX = ComplexNumber.scale(lastX, 400, 800, newJX - previousJx,  newJX + previousJx);
            			newJY = ComplexNumber.scale(lastY, 0, 300, newJY - previousJy, newJY + previousJy);
            			trackJX = StdDraw.mouseX(); trackJY = StdDraw.mouseY();
            		}
        		}
            } else if (StdDraw.isKeyPressed(73)) {
            	zoomSetUp(lastX);
            	if (lastX > 400) {
            		timeJ++;
            		previousJx/=2; previousJy/=2;
            		julia(newJX - previousJx, newJX + previousJx , newJY - previousJy, newJY + previousJy, timeJ, new ComplexNumber(newX, newY));
            	} else if (lastX < 401) {
            		time++;
            		previousx/=2; previousy/=2;
            		recursive(newX - previousx, newX + previousx , newY - previousy, newY + previousy, time);
            	}	
            } else if (StdDraw.isKeyPressed(74)) {
            	// zoomSetUp(lastX);
            	if (lastX < 401) {
            		time--;
            		previousx*=2; previousy*=2;	
           			recursive(newX - previousx, newX + previousx , newY - previousy, newY + previousy, time);
            	} else if (lastX > 400) {
            		timeJ--;
            		previousJx*=2; previousJy*=previousJy;	
            		julia(newJX - previousJx, newJX + previousJx , newJY - previousJy, newJY + previousJy, timeJ, new ComplexNumber(newX, newY));
            	}	
            }	
        }
    }

	public static void recursive(double x1, double x2, double y1, double y2, double time){
		System.out.println(x1+" "+x2+" "+y1+" "+y2);
		ComplexNumber z0 = new ComplexNumber(0,0);
		int ans;
		for (double x = x1; x<= x2; x+=0.03/(Math.pow(time, 2))){
			for(double y = y1; y<=y2; y+=0.03/(Math.pow(time, 2))) {
				ans = check(z0, new ComplexNumber(x, y), 0);	
				color(ans);
				ComplexNumber.leftGraph(new ComplexNumber(x, y), x1, x2, y1, y2);		
			}
		}
	}

	public static void julia(double x1, double x2, double y1, double y2, double time, ComplexNumber cons){
		System.out.println("julia: "+x1+" "+x2+" "+y1+" "+y2);
		ComplexNumber z0;
		int ans;
		for (double x = x1; x<= x2; x+=0.03/(Math.pow(time, 2))){
			for(double y = y1; y<=y2; y+=0.03/(Math.pow(time, 2))){
				z0 = new ComplexNumber(x,y);
				ans = check(z0, cons, 0);	
				color(ans);
				ComplexNumber.rightGraph(z0, x1, x2, y1, y2);
			}
		}
	}

	public static int check(ComplexNumber zn, ComplexNumber c, int counter){
		int ans = 0;
		double magnitude = zn.magnitude();
		// System.out.println(magnitude);

		if (magnitude < 2){
			if (counter < 255){
				ComplexNumber zn1 = new ComplexNumber((zn.power(2)).add(c));
				counter+=1;
				ans = check(zn1, c, counter);
				return ans;
			} else {
				ans = 255;
			}
		} else {
			ans = counter*20;
		}
		return ans;
	}

	public static void color(int ans) {
		if (ans == 255) {
			StdDraw.setPenColor(StdDraw.BLACK);
		} else {
			if (ans < 255) {
				ans = (int) ComplexNumber.scale(ans, 0, 100, 0, 100);
			} else {
				ans = (int) ComplexNumber.scale(ans, 100, 5000, 100, 250);
			}
			StdDraw.setPenColor(ans, 0, ans);
		}
	}

	public static void zoomSetUp(double xVal) {
		if (xVal< 400) {
			StdDraw.setPenColor(StdDraw.WHITE);
       		StdDraw.filledRectangle(200,150,200,150);
       	} else if (xVal > 400) { 
        	StdDraw.setPenColor(StdDraw.BLACK);
           	StdDraw.filledRectangle(600,150,200,150);
        }
	}

}