package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.readersgang.R;

import java.util.List;

import models.Item;
import models.VolumeInfo;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.CustomViewHolder> {
    private List<Item> volumeInfo;

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        VolumeInfo volumeInfo = this.volumeInfo.get(position).getVolumeInfo();
        holder.bookTitle.setText("Title: " + volumeInfo.getTitle());
        holder.publisher.setText("Publisher: " + volumeInfo.getPublisher());
        if (volumeInfo.getAuthors() != null && !volumeInfo.getAuthors().isEmpty()) {
            holder.bookAuthor.setText("Author " + volumeInfo.getAuthors().get(0));
        }
        holder.publishedDate.setText("Published date: " + volumeInfo.getPublishedDate());
        holder.description.setText("Description: " + volumeInfo.getDescription());
    }

    @Override
    public int getItemCount() {
        return volumeInfo == null ? 0 : volumeInfo.size();
    }

    public void setVolumeInfo(final List<Item> volumeInfo) {
        this.volumeInfo = volumeInfo;
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, bookAuthor, publisher, publishedDate, description;

        CustomViewHolder(View view) {
            super(view);
            bookTitle = view.findViewById(R.id.bookTitle);
            bookAuthor = view.findViewById(R.id.bookAuthor);
            publisher = view.findViewById(R.id.publisher);
            publishedDate = view.findViewById(R.id.publishedDate);
            description = view.findViewById(R.id.description);
        }
    }
}
