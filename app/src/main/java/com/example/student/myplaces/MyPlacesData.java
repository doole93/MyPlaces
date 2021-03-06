package com.example.student.myplaces;

import java.util.ArrayList;

/**
 * Created by student on 4/7/2016.
 */
public class MyPlacesData {
    private ArrayList<MyPlace> myPlaces;
    MyPlacesDBAdapter dbAdapter;
    private MyPlacesData(){
        myPlaces = new ArrayList<MyPlace>();
        dbAdapter=new MyPlacesDBAdapter(MyPlacesApplication.getContext());
        dbAdapter.open();
        this.myPlaces=dbAdapter.getAllEntries();
        dbAdapter.close();
        /*myPlaces.add(new MyPlace("Place A"));
        myPlaces.add(new MyPlace("Place B"));
        myPlaces.add(new MyPlace("Place C"));
        myPlaces.add(new MyPlace("Place D"));
        myPlaces.add(new MyPlace("Place E"));*/
    }
    private static class SingletonHolder{
        public static final MyPlacesData instance = new MyPlacesData();
    }

    public static MyPlacesData getInstance()
    {
        return SingletonHolder.instance;
    }

    public ArrayList<MyPlace> getMyPlaces()
    {
        return myPlaces;
    }
    public void addNewPlace(MyPlace place)
    {
        myPlaces.add(place);
        dbAdapter.open();
        long ID=dbAdapter.insertEntry(place);
        dbAdapter.close();
        place.setID(ID);
    }
    public MyPlace getPlace(int index)
    {
        return myPlaces.get(index);
    }
    public void deletePlace(int index)
    {
        MyPlace place= myPlaces.remove(index);
        dbAdapter.open();
        boolean success=dbAdapter.removeEntry(place.getID());
        dbAdapter.close();
    }
    public  void updatePlace(MyPlace place)
    {
        dbAdapter.open();
        dbAdapter.updateEntry(place.getID(), place);
        dbAdapter.close();
    }
}
