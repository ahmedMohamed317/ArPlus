package com.example.ar_task.util.adaptive

import androidx.compose.runtime.Composable
import com.example.ar_task.util.screen.ScreenClassifier
import com.example.ar_task.util.screen.WindowSizeClass


@Composable
fun ScreenClassifier.toAdaptiveLayoutScreenType(articleSelected : Boolean) : AdaptiveLayoutScreenType {
    return when {
        this is ScreenClassifier.FullyOpened && width.sizeClass == WindowSizeClass.Expanded -> {
            AdaptiveLayoutScreenType.ListOneThirdAndDetailThirds
        }
        this is ScreenClassifier.FullyOpened && width.sizeClass == WindowSizeClass.Medium && height.sizeClass != WindowSizeClass.Compact -> {
            AdaptiveLayoutScreenType.ListHalfAndDetailHalf
        }
        this is ScreenClassifier.FullyOpened && !articleSelected -> {
            AdaptiveLayoutScreenType.ScreenOnly
        }
        this is ScreenClassifier.HalfOpened.BookMode -> {

            AdaptiveLayoutScreenType.ListHalfAndDetailHalf
        }
        this is ScreenClassifier.HalfOpened.TableTopMode -> {

            AdaptiveLayoutScreenType.ListAndDetailStacked
        }
        else -> {
            AdaptiveLayoutScreenType.ScreenOnly
        }
    }
}