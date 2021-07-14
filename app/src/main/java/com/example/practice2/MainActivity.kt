package com.example.practice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice2.databinding.ActivityMainBinding

data class BusinessCard(val name:String,val contents:String, val img : Int)

class MainActivity : AppCompatActivity() {

    var businessCardArrayList = ArrayList<BusinessCard>()

    private lateinit var customAdapter : CustomAdapter
    //private lateinit var customAdapter2 : CustomAdapter2
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        for(x in 0..30)
        {
            businessCardArrayList.add(BusinessCard("WHAT WOULD YOU DO?(feat. Pink Sweat$)","HONNE",R.drawable.music1))
            businessCardArrayList.add(BusinessCard("Understand(Feat. GIST)","MELOH",R.drawable.music2))
            businessCardArrayList.add(BusinessCard("Good News","Mac Miller",R.drawable.music3))
            businessCardArrayList.add(BusinessCard("LONELY DIVER","Zior Park",R.drawable.music4))
            businessCardArrayList.add(BusinessCard("Blueline","트웰브(twlv)",R.drawable.music5))
            businessCardArrayList.add(BusinessCard("Tommy Lee(feat.Post Malone)","Tyla Yaweh",R.drawable.music6))
            businessCardArrayList.add(BusinessCard("See You Again(feat. Charlie Puth) ","Wiz Khalifa",R.drawable.music7))
        }

        customAdapter = CustomAdapter(this,businessCardArrayList)
        //customAdapter2 = CustomAdapter2(this,businessCardArrayList)
        binding.listviewMain.adapter=customAdapter
        //binding.recyclerview.adapter=customAdapter2
    }
}