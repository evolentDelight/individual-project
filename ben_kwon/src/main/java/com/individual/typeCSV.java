package com.individual;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;

import java.io.FileReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileWriter;
import java.text.NumberFormat;

import com.opencsv.CSVWriter;

public class typeCSV extends FileAndConversionTemplate {
  
  public typeCSV(String input_name, String output_name){
    super(input_name, output_name);
  }

  public ArrayList<Conversion> parseFile(String input_name){
    ArrayList<Conversion> list = new ArrayList<Conversion>();

    try{
      FileReader filereader = new FileReader(input_name);

      CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
      List<String[]> allData = csvReader.readAll();

      for(String[] row: allData){
        int iterator = 0;
        double Amount = 0;
        String OriginalCurrency = null;
        String TargetCurrency = null;
        for(String cell: row){
          if(iterator == 0) Amount = Double.parseDouble(cell);
          if(iterator == 1) OriginalCurrency = cell;
          if(iterator == 2) TargetCurrency = cell;
          iterator++;
        }
        list.add(new Conversion(Amount, OriginalCurrency, TargetCurrency));
      }

    } catch(Exception e){
      e.printStackTrace();
    }

    return list;
  }

  public void writeFile(ArrayList<Conversion> convertedList, String output_name){
    try{
      FileWriter file = new FileWriter(output_name);

      CSVWriter writer = new CSVWriter(file);

      String [] header = {"Amount", "OriginalCurrency", "TargetCurrency", "ConvertedAmount", "Status"};

      writer.writeNext(header, false);

      for (Conversion i : convertedList){
        String Amount = null;
        if(i.Amount % 1 == 0){//If Original Amount has no decimal, convert back to int
          int intAmount = (int)i.Amount;
          Amount = Integer.toString(intAmount);
        }else{
          Amount = Double.toString(i.Amount);
        }

        if(i.Status.equals("Success")){
          int intConvertedAmount = 0;
          double dblConvertedAmount = 0;
          String strConvertedAmount = null;
          //Check if decimal:
          if(i.ConvertedAmount % 1 == 0){
            intConvertedAmount = (int)i.ConvertedAmount;
          }
          else{
            dblConvertedAmount = i.ConvertedAmount;
          }
          //Check if more than 3 nondecimal digits: Add commas for readability
          if(intConvertedAmount != 0 && intConvertedAmount > 999){//ConvertedAmount is int
            strConvertedAmount = NumberFormat.getIntegerInstance(Locale.US).format(intConvertedAmount);
          }
          if(dblConvertedAmount != 0 && dblConvertedAmount > 999){
            strConvertedAmount = NumberFormat.getNumberInstance(Locale.US).format(dblConvertedAmount);
          }

          if(strConvertedAmount != null){
            String[] data = {Amount, i.OriginalCurrency, i.TargetCurrency, strConvertedAmount, i.Status};
            writer.writeNext(data, false);
            continue;
          }
          else if(dblConvertedAmount != 0){
            String ConvertedAmount = Double.toString(dblConvertedAmount);

            String[] data = {Amount, i.OriginalCurrency, i.TargetCurrency, ConvertedAmount, i.Status};
            writer.writeNext(data, false);
            continue;
          }
          else if(intConvertedAmount != 0){
            String ConvertedAmount = Integer.toString(intConvertedAmount);

            String[] data = {Amount, i.OriginalCurrency, i.TargetCurrency, ConvertedAmount, i.Status};
            writer.writeNext(data, false);
            continue;
          }
        }
        else{
          String[] data = {Amount, i.OriginalCurrency, i.TargetCurrency, "", i.Status};
          writer.writeNext(data, false);
        }
      
      }
      writer.close();
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}
