package com.individual;
import java.util.Iterator;
import java.util.Locale;
import java.util.ArrayList;

import java.io.FileReader;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileWriter;
import java.text.NumberFormat;

public class typeJSON extends FileAndConversionTemplate {

  public typeJSON(String input_name, String output_name){
    super(input_name, output_name);
  }

  public ArrayList<Conversion> parseFile(String input_name){
    ArrayList<Conversion> list = new ArrayList<Conversion>();

    JSONParser parser = new JSONParser();

    try {
      
    Object obj = parser.parse(new FileReader(input_name));

    JSONObject jsonObject = (JSONObject)obj;

    JSONArray transactions = (JSONArray)jsonObject.get("transactions");

    Iterator iterator = transactions.iterator();

    while(iterator.hasNext()){
      JSONObject entry = (JSONObject)iterator.next();

      Long l = (Long)entry.get("Amount");
      double Amount = l.doubleValue();
      String OriginalCurrency = (String)entry.get("OriginalCurrency");
      String TargetCurrency = (String)entry.get("TargetCurrency");
    
      list.add(new Conversion(Amount, OriginalCurrency, TargetCurrency));
    }

    } catch(Exception e){
      e.printStackTrace();;
    }
    return list;
  }

  public void writeFile(ArrayList<Conversion> convertedList, String output_name){
    JSONArray transactions = new JSONArray();
    
    for(Conversion i : convertedList){
      JSONObject obj = new JSONObject();

      if(i.Amount % 1 == 0){//Convert back to integer if there is no decimal
        obj.put("Amount", (int)i.Amount);
      }
      else{
        obj.put("Amount", i.Amount);
      }
      obj.put("OriginalCurrency", i.OriginalCurrency);
      obj.put("TargetCurrency", i.TargetCurrency);

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
          obj.put("ConvertedAmount", strConvertedAmount);
        }
        else if(dblConvertedAmount != 0){
          obj.put("ConvertedAmount", dblConvertedAmount);
        }
        else if(intConvertedAmount != 0){
          obj.put("ConvertedAmount", intConvertedAmount);
        }
        obj.put("Status", i.Status);
      }
      else{
        obj.put("ConvertedAmount", "");
        obj.put("Status", i.Status);
      }
      transactions.add(obj);
    }

    JSONObject output = new JSONObject();
    output.put("transactions", transactions);

    //Write JSONObject to output file
    try{
      FileWriter file = new FileWriter(output_name);

      file.write(output.toJSONString());

      file.close();
    } catch(Exception e){
      e.printStackTrace();
    }
    
  }
}
