package com.bugchain.android.development.sampleconnectserver;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BUG CHAIN on 27/2/2558.
 */
public class MemberObject {

    private String firstName;
    private String lastName;
    private String tel;
    private String email;
    private String imageUrl;

    private void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }

    private void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }

    private void setTel(String tel){
        this.tel = tel;
    }
    public String getTel(){
        return tel;
    }
    private void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    private void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return  imageUrl;
    }

    private MemberObject setMemberObject(String firstName,String lastName,
                                 String tel,String email,String imageUrl){
        MemberObject object = new MemberObject();
        object.setFirstName(firstName);
        object.setLastName(lastName);
        object.setTel(tel);
        object.setEmail(email);
        object.setImageUrl(imageUrl);

        return object;
    }

    public List<MemberObject> getMemberObject(String jsonString){
        List<MemberObject> list = new ArrayList<MemberObject>();
        MemberObject object = new MemberObject();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if(jsonArray != null){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject info = jsonArray.getJSONObject(i);
                    list.add(object.setMemberObject(
                            info.getString("firstname"),
                            info.getString("lastname"),
                            info.getString("tel"),
                            info.getString("email"),
                            info.getString("profile_picture")
                    ));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("Member Objects","All item size " + list.size());

        return list;
    }
}
