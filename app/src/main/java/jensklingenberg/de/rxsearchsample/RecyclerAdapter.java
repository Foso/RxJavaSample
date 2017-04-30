package jensklingenberg.de.rxsearchsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jens on 01.02.17.
 */
public class RecyclerAdapter
    extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

  private static final String TAG = RecyclerAdapter.class.getSimpleName();

  private Context context;
  private List<String> list;

  public RecyclerAdapter(Context context) {
    this.context = context;
    this.list = new ArrayList<>();
  }

  public void addItem(String p) {
    list.add(p);
    notifyDataSetChanged();
  }

  public void addList(List<String> p) {
    list.addAll(p);
    notifyDataSetChanged();
  }

  public void clear() {
    list = new ArrayList<>();
    notifyDataSetChanged();
  }

  private Context getContext() {
    return context;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = getContext();
    LayoutInflater inflater = LayoutInflater.from(context);

    View view = inflater.inflate(R.layout.grid_list_item, parent, false);
    ButterKnife.bind(this, view);

    ViewHolder viewHolder = new ViewHolder(view);

    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    String item = list.get(position);

    holder.txtChannelTitle.setText(item);
    Log.i(TAG, "onBindViewHolder: " + item);
    String test = "";
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    // Todo Butterknife bindings

    @BindView(R.id.tvEpisodeName) TextView txtChannelTitle;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}