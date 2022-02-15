package ru.cft.shift2022winter.info

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import ru.cft.shift2022winter.R
import ru.cft.shift2022winter.databinding.ActivityInfoBinding
import ru.cft.shift2022winter.domain.model.Valute
import ru.cft.shift2022winter.domain.model.database.ValuteModel
import java.text.SimpleDateFormat

class InfoActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_ID = "Extra"

        fun start(context: Context, valute: ValuteModel) {
            val intent = Intent(context, InfoActivity::class.java).apply {
                putExtra(EXTRA_ID, valuteToGson(valute))
            }
            context.startActivity(intent)
        }


        private fun valuteToGson(valute: ValuteModel): String {
            return Gson().toJson(valute)
        }
    }

    private lateinit var binding: ActivityInfoBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val valuteInJson = intent.getStringExtra(EXTRA_ID)
        val valute = Gson().fromJson(valuteInJson, ValuteModel::class.java)

        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.content.button.setOnClickListener {
            val rubles = binding.content.editTextNumber.text.toString().toFloat()
            val curs = valute.value/valute.nominal
            binding.content.textResult.text = String.format("%.3f", rubles/curs) + " " + valute.charCode
        }

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = valute.name

        binding.content.textView.text = valuteToDescription(valute)
        binding.content.textConvertTo.text = getString(R.string.convert_to, valute.charCode)
    }


    @SuppressLint("SimpleDateFormat")
    private fun valuteToDescription(valute: ValuteModel): String{
        return getString(R.string.iso_code, valute.charCode) +
                getString(R.string.nominal, valute.nominal.toString())  +
                getString(R.string.value, valute.value.toString())  +
                getString(R.string.prev_value, valute.previous.toString())
    }
}