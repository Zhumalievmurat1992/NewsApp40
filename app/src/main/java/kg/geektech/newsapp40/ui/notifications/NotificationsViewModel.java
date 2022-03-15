package kg.geektech.newsapp40.ui.notifications;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.geektech.newsapp40.databinding.ItemNewsBinding;
import kg.geektech.newsapp40.models.News;
import kg.geektech.newsapp40.ui.news.NewsAdapter;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}