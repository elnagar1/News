package com.example.news.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Adapters.MainAdapter;
import com.example.news.Models.SeModel;
import com.example.news.R;
import com.example.news.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<SeModel> albumList;
    private Toolbar toolbar;
    private  static int Number = 0;
    private MainAdapter mainAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);

  //      initCollapsingToolbar();


        prepareAlbums();
        prepareRecycler();





        bmb.addBuilder(new SimpleCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                FirebaseAuth.getInstance().signOut();


                Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
            }
        })
                .normalImageRes(R.drawable.ic_icon));

        bmb.addBuilder(new SimpleCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
            }
        })
                .normalImageRes(R.drawable.ic_twitter));

        bmb.addBuilder(new SimpleCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent = new Intent(getBaseContext(),CActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
            }
        })
                .normalImageRes(R.drawable.ic_heart_outline));

        bmb.addBuilder(new SimpleCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
            }
        })
                .normalImageRes(R.drawable.ic_toc_blue_grey_300_24dp));
/*
        try {
            Glide.with(this).load(R.drawable.health).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {e.printStackTrace();  }
*/

        }

    private void prepareRecycler() {

            mainAdapter = new MainAdapter(albumList, this);
            recyclerView.setLayoutManager( new GridLayoutManager(this, 2));
            recyclerView.setAdapter(mainAdapter);


    }

    private void prepareAlbums() {

        albumList=Utils.albumList();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
/*   private void initCollapsingToolbar() {

        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");
        AppBarLayout appBarLayout =  findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                    scrollRange = appBarLayout.getTotalScrollRange();


                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

    }

*/
}

