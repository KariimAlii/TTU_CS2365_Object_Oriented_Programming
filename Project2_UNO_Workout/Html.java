/*
 * TITLE: Workout Class for Project 2 UNO Workout
 * AUTHOR: Shree Shrestha
 * FOR: CS 2365 Object Oriented Programming Section 002 Spring 2020
 * CLASS DESCRIPTION: class is used to run a workout using an deck or UNO cards
 * TEST CASES: test cases for this class are held in the UNOWorkoutMain class
 * 
 * NOTE: Images for the HTML file created in this class are hosted on github and
         you must be connected to the internet to view the images.
 */
package unoworkout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


/** The class is used to create html files from output
 * @author Shree Shrestha
 */
public class Html {
     private PrintStream output;

     
    /**WriteFileOpener Method
     * @param filename
     * @throws FileNotFoundException
     * DESCRIPTION: Opens a file for writing
     * test case 1: adds a printstream for output
     */
    public void WriteFileOpener(String filename) throws FileNotFoundException{
         File outfile = new File(filename);
         output = new PrintStream(outfile);
     }
     
    
    /** printHeader Method
     * DESCRIPTION: takes an open output file and outputs a preset header to the file
     * test case 1: adds header to file
     */
    public void printHeader()
    {
        if(this.output != null)
        {
            this.output.println("<!DOCTYPE html>\n");
            this.output.println( "<html class=\"UNOWorkoutMain\" lang=\"en-us\">\n" +
                    "<head>\n" +
               
                    "   <title>UNO WORKOUT | FINAL OUTPUT </title>\n" +
                    "  </head>\n" +
                    " <body>\n" +
                    "<h1>UNO WORKOUT</h1>\n" +
                    "<p><img src=\"https://github.com/sdevaneyTTU/projectimages/blob/master/unoworkouts.jpg?raw=true\" alt=\"Workouts\" width=\"900\" height=\"100\" style=\"float: center; margin: 0 0 10px 10px;\" /></p>\n" +
                    "<p><img src=\"https://github.com/sdevaneyTTU/projectimages/blob/master/scatteredunocards.jpg?raw=true\" alt=\"Scattered UNO Cards\" width=\"400\" height=\"400\" style=\"float: right; margin: 0 0 10px 10px;\" /></p>\n" +
                    
                    "<h2> GAME RESOLUTION</h2>\n");
        }
    }
     
    
    /** addOpeningParagraphTag Method
     * DESCRIPTION: takes an open output file and outputs a opening paragraph tag to the file
     * test case 1: adds opening paragraph tag to file
     */
    public void addOpeningParagraphTag(){
        this.output.println("<p>");
    }
    
    
    /** addClosingParagraphTag Method
     * DESCRIPTION: takes an open output file and outputs a closing paragraph tag to the file
     * test case 1: adds closing paragraph tag to file
     */
    public void addClosingParagraphTag(){
        this.output.println("</p>\n");
    }
    
    
    /**addLine Method
     * DESCRIPTION: takes an open output file and outputs string that is a line to the file
     * test case 1: adds line to the file
     * @param line
     */
    public void addLine(String line){
        this.output.println(line + "<br>");
    }
    
    
    /**closeFile Method
     * DESCRIPTION: takes an open output file and outputs a closing body tag and
     *              html tag to the file. Then closes the file.
     * test case 1: finalizes and closes the file
     */
    public void closeFile(){
        this.output.println("</body>\n</html>");
        this.output.close();
    }
}
