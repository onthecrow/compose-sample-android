package com.onthecrow.compose_state_playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onthecrow.compose_state_playground.ui.theme.ComposestateplaygroundTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposestateplaygroundTheme {
                val stateCompound = viewModel.compoundUIState.collectAsState()
                val stateN1 = viewModel.uiStatePieceN1.collectAsState()
                val stateN2 = viewModel.uiStatePieceN2.collectAsState()
                val stateN3 = viewModel.uiStatePieceN3.collectAsState()
                val stateN4 = viewModel.uiStatePieceN4.collectAsState()
                val stateN5 = viewModel.uiStatePieceN5.collectAsState()
                val stateN6 = viewModel.uiStatePieceN6.collectAsState()
                val stateN7 = viewModel.uiStatePieceN7.collectAsState()
                val stateN8 = viewModel.uiStatePieceN8.collectAsState()
                val stateN9 = viewModel.uiStatePieceN9.collectAsState()
                val stateN10 = viewModel.uiStatePieceN10.collectAsState()
                val stateN11 = viewModel.uiStatePieceN11.collectAsState()
                val stateN12 = viewModel.uiStatePieceN12.collectAsState()

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = spacedBy(8.dp, Alignment.CenterVertically),
                    horizontalArrangement = spacedBy(8.dp, Alignment.CenterHorizontally),
                    contentPadding = PaddingValues(16.dp),
                    columns = GridCells.Fixed(3),
                ) {
                    items(24, key = { it }) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = when (it) {
                                0 -> stateCompound.value.textN1.toString()
                                1 -> stateCompound.value.textN2.toString()
                                2 -> stateCompound.value.textN3.toString()
                                3 -> stateCompound.value.textN4.toString()
                                4 -> stateCompound.value.textN5.toString()
                                5 -> stateCompound.value.textN6.toString()
                                6 -> stateCompound.value.textN7.toString()
                                7 -> stateCompound.value.textN8.toString()
                                8 -> stateCompound.value.textN9.toString()
                                9 -> stateCompound.value.textN10.toString()
                                10 -> stateCompound.value.textN11.toString()
                                11 -> stateCompound.value.textN12.toString()
                                12 -> stateN1.value.toString()
                                13 -> stateN2.value.toString()
                                14 -> stateN3.value.toString()
                                15 -> stateN4.value.toString()
                                16 -> stateN5.value.toString()
                                17 -> stateN6.value.toString()
                                18 -> stateN7.value.toString()
                                19 -> stateN8.value.toString()
                                20 -> stateN9.value.toString()
                                21 -> stateN10.value.toString()
                                22 -> stateN11.value.toString()
                                23 -> stateN12.value.toString()
                                else -> ""
                            },
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
