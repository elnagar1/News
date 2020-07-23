package com.example.news.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.news.R;
import com.example.news.utils.Utils;

public class DetailActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_WRITE_STORAGE = 1;
    private Intent intent;
    private TextView newsTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        intent = getIntent();
        final WebView myWebView = findViewById(R.id.webview);
       // checkPermissionIsGrantOrNot();
      //  disableFavBtn();
        newsTitle = findViewById(R.id.news_title);




        myWebView.loadUrl(intent.getStringExtra(Utils.Json_fullNewUrl));

 /*
  favNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFav();
                favNews.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_outline));
                favNews.setEnabled(false);
                Toast.makeText(DetailActivity.this,"Article enrégistré dans les favoris", Toast.LENGTH_SHORT).show();
       }
        });





        } */
   /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
 }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

            if (id == R.id.action_share) {

                String ShareText = intent.getStringExtra(Utils.Json_title)+" On BBC sport read here: "+intent.getStringExtra(Utils.Json_fullNewUrl);

                Intent textShareIntent = new Intent(Intent.ACTION_SEND);
                textShareIntent.putExtra(Intent.EXTRA_TEXT, ShareText);
                textShareIntent.setType("text/plain");
                startActivity(textShareIntent);
            }

        return super.onOptionsItemSelected(item);
    }

    public void checkPermissionIsGrantOrNot(){
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) && ((ContextCompat.checkSelfPermission(this,
        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_WRITE_STORAGE);
            }
        } else {
           // addToFav();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_WRITE_STORAGE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //addToFav();
                } else {
                }
                return;
            }
        }
    }

    public void disableFavBtn(){
        Dao dao = new Dao(DetailActivity.this);
        if (dao.getAllFavNews().size() > 0){
            for (int i = 0;i<dao.getAllFavNews().size();i++){
                if ((dao.getAllFavNews().get(i).getFullArticleUrl()).equals(""+intent.getStringExtra(Utils.Json_fullNewUrl))){
                    favNews.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_outline));
                    favNews.setEnabled(false);
                }
            }
        }
    }
    public void addToFav(){
        Dao dao = new Dao(DetailActivity.this);
        if (dao.getAllFavNews().size() > 0){
            for (int i = 0;i<dao.getAllFavNews().size();i++){
                if ((dao.getAllFavNews().get(i).getFullArticleUrl()).equals(""+intent.getStringExtra(Utils.Json_fullNewUrl))){
                    favNews.setVisibility(View.GONE);
                }else {
                    NewsModel newsModel = new NewsModel(intent.getStringExtra(Utils.Json_author),intent.getStringExtra(Utils.Json_title),intent.getStringExtra(Utils.Json_description),
                            intent.getStringExtra(Utils.Json_imgUrl),intent.getStringExtra(Utils.Json_fullNewUrl),intent.getStringExtra(Utils.Json_publishDate));

                    dao.add(newsModel);
                    break;
                }
            }
        }else {
            NewsModel newsModel = new NewsModel(intent.getStringExtra(Utils.Json_author),intent.getStringExtra(Utils.Json_title),intent.getStringExtra(Utils.Json_description),
                    intent.getStringExtra(Utils.Json_imgUrl),intent.getStringExtra(Utils.Json_fullNewUrl),intent.getStringExtra(Utils.Json_publishDate));

            dao.add(newsModel) ;
        }*/
    }
}
