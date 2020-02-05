package com.test.demo

class fileIo {
	static void main(args) {
	
	File file=new File("C:\\Users\\Srimaan\\git\\TestProject\\GroovyLearnings\\src\\news.txt");
	
	println file.text
	{
	writer -> writer.writeLine "Hellow";
	}
	} 
	
}
