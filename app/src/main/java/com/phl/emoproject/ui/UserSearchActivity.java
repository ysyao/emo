package com.phl.emoproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.phl.emoproject.R;
import com.phl.emoproject.core.BaseAsyncHttpResponseHandler;
import com.phl.emoproject.pojo.SearchResult;
import com.phl.emoproject.utils.AsyncHttpClientUtils;

import org.apache.http.Header;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_user_search)
public class UserSearchActivity extends RoboActionBarActivity {
    @InjectView(R.id.result)
    TextView result;
    @InjectView(R.id.indicator)
    View indicator;
    @InjectView(R.id.search_input)
    EditText searchInput;
    @InjectView(R.id.search_button)
    View searchBtn;
    @InjectView(R.id.back_button)
    View backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = searchInput.getText().toString();
                indicator.setVisibility(View.VISIBLE);
                AsyncHttpClientUtils.postSearchUserScope(UserSearchActivity.this, name, new SearchName());
            }
        });

    }

    private class SearchName extends BaseAsyncHttpResponseHandler<SearchResult> {
        public SearchName() {
            super();
            setType(new TypeToken<SearchResult>(){}.getType());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, SearchResult searchResult) {
            indicator.setVisibility(View.INVISIBLE);
            if (searchResult.getMessage().getReturnCode() == 0) {
                String str = searchResult.getJsonObject();
                result.setTag(str);
                String[] strs = str.split("#");
                result.setText(strs[2]);
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String str = (String)view.getTag();
                        Intent intent = new Intent();
                        intent.putExtra("user", str);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            indicator.setVisibility(View.INVISIBLE);
        }
    }
}
