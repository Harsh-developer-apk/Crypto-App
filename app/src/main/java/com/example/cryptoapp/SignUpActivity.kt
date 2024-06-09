package com.example.cryptoapp

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp.databinding.ActivitySignUpBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.SignUpButton.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passEt.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()
            val name = binding.nameEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() && name.isNotEmpty() ) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener{
                                @Override
                                fun onComplete(task: Task<Void>) {
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "Verification Email Sent,Please verify your email", Toast.LENGTH_SHORT).show()
                                    }
                                    else{
                                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                            val intent = Intent(this, LoginActivity::class.java)
                            intent.putExtra("LAUNCHED_FROM_SIGN_UP",true)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}