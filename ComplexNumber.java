/**
 * @author      Ayesha Ali
 * @version     1.0                 
 * @since       2013-11-17          
 */
public class ComplexNumber {

	private double a; 
	private double b;
	
	/**
	 * Creates a new ComplexNumber with both real and imaginary components
	 * @param a the real component of the complex number
	 * @param b the imaginary component of the complex number
	 */
	public ComplexNumber(double a, double b){
		this.a = a;
		this.b = b;		
	}
	
	/**
	 * The "copy constructor"
	 * Creates a new ComplexNumber from an existing ComplexNumber
	 * @param c a ComplexNumber
	 */
	public ComplexNumber(ComplexNumber c){
		a = c.getReal();
		b = c.getImaginary();
	}
	
	/**
	* The default constructor
	**/
	public ComplexNumber() {
		this.a = 1;
		this.b = 1;
	}
	/**
	 * An "accessor" method
	 * Returns the real component of this ComplexNumber
	 * @return a the private real component of this ComplexNumber 
	 */
	public double getReal(){
		return a;
	}
	
	/**
	 * An "accessor" method
	 * Returns the imaginary component of this ComplexNumber
	 * @return b the private imaginary component of this ComplexNumber 
	 */
	public double getImaginary(){
		return b;
	}

	/**
	 * An instance method to add two complex numbers
	 * @param add2 the complex number added to the instance
	 * @return result the result of the addition
	 */
	public ComplexNumber add(ComplexNumber add2) {
		double real2 = add2.getReal();
		double img2 = add2.getImaginary();
		ComplexNumber result  = new ComplexNumber(this.a+real2, this.b+img2);
		return result;
	}

	/**
	 * A static method that adds two complex numbers
	 * @param add1 first complex number
	 * @param add2 second complex number
	 * @return result the result of the addition
	 */
	public static ComplexNumber add(ComplexNumber add1, ComplexNumber add2) {
		double real1 = add1.getReal();
		double img1 = add1.getImaginary();
		double real2 = add2.getReal();
		double img2 = add2.getImaginary();
		ComplexNumber result  = new ComplexNumber(real1+real2, img1+img2);
		return result;
	}

	/**
	 * An instance method to subtract two complex numbers
	 * @param subtract2 the complex number subtracted from the instance
	 * @return result the result of the subtraction
	 */
	public ComplexNumber subtract(ComplexNumber subtract2) {
		double real2 = subtract2.getReal();
		double img2 = subtract2.getImaginary();
		ComplexNumber result  = new ComplexNumber(this.a-real2, this.b-img2);
		return result;
	}

	/**
	 * A static method that subtracts two complex numbers
	 * @param subtract1 first complex number
	 * @param subtract2 second complex number
	 * @return result the result of the subtraction
	 */
	public static ComplexNumber subtract(ComplexNumber subtract1, ComplexNumber subtract2) {
		double real1 = subtract1.getReal();
		double img1 = subtract1.getImaginary();
		double real2 = subtract2.getReal();
		double img2 = subtract2.getImaginary();
		ComplexNumber result  = new ComplexNumber(real1-real2, img1-img2);
		return result;
	}

	/**
	 * An instance method to multiply two complex numbers
	 * @param multiply2 the complex number multiplied by the instance
	 * @return result the product
	 */
	public ComplexNumber multiply(ComplexNumber multiply2) {
		double f = this.a*multiply2.getReal();
		double o = this.a*multiply2.getImaginary();
		double i = this.b*multiply2.getReal();
		double l = this.b*multiply2.getImaginary()*-1;

		double real = f +l;
		double imaginary = o +i;

		ComplexNumber result = new ComplexNumber(real, imaginary);
		return result;
	}

	/**
	 * A static method that multiplies two complex numbers
	 * @param multiply1 first complex number
	 * @param multiply2 second complex number
	 * @return result the result of the multiplication
	 */
	public static ComplexNumber multiply(ComplexNumber multiply1, ComplexNumber multiply2) {
		double f = multiply1.getReal()*multiply2.getReal();
		double o = multiply1.getReal()*multiply2.getImaginary();
		double i = multiply1.getImaginary()*multiply2.getReal();
		double l = multiply1.getImaginary()*multiply2.getImaginary()*-1;

		double real = f +l;
		double imaginary = o +i;

		ComplexNumber result = new ComplexNumber(real, imaginary);
		return result;
	}

	/**
	 * An instance method to divide two complex numbers
	 * @param divide2 the complex number that the instance would be divided by 
	 * @return result the result of the division
	 */
	public ComplexNumber divide(ComplexNumber divide2) {
		ComplexNumber denominator = divide2.multiply(divide2.conjugate());
		ComplexNumber numerator = this.multiply(divide2.conjugate());

		if (denominator.getReal() == 0 ) {
			throw new ArithmeticException();
		}

		double real = numerator.getReal()/denominator.getReal();
		double imaginary = numerator.getImaginary()/denominator.getReal();

		ComplexNumber result = new ComplexNumber(real, imaginary);
		return result;
	}

	/**
	 * A static method that divides two complex numbers
	 * @param divide1 first complex number
	 * @param divide2 second complex number
	 * @return result the result of the division
	 */
	public static ComplexNumber divide(ComplexNumber divide1, ComplexNumber divide2) {
		ComplexNumber denominator = divide2.multiply(divide2.conjugate());
		ComplexNumber numerator = divide1.multiply(divide2.conjugate());

		if (denominator.getReal() == 0 ) {
			throw new ArithmeticException();
		}

		double real = numerator.getReal()/denominator.getReal();
		double imaginary = numerator.getImaginary()/denominator.getReal();

		ComplexNumber result = new ComplexNumber(real, imaginary);
		return result;
	}

	/**
	 * An instance method that raises an instance to a power
	 * @param exponent the exponent 
	 * @return result the result of the multiplication
	 */
	public ComplexNumber power(int exponent) {
		ComplexNumber result= new ComplexNumber(this);
		if (exponent == 0) {
			return new ComplexNumber(1, 0);
		} else if (exponent < 0) {
			throw new IllegalArgumentException();
		}

		for (int i = 1; i < exponent; i++) {
			result = result.multiply(this);
		}

		return result;
	}

	/**
	 * A static method that raises a complex number to a power
	 * @param input the complex number
	 * @param exponent the power the complex number will be raised to
	 * @return result the result of the multiplication
	 */
	public static ComplexNumber power(ComplexNumber input, int exponent) {
		ComplexNumber result= new ComplexNumber(input);
		
		if (exponent == 0) {
			return new ComplexNumber(1, 0);
		} else if (exponent < 0) {
			throw new IllegalArgumentException();
		}

		for (int i = 1; i < exponent; i++) {
			result = result.multiply(input);
		}

		return result;
	}

	/**
	 * An instance method that returns the conjugate of a complex number
	 * @return result conjugate of a complex number
	 */
	private ComplexNumber conjugate() {
		return (new ComplexNumber(this.a, 0-this.b));
	} 

	/**
	 * An instance method that turns a complex number into a string
	 * @return result a string that returns the complex number in the familiar form of a+bi
	 */
	public String toString() {
		String real;
		String img ="";

		if (this.a ==0.0 && this.b == 0.0) return "0";
		
		if (this.a == 0.0) real = "";
		else real = this.a+"";
		
		if (this.b == 0.0) img = "";
		else if (this.b == 1) img = "i";
		else if (this.b == -1) img = "-i";
		else if (this.b > 0.0 && a != 0) img= "+ "+this.b+"i";
		else if (this.b > 0.0 && a == 0) img= this.b+"i";
		else if (this.b <0.0) img= "- "+java.lang.Math.abs(this.b)+"i";


		String result = real+" "+img;
		return result;
	}

	/**
	 * An instance method that returns the magnitude of a complex number 
	 * @return magnitude magnitude (double)
	 */
	public double magnitude() {
		return (Math.sqrt(this.a*this.a + this.b*this.b));
	}
	
	/**
	 * An instance method that compares the magnitudes of two complex numbers 
	 * @param c the second complex number  
	 * @return result This method returns a negative number if the second number has a greater magnitude, a positive number if the instance has a greater magnitude, and 0 if they are equal  
	 */
	public int compareTo(ComplexNumber c) {
		return ((int) (this.magnitude() -c.magnitude()));
	}
	
	public static void graph(ComplexNumber cons, double x1, double x2, double y1, double y2, double choice) {
		if (choice == 1) leftGraph(cons, x1, x2, y1, y2);
		else if (choice == 2) rightGraph(cons, x1, x2, y1, y2);
		
	}

	public static void leftGraph(ComplexNumber cons, double x1, double x2, double y1, double y2) {
		double x = scale(cons.getReal(), x1, x2, 0, 400);
		double y = scale(cons.getImaginary(), y1, y2, 0, 300);
		StdDraw.point(x, y);
		
	}

	public static void rightGraph(ComplexNumber cons, double x1, double x2, double y1, double y2) {
		double x = scale(cons.getReal(), x1, x2, 400, 800);
		double y = scale(cons.getImaginary(), y1, y2, 0, 300);
		StdDraw.point(x, y);
		
	}

	public static double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax) {
        return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
    }
	/**
	 * A tester method
	 * @param args
	 */
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(3, -2);
		ComplexNumber c2 = new ComplexNumber(2, 1);
		ComplexNumber zero = new ComplexNumber(0,0);
		ComplexNumber one = new ComplexNumber(1,0);
		
		System.out.println(c1.toString());
		System.out.println(c2.toString());

		System.out.println("\nmagnitude: ");
		System.out.println(c1.magnitude());
		System.out.println(c2.magnitude());
		System.out.println(zero.magnitude());

		System.out.println("\ncompare to: ");
		System.out.println(c1.compareTo(c2));

		System.out.println("\nAdd: ");
		System.out.println((c1.add(c2)).toString());
		System.out.println((c1.add(zero)).toString());
		System.out.println((c1.add(one)).toString());
		System.out.println((add(c1, c2)).toString());
		
		System.out.println("\nSubtract: ");
		System.out.println((c1.subtract(c2)).toString());
		System.out.println((c1.subtract(zero)).toString());
		System.out.println((c1.subtract(one)).toString());
		System.out.println((subtract(c1, c2)).toString());

		System.out.println("\nMultiply: ");
		System.out.println((c1.multiply(c2)).toString());
		System.out.println((c1.multiply(zero)).toString());
		System.out.println((c1.multiply(one)).toString());
		System.out.println((multiply(c1, c2)).toString());

		System.out.println("\nDivide: ");
		System.out.println((c1.divide(c2)).toString());
		// System.out.println((c1.divide(zero)).toString());
		System.out.println((c1.divide(one)).toString());
		System.out.println((divide(c1, c2)).toString());


		System.out.println("\nExponent: ");
		System.out.println((c1.power(2)).toString());
		System.out.println((c1.power(0)).toString());
		// System.out.println((c1.power(-1)).toString());
		System.out.println((power(c1, 4)).toString());

	}
}

