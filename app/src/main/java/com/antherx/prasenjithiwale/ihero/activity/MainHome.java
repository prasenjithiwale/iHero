package com.antherx.prasenjithiwale.ihero.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antherx.prasenjithiwale.ihero.Purchase;
import com.antherx.prasenjithiwale.ihero.R;
import com.antherx.prasenjithiwale.ihero.SwipeCard;
import com.antherx.prasenjithiwale.ihero.YoutubeVideos;
import com.antherx.prasenjithiwale.ihero.important.Album;
import com.antherx.prasenjithiwale.ihero.important.AlbumAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.Profile;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.intellij.lang.annotations.JdkConstants;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainHome extends AppCompatActivity {

    Profile profile;
    public RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private List<Album> albumList;
    private Context myContext;
    public TextView NameLove;
    private TextView IdLove;
    public String name;
    public String id;
    private CircleImageView circleImageViewOnHome;
    private String profilePictureURL;
    ImageView loveProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NameLove = (TextView) findViewById(R.id.love_music);
        loveProf = (ImageView) findViewById(R.id.love_prof_img);

        circleImageViewOnHome = (CircleImageView) findViewById(R.id.imgProfilePictureOnHome);

        initCollapsingToolbar();

        profile = Profile.getCurrentProfile();
        if (profile != null) {
            name = profile.getName();
            id = profile.getId();
            NameLove.setText(profile.getFirstName());

            profilePictureURL = "http://graph.facebook.com/" + profile.getId() + "/picture?type=large";
            Glide.with(this).load(profilePictureURL)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(loveProf);

        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        albumAdapter = new AlbumAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(albumAdapter);
        prepareAlbums();
        try {
            Glide.with(this).load("https://s-media-cache-ak0.pinimg.com/236x/e8/96/fc/e896fc42b6911272129724e8c7383489.jpg").into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    if(profile!=null){
                    collapsingToolbar.setTitle(profile.getName());
                    Glide.with(getApplicationContext()).load(profilePictureURL)
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(circleImageViewOnHome);
                    }else{
                        collapsingToolbar.setTitle("User Unknown");
                        circleImageViewOnHome.setImageResource(R.drawable.com_facebook_profile_picture_blank_square);
                    }
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    circleImageViewOnHome.setImageResource(0);
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {

        int[] covers = new int[]{
                R.drawable.d1, R.drawable.d2, R.drawable.d4, R.drawable.d5, R.drawable.d6, R.drawable.d7,
                R.drawable.d8, R.drawable.d9, R.drawable.d10, R.drawable.d3, R.drawable.step2};

        String[] cover = new String[]{
                "http://3.bp.blogspot.com/-cL-EikqaypM/UzBFtcSPMUI/AAAAAAAAKnk/V6t5Gpnv8pA/s1600/Indian+Beautiful+Girls+Wallpapers+Free+Download+2.jpg",
                "http://img.izismile.com/img/img6/20130304/640/naturally_beautiful_girls_640_36.jpg",
                "http://cdn2.stylecraze.com/wp-content/uploads/2013/10/Priyanka-Chopra1.jpg",
                "https://www.funnypica.com/wp-content/uploads/2015/05/TOP-50-Beautiful-Girls-Girl-2-of-50.jpg",
                "http://ipl7-live.com/wp-content/uploads/2017/05/beautiful-girls-wallpapers-beautiful-girls-wallpapers.jpg",
                "http://4.bp.blogspot.com/-2cy0CzAhDSs/UzBFo2-CTZI/AAAAAAAAKmA/ngK__1tV4ms/s1600/Beautiful+Indian+Girls+Wallpapers+for+Desktop+Picture+9.jpg"
        };


        Album a = new Album("Prasenjit", 13, cover[0]);
        albumList.add(a);

        a = new Album("Prasenjit", 8, cover[1]);
        albumList.add(a);

        a = new Album("Prasenjit", 11, cover[2]);
        albumList.add(a);

        a = new Album("Prasenjit", 12, cover[3]);
        albumList.add(a);

        a = new Album("Prasenjit", 14, cover[4]);
        albumList.add(a);

        a = new Album("Prasenjit", 1, cover[5]);
        albumList.add(a);



        albumAdapter.notifyDataSetChanged();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this, MainLogin.class);
                startActivity(intent);
                return true;
            case R.id.purchase:
                StyleableToast purchaseToast = new StyleableToast(this, "Purchasing...", Toast.LENGTH_SHORT);
                purchaseToast.setBackgroundColor(getResources().getColor(R.color.purchase_background_color));
                purchaseToast.setTextColor(Color.BLACK);
                purchaseToast.setBoldText();
                purchaseToast.setMaxAlpha();
                purchaseToast.show();
                Intent purchaseIntent = new Intent(this, Purchase.class);
                startActivity(purchaseIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void cameraView(View view) {

        Intent cameraTransfer = new Intent(this, SwipeCard.class);
        startActivity(cameraTransfer);
    }

    public void playVideo(View view){

        Intent videoTransfer = new Intent(this, YoutubeVideos.class);
        startActivity(videoTransfer);
        StyleableToast purchaseToast = new StyleableToast(this, "VideoView", Toast.LENGTH_SHORT);
        purchaseToast.setBackgroundColor(getResources().getColor(R.color.purchase_background_color));
        purchaseToast.setTextColor(Color.BLACK);
        purchaseToast.setBoldText();
        purchaseToast.setMaxAlpha();
        purchaseToast.show();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}



