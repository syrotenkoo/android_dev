package com.example.catuser

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.catuser.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val progressPoint = Random.nextInt(101)
        binding.pointsProgress.setProgress(progressPoint)
        binding.pointsValue.setText("${progressPoint}/100")

        binding.saveButton.isEnabled = false

        binding.userNameText.doOnTextChanged { text, _, _, _ ->
            if(text != null) {
                binding.userName.isErrorEnabled = text.length > 40
            }

            checkButtonSave()
        }

        binding.phoneNumberText.doOnTextChanged { _, _, _, _ ->
            checkButtonSave()
        }

        binding.genderOptions.setOnCheckedChangeListener { _, _ ->
            checkButtonSave()
        }

        binding.getNotifications.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                binding.authoriazationCheckBox.isEnabled = true
                binding.offersCheckBox.isEnabled = true
            } else {
                binding.authoriazationCheckBox.isEnabled = false
                binding.offersCheckBox.isEnabled = false
            }

            checkNotification(isChecked)
        }

        binding.authoriazationCheckBox.setOnCheckedChangeListener { _, _ ->
            checkButtonSave()
        }

        binding.offersCheckBox.setOnCheckedChangeListener { _, _ ->
            checkButtonSave()
        }
    }

    private fun checkNotification(isChecked: Boolean) {
        binding.authoriazationCheckBox.isEnabled = isChecked
        binding.offersCheckBox.isEnabled = isChecked
        if(!isChecked) {
            binding.authoriazationCheckBox.isChecked = false
            binding.offersCheckBox.isChecked = false
        }
        checkButtonSave()
    }

    private fun checkUserName(): Boolean {
        return !(binding.userName.isErrorEnabled || binding.userNameText.text.isNullOrBlank())
    }

    private fun checkGenderOptions(): Boolean {
        if (binding.optionMale.isChecked || binding.optionFemale.isChecked) return true
        return false
    }

    private fun checkNotifications(): Boolean {
        if (binding.getNotifications.isChecked && binding.authoriazationCheckBox.isChecked
            || binding.getNotifications.isChecked && binding.offersCheckBox.isChecked) {
            return true
        } else if (!binding.getNotifications.isChecked) {
            return true
        }
        return false
    }

    private fun checkButtonSave() {
        binding.saveButton.isEnabled =
            checkUserName() && !binding.phoneNumberText.text.isNullOrBlank() && checkGenderOptions() && checkNotifications()
        if (binding.saveButton.isEnabled) pressButtonSave()
    }

    private fun pressButtonSave() {
        binding.saveButton.setOnClickListener {
            Toast.makeText(this, "Изменения сохранены", Toast.LENGTH_LONG).show()
        }
    }
}