package com.example.news.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news.Models.SeModel;
import com.example.news.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fairuz on 20/04/17.
 */

public class Utils {
    public  static  int[] covers = new int[]{
            R.drawable.busin,
            R.drawable.entertainment,
            R.drawable.health,
            R.drawable.himit,
            R.drawable.sport,
            R.drawable.tech,
            R.drawable.science
    };

    public static String Json_Url = " https://newsapi.org/v1/articles?source=bbc-sport&sortBy=top&apiKey=";
    public static String Json_NewsRoot = "articles";
    public static String Json_author = "author";
    public static String Json_title = "title";
    public static String Json_description = "description";
    public static String Json_imgUrl = "urlToImage";
    public static String Json_fullNewUrl= "url";
    public static String Json_publishDate="publishedAt";
    public static String id="_id";
    private static List<SeModel> albumList=new ArrayList<>();

    public static List<SeModel> albumList() {

        albumList.add(new SeModel(covers[0], "GoogleNews", "business"));
        albumList.add(new SeModel(covers[1], "GoogleNews", "entertainment"));
        albumList.add(new SeModel(covers[2], "GoogleNews", "health"));
        albumList.add(new SeModel(covers[3], "HIMITNews", "HIMIT"));
        albumList.add(new SeModel(covers[4], "GoogleNews", "sport"));
        albumList.add(new SeModel(covers[5], "GoogleNews", "technology"));
        albumList.add(new SeModel(covers[6], "GoogleNews", "science"));
        return albumList;
    }
    public static boolean validateForm(Dialog dialog) {
        boolean valid = true;
        EditText title = dialog.findViewById(R.id.title);
        EditText des = dialog.findViewById(R.id.Description);

        String UserName = title.getText().toString();
        if (TextUtils.isEmpty(UserName)) {
            title.setError("requre");
            valid = false;
        } else {
            title.setError(null);
        }

        String emailString = des.getText().toString();
        if (TextUtils.isEmpty(emailString)) {
            des.setError("requre");
            valid = false;
        } else {
            des.setError(null);
        }

        return valid;
    }
    public static boolean checkUser(Context context){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null) {return  true;}
        else {
            Toast.makeText(context, R.string.Sign_in_before, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
