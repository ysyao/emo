package com.phl.emoproject.core;


import android.util.Xml;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.google.gson.stream.JsonToken.BEGIN_ARRAY;

public abstract class BaseAsyncHttpResponseHandler<T> extends AsyncHttpResponseHandler {
    private Type type;
    private Type listType;

    public BaseAsyncHttpResponseHandler() {
        super();
    }
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getListType() {
        return listType;
    }

    public void setListType(Type listType) {
        this.listType = listType;
    }

    public abstract void onSuccess(int statusCode, Header[] headers, T t);


    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

        onSuccess(statusCode, headers, parseByte2JsonPojo(getType(), getListType(),
                responseBody));
    }

    /**
     * 将响应获得的byte转换成为json，在onSuccess方法当中打印出来
     *
     * @param type
     *            转换POJO
     * @param listType
     * @param responseBody
     * @return
     */
    public T parseByte2JsonPojo(Type type, Type listType,
                                byte[] responseBody) {
        ByteArrayInputStream bis = new ByteArrayInputStream(responseBody);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    bis, "UTF-8"), BUFFER_SIZE);

            XmlPullParser parser = Xml.newPullParser();
            String s = "";
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(reader);
//            parser.nextTag();

            int event = parser.getEventType();
            String text = "";
            while (event != XmlPullParser.END_DOCUMENT)
            {
                String name=parser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("string")){
                            s = text;
                        }
                        break;
                }
                event = parser.next();
            }

            Gson gson = new Gson();
            if (listType == null) {
                return gson.fromJson(s.trim(), type);
            }
//            else {
//                JsonReader jsonReader = new JsonReader(reader);
//                if (jsonReader.peek() == BEGIN_ARRAY) {
//                    return gson.fromJson(s, listType);
//                }
//            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
