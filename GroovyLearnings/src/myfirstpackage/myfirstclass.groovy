package myfirstpackage;



class myfirstclass {
	public static void main(String[] args) {
		def File=new File("C:\\Users\\Srimaan\\git\\TestProject\\API\\src\\test\\java\\test1\\putRequest.java")


		File.each { println it }

		def lineNumber=0
		File.eachLine {    line ->
        lineNumber++
        println "$lineNumber: $line"}
	}
}



