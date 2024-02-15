package com.example.orderapplication.order_feature.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.orderapplication.order_feature.presentation.state.ProductListItem
import com.example.orderapplication.ui.theme.orange
import com.example.orderapplication.ui.theme.white

@Composable
fun CheckoutDialog(
    onDismiss:()->Unit,
    onConfirm:()->Unit,
    selectedProducts:List<ProductListItem>
) {

    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ){
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.8f)
                .border(1.dp, color = orange, shape = RoundedCornerShape(15.dp))
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Column {
                    Text(
                        "Checkout",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Divider(modifier = Modifier.padding(10.dp))
                    if(selectedProducts.isNotEmpty()){
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier
                                .padding(top = 15.dp)
                        ){
                            items(
                                selectedProducts,
                                key = {productListItem ->
                                    productListItem.id
                                }
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        "${it.selectedAmount}x "+it.name
                                    )
                                    Text(
                                        "%.2f".format(it.pricePerAmount*it.selectedAmount)+" $"
                                    )
                                }
                            }
                        }
                    }else{
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text("Please select items to order")
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            "Total sum",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "%.2f".format(selectedProducts.sumOf { (it.selectedAmount*it.pricePerAmount).toDouble() })+" $"
                            , fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Button(
                            onClick = {
                                onDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange,
                                contentColor = white
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            shape = CircleShape
                        ){
                            Text(
                                text = "Cancel",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                        Button(
                            onClick = {
                                onConfirm()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange,
                                contentColor = white
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            shape = CircleShape
                        ){
                            Text(
                                text = "Confirm",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
            }
        }
    }

}