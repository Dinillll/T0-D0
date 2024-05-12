package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.R;
import com.example.todoapp.ToDoActivity;

import java.util.List;

import model.IModel;
import model.ToDoItem;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {


    private List<ToDoItem> items;
    private int layoutResId;
    private MainActivity activity;
    private IModel model;


    public TodoAdapter(List<ToDoItem> items, int layoutResId, MainActivity activity, IModel model) {
        this.items = items;
        this.layoutResId = layoutResId;
        this.activity = activity;
        this.model=model;

    }

    public Context getContext() {
        return activity;
    }

    public void removeItem(int position) {
        ToDoItem tdi = items.get(position);
        items.remove(tdi);
        notifyItemRemoved(position);
        model.removeToDoItem(tdi);
    }

    public void editItem(int position) {
        ToDoItem tdi = items.get(position);
        Intent intent = new Intent(activity, ToDoActivity.class);
        intent.putExtra("tdi",tdi);
        intent.putExtra("index",position);
        activity.startActivityForResult(intent,MainActivity.RQC_EDIT);
        notifyItemChanged(position);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layoutResId, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoItem tdi = items.get(position);
        holder.tvName.setText(tdi.getName());
        holder.tvDate.setText(tdi.getYear() + "." + tdi.getMonth() + "." + tdi.getDay() + ".");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDate;
        private TextView tvUrgency;
        private ImageView ivUrgency;
        private RadioButton rbNemF;
        private RadioButton rbKicsitF;
        private RadioButton rbNagyonF;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
