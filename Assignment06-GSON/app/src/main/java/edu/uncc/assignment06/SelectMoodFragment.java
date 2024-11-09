package edu.uncc.assignment06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.uncc.assignment06.databinding.FragmentSelectMoodBinding;
import edu.uncc.assignment06.databinding.ListItemMoodBinding;
import edu.uncc.assignment06.models.Mood;
import edu.uncc.assignment06.models.MoodsResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SelectMoodFragment extends Fragment {
    public SelectMoodFragment() {
        // Required empty public constructor
    }

    FragmentSelectMoodBinding binding;
    ArrayList<Mood> mMoods = new ArrayList<>();
    MoodsAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectMoodBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Mood");
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelMoodSelection();
            }
        });

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.sendSelectedMood(mMoods.get(position));

            }
        });

        adapter = new MoodsAdapter(getActivity(), R.layout.list_item_mood, mMoods);
        binding.listView.setAdapter(adapter);
        getMoods();
    }

    private final OkHttpClient client = new OkHttpClient();

    private void getMoods(){
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/mood/moods")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String body = response.body().string();
                    mMoods.clear();
                    Gson gson = new Gson();
                    MoodsResponse moodsResponse = gson.fromJson(body, MoodsResponse.class);
                    mMoods.addAll(moodsResponse.getMoods());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    Log.d("demo", "onResponse: Not Successful");
                }
            }
        });
    }

    class MoodsAdapter extends ArrayAdapter<Mood>{

        public MoodsAdapter(@NonNull Context context, int resource, @NonNull List<Mood> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ListItemMoodBinding moodBinding;
            if(convertView == null){
                moodBinding = ListItemMoodBinding.inflate(getLayoutInflater(), parent, false);
                convertView = moodBinding.getRoot();
                convertView.setTag(moodBinding);
            } else {
                moodBinding = (ListItemMoodBinding) convertView.getTag();
            }
            Mood mood = getItem(position);

            moodBinding.textViewMood.setText(mood.getName());

            Picasso.get().load(mood.getImgUrl()).into(moodBinding.imageViewMood);

            return convertView;
        }
    }

    SelectMoodListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectMoodListener) {
            mListener = (SelectMoodListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SelectMoodListener");
        }
    }

    interface SelectMoodListener {
        void sendSelectedMood(Mood mood);
        void cancelMoodSelection();
    }
}