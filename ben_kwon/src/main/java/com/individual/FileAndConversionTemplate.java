package com.individual;
import java.util.Arrays;
import java.util.ArrayList;

class Conversion {// Initial Amount will be in double to consider decimals
  public double Amount;
  public String OriginalCurrency;
  public String TargetCurrency;
  public double ConvertedAmount;
  public String Status;

  Conversion(double Amount, String OriginalCurrency, String TargetCurrency){
    this.Amount = Amount;
    this.OriginalCurrency = OriginalCurrency;
    this.TargetCurrency = TargetCurrency;
  }
}

public abstract class FileAndConversionTemplate{
  private String input_name;
  private String output_name;

  public FileAndConversionTemplate(String input_name, String output_name){
    this.input_name = input_name;
    this.output_name = output_name;
  }

  public final void execute(){
    ArrayList<Conversion> list = parseFile(this.input_name);
    ArrayList<Conversion> convertedList = executeConversion(list);
    writeFile(convertedList, this.output_name);
  }

  public abstract ArrayList<Conversion> parseFile(String input_name);//JSON, CSV, or XML
  //Returns data structure to do conversion
  //Data Structure should be the same throughout all file formats

  public ArrayList<Conversion> executeConversion(ArrayList<Conversion> list){
    for (Conversion i : list){

      //Check for Issues
      ArrayList<String> validCurrency = new ArrayList<String>(Arrays.asList("USD", "EUR", "GBP", "AUD", "CAD", "CHF", "JPY", "INR"));
      //Check if OriginalCurrency is a valid currency code
      if(!validCurrency.contains(i.OriginalCurrency)){
        if(i.OriginalCurrency.matches("-?\\d+(\\.\\d+)?")){
          i.Status = "Invalid original currency should not contain numbers";
        }
        else{
          i.Status = "Invalid original currency code";
        }
        continue;
      }
      //Check if TargetCurrency is a valid currency code
      if(!validCurrency.contains(i.TargetCurrency)){
        if(i.TargetCurrency.matches("-?\\d+(\\.\\d+)?")){
          i.Status = "Invalid target currency should not contain numbers";
        }
        else{
          i.Status = "Invalid target currency code";
        }
        continue;
      }

      if(i.OriginalCurrency.equals("USD")){
        if(i.TargetCurrency.equals("EUR")){
          //0.94
          i.ConvertedAmount = i.Amount * 94 / 100;
          i.Status = "Success";
          continue;
        }
        if(i.TargetCurrency.equals("CAD")){
          // 1/0.73
          i.ConvertedAmount = i.Amount * 100 / 73;
          i.Status = "Success";
          continue;
        }
        if(i.TargetCurrency.equals("CHF")){
          i.ConvertedAmount = i.Amount * 0.91;
          i.Status = "Success";
          continue;
        }
        if(i.TargetCurrency.equals("JPY")){
          // 1/0.0065
          i.ConvertedAmount = i.Amount * 10000 / 65;
          i.Status = "Success";
          continue;
        }
      }
      if(i.OriginalCurrency.equals("EUR")){
        if(i.TargetCurrency.equals("GBP")){
          //0.86
          i.ConvertedAmount = i.Amount * 0.86;
          i.Status = "Success";
          continue;
        }
        if(i.TargetCurrency.equals("USD")){
          // 1/0.94
          i.ConvertedAmount = i.Amount * 1/0.94;
          i.Status = "Success";
          continue;
        }
      }
      if(i.OriginalCurrency.equals("GBP")){
        if(i.TargetCurrency.equals("INR")){
          //103.98
          i.ConvertedAmount = i.Amount * 103.98;
          i.Status = "Success";
          continue;
        }
        if(i.TargetCurrency.equals("EUR")){
          // 1/0.86
          i.ConvertedAmount = i.Amount * 1/0.86;
          i.Status = "Success";
          continue;
        }
      }
      if(i.OriginalCurrency.equals("AUD")){
        if(i.TargetCurrency.equals("CAD")){
          // 0.89
          i.ConvertedAmount = i.Amount * 0.89;
          i.Status = "Success";
          continue;
        }
        if(i.TargetCurrency.equals("CHF")){
          // 1/1.69
          i.ConvertedAmount = i.Amount * 1/1.69;
          i.Status = "Success";
          continue;
        }
      }
      if(i.OriginalCurrency.equals("CAD")){
        if(i.TargetCurrency.equals("USD")){
          // 0.73
          i.ConvertedAmount = i.Amount * 0.73;
          i.Status = "Success";
          continue;
        }
        if(i.TargetCurrency.equals("AUD")){
          // 1/0.89
          i.ConvertedAmount = i.Amount * 1/0.89;
          i.Status = "Success";
          continue;
        }
      }
      if(i.OriginalCurrency.equals("CHF")){
        if(i.TargetCurrency.equals("AUD")){
          // 1.69
          i.ConvertedAmount = i.Amount * 1.69;
          i.Status = "Success";
          continue;
        }
        if(i.TargetCurrency.equals("USD")){
          // 1/0.91
          i.ConvertedAmount = i.Amount * 1/0.91;
          i.Status = "Success";
          continue;
        }
      }
      if(i.OriginalCurrency.equals("JPY")){
        if(i.TargetCurrency.equals("USD")){
          // 0.0065
          i.ConvertedAmount = i.Amount * 0.0065;
          i.Status = "Success";
          continue;
        }
      }
      if(i.OriginalCurrency.equals("INR")){
        if(i.TargetCurrency.equals("GBP")){
          // 1/103.98
          i.ConvertedAmount = i.Amount * 1/103.98;
          i.Status = "Success";
          continue;
        }
      }

    }
    
    return list;
  }

  public abstract void writeFile(ArrayList<Conversion> convertedList, String output_name);//JSON, CSV, or XML


}