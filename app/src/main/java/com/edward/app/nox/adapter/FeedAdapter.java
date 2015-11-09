package  com.edward.app.nox.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.edward.app.nox.R;
import com.edward.app.nox.utility.Utils;
import com.edward.app.nox.view.SquaredImageView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ANIMATED_ITEMS_COUNT = 2;
    private static final String TAG ="Edward";
    private Context context;
    private int lastAnimatedPosition = -1;
    private int itemsCount = 2;

    public FeedAdapter(Context context) {



        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG,"Start Bind View Holder onCreateViewHolder#####################");

        final View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new CellFeedViewHolder(view);
    }

    private void runEnterAnimation(View view, int position) {
        if (position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        Log.d("Edward", "Start Bind View Holder#####################");


        runEnterAnimation(viewHolder.itemView, position);
        CellFeedViewHolder holder = (CellFeedViewHolder) viewHolder;

        holder.ivFeedChart.setData(drewChart());

        holder.ivFeedChart.invalidate();


        if (position % 2 == 0) {
            holder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_1);
            holder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_1);
        } else {
            holder.ivFeedCenter.setImageResource(R.drawable.img_feed_center_2);
            holder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_2);
        }
    }

    /*
    * 沒有設置itemCount系統不會自動trigger onCreateViewHolder function
    */
    @Override
    public int getItemCount() {
        return itemsCount;
    }

    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.ivFeedCenter)
        SquaredImageView ivFeedCenter;
        @InjectView(R.id.ivFeedBottom)
        ImageView ivFeedBottom;
        @InjectView(R.id.ivFeedChart)
        LineChart ivFeedChart;

        public CellFeedViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public void updateItems() {
        itemsCount = 10;
        notifyDataSetChanged();
    }


    public LineData drewChart(){
        String dataset_label1 = "Company 1";
        ArrayList<Entry> yVals1 = new ArrayList<>();
        yVals1.add(new Entry(100, 0));
        yVals1.add(new Entry(105, 1));
        yVals1.add(new Entry(100, 2));
        yVals1.add(new Entry(250, 3));
        LineDataSet dataSet1 = new LineDataSet(yVals1, dataset_label1);
        dataSet1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        dataSet1.setLineWidth(20);
        dataSet1.setCircleSize(10);
        dataSet1.setValueTextSize(15);

        String dataset_label2 = "Company 2";
        ArrayList<Entry> yVals2 = new ArrayList<>();
        yVals2.add(new Entry(80, 0));
        yVals2.add(new Entry(150, 1));
        yVals2.add(new Entry(170, 2));
        yVals2.add(new Entry(200, 3));
        LineDataSet dataSet2 = new LineDataSet(yVals2, dataset_label2);
        dataSet2.setLineWidth(100);
        dataSet2.setCircleSize(10);
        dataSet2.setValueTextSize(15);

        List<LineDataSet> dataSetList = new ArrayList<>();
        dataSetList.add(dataSet1);
        dataSetList.add(dataSet2);

        List<String> xVals = new ArrayList<>();
        xVals.add("Q1");
        xVals.add("Q2");
        xVals.add("Q3");
        xVals.add("Q4");
        return new LineData(xVals, dataSetList);



    }
}