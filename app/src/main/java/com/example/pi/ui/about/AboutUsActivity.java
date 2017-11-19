package com.example.pi.ui.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.pi.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

/**
 * Created by
 *
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 */
public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        AboutView view = AboutBuilder.with(this)
                .setBackgroundColor(R.color.colorPrimaryLight)
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName("Miguel Rodriguez Jimenez")
                .setSubTitle("Promador multiplataforma")
                .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("")
                .addGitHubLink("MiguelRJ")
                .addInstagramLink("migue.rj")
                .addLinkedInLink("a")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
        addContentView(view, view.getLayoutParams());
    }
}
