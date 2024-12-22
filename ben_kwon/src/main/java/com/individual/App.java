package com.individual;
public class App 
{
    public static void main(String [] args){

        String input_name = args[0];//Should be args[0]
        String output_name = args[1];//Should be args[1]
    
        FileAndConversionTemplate conversionType = null;
        
        //Check if CSV, JSON, or XML
        if(args[0].contains(".csv")){
          conversionType = new typeCSV(input_name, output_name);
        }
        else if(args[0].contains(".json")){
          conversionType = new typeJSON(input_name, output_name);
        }
        else if(args[0].contains(".xml")){
          conversionType = new typeXML(input_name, output_name);
        }
        else{
          System.out.println("Incompatible File Type. Please use: CSV, JSON, or XML");
          return;
        }
    
        conversionType.execute();
      }
}
