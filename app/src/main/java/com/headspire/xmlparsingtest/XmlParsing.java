package com.headspire.xmlparsingtest;

import android.util.Log;
import android.util.Xml;

import com.headspire.xmlparsingtest.model.Employee;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XmlParsing contains parsing the xml
 */
public class XmlParsing {

    private List<Employee> employees;
    private Employee employee;
    private String text;

    public XmlParsing()
    {
        employees=new ArrayList<Employee>();
    }


    public List<Employee> parse(InputStream inputStream)
    {
        try {
            XmlPullParserFactory xmlPullParserFactory=XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser=xmlPullParserFactory.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            xmlPullParser.setInput(inputStream,null);
            int eventType = xmlPullParser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT)
            {
                String tagName=xmlPullParser.getName();
                switch(eventType)
                {
                    case XmlPullParser.START_TAG:
                        if(tagName!=null && tagName.equalsIgnoreCase("employee"))
                            employee=new Employee();
                        break;
                    case XmlPullParser.TEXT:
                        text=xmlPullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(tagName.equalsIgnoreCase("employee"))
                        {
                            employees.add(employee);
                        }
                        else if(tagName.equalsIgnoreCase("id"))
                            employee.setId(Integer.parseInt(text));
                        else if(tagName.equalsIgnoreCase("name"))
                            employee.setName(text);
                        break;
                        default:
                            Log.e("tagg","in default");
                            break;
                }
                eventType=xmlPullParser.next();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return employees;
    }
}
