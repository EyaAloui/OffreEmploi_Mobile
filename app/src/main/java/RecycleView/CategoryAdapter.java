package RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;


import com.example.myproj.R;

import java.util.List;




public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {

    private final List<String> items;
    private final ClickListener clickListener;
    private int selectedPosition = -1;
    private int lastSelectedPosition = -1;
    private Context context;

    public CategoryAdapter(List<String> items, ClickListener clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView catTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catTxt = itemView.findViewById(R.id.catTxt);  // Ensure this ID matches your XML layout
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(holder.getAdapterPosition());  // Use holder.getAdapterPosition() here
        holder.catTxt.setText(item);

        if (selectedPosition == holder.getAdapterPosition()) {
            holder.catTxt.setBackgroundResource(R.drawable.blue_full_corner);
            holder.catTxt.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.catTxt.setBackgroundResource(R.drawable.grey_full_corner_bg);
            holder.catTxt.setTextColor(context.getResources().getColor(R.color.black));
        }

        holder.itemView.setOnClickListener(v -> {
            lastSelectedPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();  // Use holder.getAdapterPosition() here
            notifyItemChanged(lastSelectedPosition);
            notifyItemChanged(selectedPosition);
            clickListener.onClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ClickListener {
        void onClick(String category);
    }
}
