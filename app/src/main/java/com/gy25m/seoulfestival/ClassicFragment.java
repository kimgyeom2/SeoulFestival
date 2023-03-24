package com.gy25m.seoulfestival;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ClassicFragment extends Fragment {
    ArrayList<FestivalItem> festivalItems = new ArrayList<>();
    RecyclerView recyclerView;

    Myadapter adapter;
    String address = "http://openapi.seoul.go.kr:8088/6b725150454b6735313330425a67686e/xml/culturalEventInfo/1/5/클래식";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classic, container, false);
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(address);
                    InputStream is=url.openStream();
                    InputStreamReader isr=new InputStreamReader(is);

                    XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                    XmlPullParser xpp=factory.newPullParser();
                    xpp.setInput(isr);

                    int eventType=xpp.getEventType();
                    FestivalItem festivalItem=null;

                    while (eventType!=XmlPullParser.END_DOCUMENT){

                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:{

                            }
                            case XmlPullParser.START_TAG:
                                String tagName=xpp.getName();
                                if("row".equals(tagName)){
                                    festivalItem=new FestivalItem();
                                } else if ("CODENAME".equals(tagName)) {
                                    xpp.next();
                                    festivalItem.codeName=xpp.getText();
                                } else if ("TITLE".equals(tagName)) {
                                    xpp.next();
                                    festivalItem.title=xpp.getText();
                                } else if ("DATE".equals(tagName)) {
                                    xpp.next();
                                    festivalItem.date=xpp.getText();
                                } else if ("PLACE".equals(tagName)) {
                                    xpp.next();
                                    festivalItem.place=xpp.getText();
                                } else if ("MAIN_IMG".equals(tagName)) {
                                    xpp.next();
                                    festivalItem.titleImg=xpp.getText();
                                }
                                break;
                            case  XmlPullParser.END_TAG:
                                String tagName2=xpp.getName();
                                if (tagName2.equals("row")){
                                    festivalItems.add(festivalItem);
                                }
                                break;
                            case  XmlPullParser.TEXT:
                                break;
                        }
                        eventType=xpp.next();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView=view.findViewById(R.id.recycler);
                            adapter=new Myadapter(getContext(),festivalItems);
                            recyclerView.setAdapter(adapter);
                        }
                    });

                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();





    }
}
