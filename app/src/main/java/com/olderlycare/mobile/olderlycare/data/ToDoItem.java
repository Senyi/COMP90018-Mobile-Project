package com.olderlycare.mobile.olderlycare.data;


public class ToDoItem {

 @com.google.gson.annotations.SerializedName("text")
 private String mText;

 @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("Complete")
    private boolean mComplete;

    public ToDoItem(){

    }
    public ToDoItem(String text,String id){
        this.setText(text);
        this.setId(id);
    }

    @Override
    public String toString(){return getText();}

    public String getText() {return mText;}

    public final void setText(String text) {mText = text;}

    public String getId() {return  mId;}

    public final void setId(String id) {mId = id;}

    public boolean ismComplete() {return mComplete;}

    public void setmComplete(boolean complete) {mComplete = complete;}

    @Override
    public boolean equals(Object o) {return o instanceof ToDoItem && ((ToDoItem) o).mId == mId; }

}
