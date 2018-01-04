/*Student Name: Yibo Wang
UserID: 2203982
Collaboration Statement: I worked on this program by myself without collaboration
*/

public class Fahrenheit{

	public static void main(String[] args){
	System.out.println(toCelsius(100.0));
	}
	
	public static double toCelsius(double fahrenheit){

	double celsius = (fahrenheit-32)/1.8; //converts the Fahrenheit parameter to Celsius

	return celsius;
	}
}