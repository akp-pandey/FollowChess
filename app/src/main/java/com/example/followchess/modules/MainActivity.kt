package com.example.followchess.modules

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.followchess.R
import com.example.followchess.utility.isInternetAvailable
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var rvItem:RecyclerView
    lateinit var recyclerViewAdapter:ItemRecyclerAdapter
    lateinit var progressBar: ProgressBar
    lateinit var tvNoInternet:TextView
    var isHorizontal:Boolean=true

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
        setUpEvents()

    }

    private fun setUpEvents() {
        //Calling the API from viewModel
        if (this.isInternetAvailable()){
            tvNoInternet.visibility=View.GONE
            viewModel.getConfigJsonList()
        }else{
            tvNoInternet.visibility=View.VISIBLE
        }


        //Observing the API Call
        viewModel.isLoading.observe(this,{
            if (it){
                progressBar.visibility=View.VISIBLE
            }else{
                progressBar.visibility=View.GONE
            }
        })

        //Checking for orientation and changing layoutManager of recyclerView
        if (resources.configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){
            isHorizontal=false
            val gridLayoutManager=GridLayoutManager(this,3)
            rvItem.layoutManager=gridLayoutManager
        }else{
            isHorizontal=true
            rvItem.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        }

        //Setting adapter for recyclerView
        recyclerViewAdapter=ItemRecyclerAdapter(isHorizontal)

        //Observing api response and submitting to listAdapter of RecyclerView
        viewModel.configJsonArrayList.observe(this, {
            recyclerViewAdapter.submitList(it.trns)
            rvItem.adapter=recyclerViewAdapter

        })

    }

    private fun setUp() {
        //Getting id and initializing the variables for views
        progressBar=findViewById(R.id.progressBar)
        rvItem=findViewById(R.id.rvItems)
        tvNoInternet=findViewById(R.id.noInternet)

        //Creating instance of ViewModel
        viewModel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }
}