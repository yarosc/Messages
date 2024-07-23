package test.muzz.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainView() {

    Scaffold(
        topBar = {
                 //TODO TopBar
        },
        bottomBar = {
                 //TODO BottomBar
        },
        content = { paddingValues ->
            Column(
                Modifier.fillMaxSize().padding(paddingValues)
            ) {

            }
        }
    )

}

@Preview
@Composable
fun MainViewPreview() {
    MainView()
}