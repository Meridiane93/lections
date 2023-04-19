package com.meridiane.magiccasino

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import com.meridiane.magiccasino.databinding.ActivityAuthorizationBinding

class AuthoriztionAct : AppCompatActivity() {

    private lateinit var binding : ActivityAuthorizationBinding
    private lateinit var ccp: CountryCodePicker
    private var phone = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ccp = binding.ccp

        ccp.registerCarrierNumberEditText(binding.edPhoneNumber)

        ccp.setPhoneNumberValidityChangeListener { checkNumber(it) }

        binding.btByPhoneFalse.setOnClickListener { changeButton(1) }
        binding.imageMailFalse.setOnClickListener { changeButton(10) }

        binding.imageCheckFalse.setOnClickListener {
            binding.imageCheckTrue.visibility = View.VISIBLE
        }
        binding.imageCheckTrue.setOnClickListener {
            binding.imageCheckTrue.visibility = View.GONE
        }

        binding.btToTheGame.setOnClickListener {
            if ( phone != ""){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }else Toast.makeText(this,"Проверьте правильность номера",Toast.LENGTH_LONG).show()
        }
    }

    private fun checkNumber(boolean: Boolean) = with(binding){
        if(!boolean){
            imageCheckFalseNumber.visibility = View.VISIBLE
            imageCheckTrueNumber.visibility = View.GONE
        }
        else {
            imageCheckFalseNumber.visibility = View.GONE
            imageCheckTrueNumber.visibility = View.VISIBLE
            phone = binding.edPhoneNumber.toString()
        }
    }

    private fun changeButton(int:Int) = with(binding){
        if(int == 1){
            btByPhoneFalse.visibility = View.GONE
            btByEmailTrue.visibility = View.GONE
            edMail.visibility = View.GONE

            imageSlesh.visibility = View.VISIBLE
            ccp.visibility = View.VISIBLE
            edPhoneNumber.visibility = View.VISIBLE
            imageCheckFalseNumber.visibility = View.VISIBLE
        } else{
            btByPhoneFalse.visibility = View.VISIBLE
            btByEmailTrue.visibility = View.VISIBLE
            edMail.visibility = View.VISIBLE

            imageSlesh.visibility = View.GONE
            ccp.visibility = View.GONE
            edPhoneNumber.visibility = View.GONE
            imageCheckFalseNumber.visibility = View.GONE
            imageCheckTrueNumber.visibility = View.GONE
        }
    }

}