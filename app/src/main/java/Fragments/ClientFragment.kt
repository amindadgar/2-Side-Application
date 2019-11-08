package Fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.amindadgar.a2sideapp.R
import kotlinx.android.synthetic.main.fragment_client.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.Socket

class ClientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_client, container, false)
        val SendButton = layout.findViewById<Button>(R.id.SendButton)


        val dialogBuilder = AlertDialog.Builder(activity)
        val inflater1 = this.layoutInflater
        val dialogView = inflater1.inflate(R.layout.dialog_layout, null)
        dialogBuilder.setView(dialogView)

        val editText = dialogView.findViewById<EditText>(R.id.dialog_text)
        var ip:String?=null
        dialogBuilder.setTitle("Enter Your Server IP")
        dialogBuilder.setPositiveButton("Save", { _ , _ ->
            ip = editText.text.toString()

            Toast.makeText(activity,"ip: $ip",Toast.LENGTH_LONG).show()
        })
        SendButton.setOnClickListener {
            connection(ip!!)
            toast("Sending to server")
        }


        val b = dialogBuilder.create()
        b.show()

        return layout
    }
    fun connection(ip:String){
        doAsync {
            val host = ip
            val port = 6785
            val clientSocket: Socket = Socket(host, port)
            val inFromServer: BufferedReader =
                BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            val outToServer: DataOutputStream =
                DataOutputStream(clientSocket.getOutputStream())

            outToServer.writeBytes(toServerText.text.toString())
            outToServer.flush()

            val fromServerText: String = inFromServer.readLine()
            if (fromServerText != "")
                uiThread {
                    Toast.makeText(context, fromServerText, Toast.LENGTH_LONG).show()
                }
        }
//            clientSocket.close()

    }

}
