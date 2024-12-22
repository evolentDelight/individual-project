package com.individual;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class typeXML extends FileAndConversionTemplate {

  public typeXML(String input_name, String output_name){
    super(input_name, output_name);
  }

  public ArrayList<Conversion> parseFile(String input_name){
    ArrayList<Conversion> list = new ArrayList<Conversion>();

    try{
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();

      Document dom = db.parse(input_name);

      NodeList transaction = dom.getElementsByTagName("transaction");
      for(int i = 0; i < transaction.getLength(); i++){
        Node item = transaction.item(i);
        NodeList nodelist = item.getChildNodes();

        double Amount = 0;
        String OriginalCurrency = null;
        String TargetCurrency = null;

        for( int j = 0; j < nodelist.getLength(); j++){
         Node node = nodelist.item(j);
         if(node.getNodeType() == Node.ELEMENT_NODE){
          if(j == 1) Amount = Double.parseDouble(node.getTextContent());
          if(j == 3) OriginalCurrency = node.getTextContent();
          if(j == 5) TargetCurrency = node.getTextContent();
         }
        }
        list.add( new Conversion(Amount, OriginalCurrency, TargetCurrency));
      }

    } catch(Exception e){
      e.printStackTrace();
    }

    return list;
  }

  public void writeFile(ArrayList<Conversion> convertedList, String output_name){
    try{
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      Document doc = docBuilder.newDocument();

      Element rootElement = doc.createElement("transactions");
      doc.appendChild(rootElement);

      for(Conversion i : convertedList){
        Element transaction = doc.createElement("transaction");

        String Amount = null;
        if(i.Amount % 1 == 0){//If Original Amount has no decimal, convert back to int
          int intAmount = (int)i.Amount;
          Amount = Integer.toString(intAmount);
        }else{
          Amount = Double.toString(i.Amount);
        }

        Element elementAmount = doc.createElement("Amount");
        elementAmount.setTextContent(Amount);
        transaction.appendChild(elementAmount);

        Element elementOriginalCurrency = doc.createElement("OriginalCurrency");
        elementOriginalCurrency.setTextContent(i.OriginalCurrency);
        transaction.appendChild(elementOriginalCurrency);

        Element elementTargetCurrency = doc.createElement("TargetCurrency");
        elementTargetCurrency.setTextContent(i.TargetCurrency);
        transaction.appendChild(elementTargetCurrency);

        Element elementConvertedAmount = doc.createElement("ConvertedAmount");

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
            elementConvertedAmount.setTextContent(strConvertedAmount);

          }
          else if(dblConvertedAmount != 0){
            String ConvertedAmount = Double.toString(dblConvertedAmount);
            elementConvertedAmount.setTextContent(ConvertedAmount);
            
          }
          else if(intConvertedAmount != 0){
            String ConvertedAmount = Integer.toString(intConvertedAmount);
            elementConvertedAmount.setTextContent(ConvertedAmount);
            
          }
          transaction.appendChild(elementConvertedAmount);
        }
        else{
          elementConvertedAmount.setTextContent("");
          transaction.appendChild(elementConvertedAmount);

        }
        Element elementStatus = doc.createElement("Status");
        elementStatus.setTextContent(i.Status);
        transaction.appendChild(elementStatus);

        rootElement.appendChild(transaction);
      }

      //Write to XML file
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();

    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    DOMSource source = new DOMSource(doc);
    
    StreamResult result = new StreamResult(output_name);

    transformer.transform(source, result);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}