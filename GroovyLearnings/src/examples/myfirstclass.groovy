package examples

class RangeExample {
    static void main(def args) {
        //Variable declarations
     
    def rootFiles = new File("filePath").listRoots()
    rootFiles.each {
        file -> println file.Directory
    }
}
}
