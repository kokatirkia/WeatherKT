package com.example.weather.ui.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weather.R

@Composable
fun Tabs(
    selectedTabIndex: Int,
    onSelectedIndexChanged: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = Color.White
    ) {
        for (i in 0..1) {
            Tab(
                selected = selectedTabIndex == i,
                onClick = { onSelectedIndexChanged(i) },
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .padding(horizontal = 16.dp, vertical = 2.dp)
            ) {
                if (i == 0) Text(text = stringResource(R.string.current))
                else Text(text = stringResource(R.string.five_days))
            }
        }
    }
}