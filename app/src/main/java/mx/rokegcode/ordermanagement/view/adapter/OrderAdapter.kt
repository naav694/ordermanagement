package mx.rokegcode.ordermanagement.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.item_order.view.*
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.model.data.Order
import java.text.NumberFormat
import java.util.*

class OrderAdapter(private var orderList: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false) as View
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.textDeliveryDate.text = order.deliveryDate
        holder.textProductDesc.text = order.productDescription
        holder.textPrice.text =
            NumberFormat.getCurrencyInstance(Locale.getDefault()).format(order.productPrice)
        holder.btnFinishOrder.setOnClickListener {

        }
        holder.btnViewOrderDetail.setOnClickListener {

        }
    }

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textDeliveryDate: TextView = view.textDeliveryDate
        val textClientOrder: TextView = view.textClientOrder
        val textProductDesc: TextView = view.textProductDesc
        val textPrice: TextView = view.textPrice
        val btnViewOrderDetail: MaterialButton = view.btnViewOrderDetail
        val btnFinishOrder: MaterialButton = view.btnFinishOrder
    }
}