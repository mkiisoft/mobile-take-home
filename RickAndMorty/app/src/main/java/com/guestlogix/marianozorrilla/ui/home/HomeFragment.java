package com.guestlogix.marianozorrilla.ui.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guestlogix.marianozorrilla.R;
import com.guestlogix.marianozorrilla.data.listener.ItemListener;
import com.guestlogix.marianozorrilla.data.model.episode.EpisodeItem;
import com.guestlogix.marianozorrilla.ui.episode.EpisodeActivity;
import com.guestlogix.marianozorrilla.ui.home.adapter.EpisodeAdapter;
import com.guestlogix.marianozorrilla.ui.home.presenter.HomeFragmentPresenter;
import com.guestlogix.marianozorrilla.ui.home.presenter.HomeFragmentView;
import com.guestlogix.marianozorrilla.ui.presenter.MvpFragment;
import com.guestlogix.marianozorrilla.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HomeFragment extends MvpFragment<HomeFragmentPresenter> implements HomeFragmentView,
        ItemListener<EpisodeItem>, Constants {

    private HomeFragmentPresenter presenter;

    private LinearLayout loading;
    private LinearLayout dataNetwork;

    private ImageView dataNetworkImage;
    private TextView dataNetworkText;

    private TextView loadingText;

    private EpisodeAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        presenter = new HomeFragmentPresenter(this, view.getContext());
        setPresenter(presenter);

        loading = view.findViewById(R.id.loading);
        loadingText = view.findViewById(R.id.loading_text);
        dataNetwork = view.findViewById(R.id.data_network);
        dataNetwork.setOnClickListener(click -> presenter.getData());

        dataNetworkImage = view.findViewById(R.id.data_network_image);
        dataNetworkText = view.findViewById(R.id.data_network_text);

        int itemsSpan = 1;

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            itemsSpan = 2;
        }

        RecyclerView recycler = view.findViewById(R.id.recycler_episodes);

        recycler.setLayoutManager(new GridLayoutManager(getActivity(), itemsSpan));
        adapter = new EpisodeAdapter(this);
        recycler.setAdapter(adapter);

        presenter.init();
    }

    @Override
    public void setHomeListContent(List<EpisodeItem> episodes) {
        loading.setVisibility(GONE);
        presenter.setLoading(false);
        adapter.setEpisodes(episodes);
    }

    @Override
    public void handleError(Exception exception) {
        loading.setVisibility(GONE);
        presenter.setLoading(false);
        dataNetworkImage.setImageResource(R.drawable.data_error);
        dataNetworkText.setText(R.string.data_error);
        dataNetwork.setVisibility(VISIBLE);
    }

    @Override
    public void noNetwork() {
        presenter.setLoading(false);
        dataNetworkImage.setImageResource(R.drawable.no_data);
        dataNetworkText.setText(R.string.no_network);
        dataNetwork.setVisibility(VISIBLE);
    }

    @Override
    public void loading() {
        adapter.setEpisodes(new ArrayList<>());
        dataNetwork.setVisibility(GONE);
        loading.setVisibility(VISIBLE);
    }

    @Override
    public void notifyUpdates(String update) {
        loadingText.setText(update);
    }

    @Override
    public void click(EpisodeItem item) {
        Intent intent = new Intent(getActivity(), EpisodeActivity.class);
        intent.putExtra(EXTRA_ID, item.getId());
        Objects.requireNonNull(getActivity()).startActivity(intent);
    }
}
