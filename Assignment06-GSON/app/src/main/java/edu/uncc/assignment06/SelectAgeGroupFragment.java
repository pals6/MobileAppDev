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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uncc.assignment06.databinding.FragmentSelectAgeGroupBinding;
import edu.uncc.assignment06.models.AgeGroup;
import edu.uncc.assignment06.models.Mood;
import edu.uncc.assignment06.models.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SelectAgeGroupFragment extends Fragment {

    public SelectAgeGroupFragment() {
        // Required empty public constructor
    }

    FragmentSelectAgeGroupBinding binding;

    ArrayList<AgeGroup> mAgeGroups = new ArrayList<>();
    ArrayAdapter<AgeGroup> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectAgeGroupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Select Age Group");
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.sendSelectedAgeGroup(mAgeGroups.get(position));
            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelAgeGroupSelection();
            }
        });

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mAgeGroups);
        binding.listView.setAdapter(adapter);


        getAgeGroups();
    }

    private final OkHttpClient client = new OkHttpClient();

    private void getAgeGroups(){
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/mood/age-groups")
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
                    mAgeGroups.clear();

                    try {
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray ageGroupsJsonArray = jsonObject.getJSONArray("moods");
                        for (int i = 0; i < ageGroupsJsonArray.length(); i++) {
                            JSONObject ageGroupJsonObject = ageGroupsJsonArray.getJSONObject(i);
                            AgeGroup ageGroup = new AgeGroup(ageGroupJsonObject);
                            mAgeGroups.add(ageGroup);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    Log.d("demo", "onResponse: " + body);

                } else {
                    Log.d("demo", "onResponse: Not Successful");
                }
            }
        });
    }



    SelectAgeGroupListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectAgeGroupListener) {
            mListener = (SelectAgeGroupListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SelectAgeGroupListener");
        }
    }

    interface SelectAgeGroupListener {
        void sendSelectedAgeGroup(AgeGroup ageGroup);
        void cancelAgeGroupSelection();
    }
}