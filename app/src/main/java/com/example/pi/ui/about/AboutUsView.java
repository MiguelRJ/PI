package com.example.pi.ui.about;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.example.pi.R;
import com.example.pi.ui.base.BaseFragment;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

/**
 * Created by
 *
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 */
public class AboutUsView extends BaseFragment {

    public static final String TAG = "AboutUsView";
    private AboutView aboutView;

    public static Fragment newInstance(Bundle bundle){
        AboutUsView aboutUsView = new AboutUsView();
        if (bundle != null) {
            aboutUsView.setArguments(bundle);
        }
        return aboutUsView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutView = AboutBuilder.with(getActivity())
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
                .addLinkedInLink("miguelrodríguezjiménez")
                .addFiveStarsAction()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_about_us, container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().addContentView(aboutView, aboutView.getLayoutParams());
    }
}
