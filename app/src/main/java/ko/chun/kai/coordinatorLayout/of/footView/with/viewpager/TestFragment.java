package ko.chun.kai.coordinatorLayout.of.footView.with.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin on 2016/7/7.
 */
public class TestFragment extends Fragment {

    public static TestFragment newInstance(String str){
        TestFragment testFragment = new TestFragment();
        Bundle data = new Bundle();
        data.putString("str", str);
        testFragment.setArguments(data);
        return testFragment;
    }

    @Bind(R.id.recyclerView)RecyclerView recyclerView;

    String str = null;
    String[] data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        str = getArguments() != null ? getArguments().getString("str") : "NULL";
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("onCreateView", str);
        View view = inflater.inflate(R.layout.test, null);
        ButterKnife.bind(this, view);

        data = new String[50];
        for(int i=0;i<data.length;i++){
            data[i]= "Index:" + i ;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new Adapter());

        return view;
    }

    TextView tv;
    BottomToUpAnimation bottomToUpAnimation;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        tv = new TextView(view.getContext());
        tv.setLayoutParams(lp);
        tv.setPadding(64, 64, 64, 64);
        tv.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.black));
        ((ViewGroup)view).addView(tv);
        bottomToUpAnimation = new BottomToUpAnimation(tv);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    bottomToUpAnimation.UpToBottom();
                } else {
                    bottomToUpAnimation.BottomToUp();
                }
            }
        });
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{

        public AdapterHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private class Adapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            TextView tv = new TextView(viewGroup.getContext());
            tv.setPadding(32,32,32,32);
            return new AdapterHolder(tv);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            ((TextView)holder.itemView).setText("position: " + position);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }
    }

}
