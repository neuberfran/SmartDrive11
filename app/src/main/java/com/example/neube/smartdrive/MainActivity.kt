package com.example.neube.smartdrive

import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.neube.smartdrive.R
import com.google.firebase.database.DataSnapshot
import android.app.Activity
import androidx.lifecycle.ViewModel
import android.app.Application
import android.content.ContentValues
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.example.neube.smartdrive.controlamotores.SmartModel
import com.example.neube.smartdrive.controlamotores.SmartViewModel
import com.google.android.things.pio.Gpio
import java.util.*
import com.google.android.things.pio.GpioCallback
import com.google.android.things.pio.I2cDevice
import com.google.android.things.pio.PeripheralManager
import com.google.firebase.auth.FirebaseAuth
import com.example.neube.smartdrive.device.BoardDefaults
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import androidx.arch.core.util.Function

import com.neuberfran.androidthings.driver.SmartDrive.SmartDrive
import com.neuberfran.androidthings.driver.SmartDrive.SmartDrive.*
import timber.log.Timber
import java.io.IOException

class MainActivity : AppCompatActivity() {

    internal var TAG = MainActivity::class.simpleName

    var xdatabase = FirebaseDatabase.getInstance()
    var myRef = xdatabase.getReference()

    var fcmotoruma = myRef.child("home/fcmotoruma")
    var fcmotorumb = myRef.child("home/fcmotorumb")
    var fcmotordoisa = myRef.child("home/fcmotordoisa")
    var fcmotorboisb = myRef.child("home/fcmotordoisb")

    var direcaodois: Int? = null
    var parardois: Int? = null

    var direcaoum: Int? = null
    var pararum: Int? = null


//    var DirecaoDois = myRef.child("home/Direcao")
//    var PararDois = myRef.child("home/PararUm")
//    var DirecaoUm = myRef.child("home/DirecaoUm")
//    var PararUm = myRef.child("home/PararUm")
//
//    var direcatodoisa: Long? = null
//    var paradoisa: Long? = null
//
//    var direcaouma: Long? = null
//    var pararuma: Long? = null

    var mSmartDrive: SmartDrive? = null

    var janelauma: Int? = null

    internal var I2C_PIN_NAME = "I2C1"
    internal val I2C_ADDRESS_SMARTDRIVE = 0x1B
    internal var SmartDrive_COMMAND = 0x41

    // Supported I2C commands
    internal var CMD_R = 0x52
    internal var CMD_S = 0x53

    var FCMotorUmA: Gpio? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hotStockViewModel = ViewModelProviders.of(this).get(SmartViewModel::class.java)

        val hotStockLiveData = hotStockViewModel.hotStockLiveData

        hotStockLiveData.observe(this, Observer<SmartViewModel.Deserializer> {

        fun onChanged(hotStock: SmartModel?) {
                if (hotStock!=null) {

                    val pararum = hotStock.PararUm

                    Log.i(ContentValues.TAG, "Volto 191.1.00 191.1.00 191.1.00" + pararum)

                    val direcaoum = hotStock.DirecaoUm

                    Log.i(ContentValues.TAG, "Volto 191.2.00 191.2.00 191.2.00" + direcaoum)

                }
            }
        })

        Log.i(ContentValues.TAG, "Volto 193.00 193.00 193.00" + hotStockLiveData)

        Log.i(ContentValues.TAG, "Volto 194.00 194.00 194.00"+pararum)

        mSmartDrive = SmartDrive(I2C_PIN_NAME, I2C_ADDRESS_SMARTDRIVE)

        mSmartDrive?.command(CMD_R )

        var FCMotorUmA: Gpio? = null

        val manager = PeripheralManager.getInstance()

        try {
            // Step 1. Create GPIO connection.
            FCMotorUmA = manager.openGpio(BoardDefaults.getGPIOForButton21())

            Log.i(TAG, "99 99 99 ")
            // Step 2. Configure as an input.
            FCMotorUmA.setDirection(Gpio.DIRECTION_IN)
            // Step 3. Enable edge trigger events.
            FCMotorUmA.setEdgeTriggerType(Gpio.EDGE_FALLING)

            FCMotorUmA.registerGpioCallback(mCallback)

            if (FCMotorUmA.value) {

                fcmotoruma.setValue(1)

            }else if (!FCMotorUmA.value) {

               fcmotoruma.setValue(0)

            }

              fcmotoruma.setValue(1)

        } catch (e: IOException) {
            Log.e(TAG, "Error on PeripheralIO API", e)
        }
    }

    private val mCallback = GpioCallback { FCMotorUmA ->

        try {
            Log.i(TAG, "GPIO changed, button 101 101 101" + FCMotorUmA.value)

            if (FCMotorUmA.value) {

                fcmotoruma.setValue(1)

                Log.i(TAG, "passei 3 passei 3 passei 3")

            } else if (!FCMotorUmA.value) {

                fcmotoruma.setValue(0)

                Log.i(TAG, "passei 911 passei 911 passei 911"+FCMotorUmA.value)

                while (!FCMotorUmA.value) {

                 //   if (pararum == 0) {
                   // if (pararum!!.equals(0)) {

                        Log.i(TAG, "passei 411 passei 411 passei 411" + pararum)
                        Log.i(TAG, "passei 412 passei 412 passei 412" + direcaoum)
                        mSmartDrive?.SmartDrive_Run_Unlimited(SmartDrive_Motor_1, SmartDrive_Direction_Reverse, 100)

                 //   }else if (pararum == 1) {

                   //    mSmartDrive?.SmartDrive_Stop(SmartDrive_Motor_1, SmartDrive_Next_Action_Brake)

                  //  }

                    Log.i(TAG, "nao entrei no if and else if 912 912 912"+FCMotorUmA.value)

                }
            }

            fcmotoruma.setValue(1)

        } catch (e: IOException) {
            Log.w(TAG, "Error reading GPIO")
        }

        // Return true to keep callback active.
        true
    }

    // Clean
    @Throws(IOException::class)
    fun CleanRegisters(device: I2cDevice, address: Int) {

        device.writeRegByte(SmartDrive_COMMAND, CMD_R.toByte())
        //    device.writeRegByte(address, limpa)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Step 6. Close the resource
        if (FCMotorUmA!=null) {
            FCMotorUmA!!.unregisterGpioCallback(mCallback)
            try {
                FCMotorUmA!!.close()
            } catch (e: IOException) {
                Log.e(TAG, "Error on PeripheralIO API", e)
            }

        }
        mSmartDrive?.close()

        val i2c_device: I2cDevice? = null
        if (i2c_device!=null) {
            CleanRegisters(i2c_device, I2C_ADDRESS_SMARTDRIVE)
        }
    }

    private fun wait1sec() = Thread.sleep(5000)

}

