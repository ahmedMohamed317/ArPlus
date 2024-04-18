package com.example.ar_task.presentation.favorite


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ar_task.domain.model.articles.ArticlesModel
import com.example.ar_task.presentation.search.LoadImage
import com.example.ar_task.util.connectivity.ConnectivityObserver

import kotlinx.coroutines.flow.StateFlow
@Composable
fun FavoriteScreen(
    modifier: Modifier,
    connectivityStatus: StateFlow<ConnectivityObserver.Status>,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val articlesState by viewModel.allArticles.collectAsState(emptyList())
    Log.d("dpa",articlesState.toString())
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        if (articlesState.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = rememberLazyListState(),
                content = {
                    items(articlesState) { article ->
                        FavoriteArticleItem(
                            article = article,
                            onRemoveClick = { viewModel.delete(it) }
                        )
                    }
                }
            )
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "No favorite items",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }
        }

    }
}

@Composable
fun FavoriteArticleItem(
    article: ArticlesModel,
    onRemoveClick: (ArticlesModel) -> Unit
) {
    val isFavorite = true
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.title,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(6.dp))
                LoadImage(
                    imageUrl = article.urlToImage ?: "https://upload.wikimedia.org/wikipedia/commons/3/3f/Placeholder_view_vector.svg",
                    modifier = Modifier.size(34.dp),
                    contentDescription = "Article Image"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.author ?: "", fontStyle = FontStyle.Italic)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.description ?: "")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.url,
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        uriHandler.openUri(article.url)
                    }.weight(.8f),
                )
                Spacer(modifier = Modifier.width(6.dp))

                IconButton(
                    onClick = {
                        onRemoveClick(article)
                    },
                    modifier = Modifier
                        .size(18.dp)
                        .background(
                            if (isFavorite) Color.Red else Color.Transparent,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.White else Color.Gray
                    )
                }
            }
        }
    }
}
