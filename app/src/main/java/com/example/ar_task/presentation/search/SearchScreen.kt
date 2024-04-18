package com.example.ar_task.presentation.search
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ar_task.util.collectAsSharedFlowWithLifeCycle
import com.example.ar_task.R
import com.example.ar_task.domain.model.articles.ArticlesModel
import com.example.ar_task.presentation.favorite.FavoriteViewModel
import com.example.ar_task.util.connectivity.ConnectivityObserver
import com.example.ar_task.util.screen.sdp
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SearchScreen(
    modifier: Modifier,
    connectivityStatus: StateFlow<ConnectivityObserver.Status>,
    viewModel: SearchViewModel = hiltViewModel(),
    favViewModel: FavoriteViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val connectionStatus = connectivityStatus.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val noInternetLottie by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_internet))
    val logoAnimationState = animateLottieCompositionAsState(
        composition = noInternetLottie,
        isPlaying = true,
        speed = 0.5f
    )


    viewModel.error.collectAsSharedFlowWithLifeCycle {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        if (connectionStatus.value == ConnectivityObserver.Status.Available) {
            var searchText by rememberSaveable { mutableStateOf("") }
            viewModel.getArticlesBySearch("")

            OutlinedTextField(
                value = searchText,
                onValueChange =
                { searchText = it
                    viewModel.getArticlesBySearch(it)
                },
                placeholder = { Text(text = "Search for articles") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.sdp, 16.sdp, 16.sdp, 6.sdp)

            )


            Spacer(modifier = Modifier.size(4.sdp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState
            ) {
                items(state.value.articles) { article ->
                    ArticleItem(article = article, favViewModel = favViewModel)
                }
            }
        } else {
            LottieAnimation(
                noInternetLottie,
                modifier = Modifier
                    .size(100.sdp)
                    .align(CenterHorizontally),
                progress = { logoAnimationState.progress },
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun ArticleItem(
    article: ArticlesModel,
    favViewModel: FavoriteViewModel
) {
    val isFavorite by remember { favViewModel.isArticleFavorite(article.title) }.collectAsState(initial = false)
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
                        if (isFavorite) {
                            favViewModel.delete(article)
                        } else {
                            favViewModel.insert(article)
                        }
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

@Composable
fun LoadImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = contentDescription,
        modifier = modifier
    )
}
