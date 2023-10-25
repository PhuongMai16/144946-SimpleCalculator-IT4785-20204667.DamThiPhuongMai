package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.Stack

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        val textView : TextView = findViewById(R.id.text)
        var text = textView.text.toString()
        if(v?.id == R.id.khong){
            if(text.equals("0")) text = "0"
            else text = text + "0"
        }
        else if(v?.id == R.id.mot){
            if(text.equals("0")) text = "1"
            else text = text + "1"
        }
        else if(v?.id == R.id.hai){
            if(text.equals("0")) text = "2"
            else text = text + "2"
        }
        else if(v?.id == R.id.ba){
            if(text.equals("0")) text = "3"
            else text = text + "3"
        }
        else if(v?.id == R.id.bon){
            if(text.equals("0")) text = "4"
            else text = text + "4"
        }
        else if(v?.id == R.id.nam){
            if(text.equals("0")) text = "5"
            else text = text + "5"
        }
        else if(v?.id == R.id.sau){
            if(text.equals("0")) text = "6"
            else text = text + "6"
        }
        else if(v?.id == R.id.bay){
            if(text.equals("0")) text = "7"
            else text = text + "7"
        }
        else if(v?.id == R.id.tam){
            if(text.equals("0")) text = "8"
            else text = text + "8"
        }
        else if(v?.id == R.id.chin){
            if(text.equals("0")) text = "9"
            else text = text + "9"
        }
        else if(v?.id == R.id.congtru){
            if(text.count() > 1){
                text = text.substring(0, text.count() - 1)
            }
            else text = "0"
        }
        else if(v?.id == R.id.CE){
            text = "0"
        }
        else if(v?.id == R.id.C){
            text = "0"
        }
        else if(v?.id == R.id.chia){
            var i: Char = text[text.count()-1]
            if(i.equals('+') || i.equals('-')||i.equals('/')||i.equals('x')){
                text = text.substring(0, text.count() - 1)
            }
            text = text + "/"
        }else if(v?.id == R.id.cong){
            var i: Char = text[text.count()-1]
            if(i.equals('+') || i.equals('-')||i.equals('/')||i.equals('x')){
                text = text.substring(0, text.count() - 1)
            }
            text = text + "+"
        }
        else if(v?.id == R.id.x){
            var i: Char = text[text.count()-1]
            if(i.equals('+') || i.equals('-')||i.equals('/')||i.equals('x')){
                text = text.substring(0, text.count() - 1)
            }
            text = text + "x"

        }
        else if(v?.id == R.id.tru){
            var i: Char = text[text.count()-1]
            if(i.equals('+') || i.equals('-')||i.equals('/')||i.equals('x')){
                text = text.substring(0, text.count() - 1)
            }
            text = text + "-"
        }
        else if(v?.id == R.id.bang){
            var i: Char = text[text.count()-1]
            if(i.equals('+') || i.equals('-')||i.equals('/')||i.equals('x')){
                text = text.substring(0, text.count() - 1)
            }
            var rs = calculator(text)
            text = rs.toString()
        }
        textView.text = text
    }
    public fun calculator(text : String): Double{
        var rs: String = ""
        // chuyen trung to sang hau to
        var stack : Stack<Char> = Stack()
        for(i in text){
            if(i.equals('+') || i.equals('-')||i.equals('/')||i.equals('x')) {
                rs = rs + "*"
                if(stack.empty()) stack.push(i)
                else{
                    var tmp: Char = stack.peek()
                    if(privotOperator(tmp) >= privotOperator(i)){
                        rs = rs + stack.peek()
                        stack.pop()
                        stack.push(i)
                    }
                    else{
                        stack.push(i)
                    }
                }
            }else{
                rs = rs + i
            }
        }
        rs = rs +"*"
        while(!stack.empty()){
            rs = rs + stack.peek()
            stack.pop()
        }
        // tinh phep tinh cua hau to
        var st : Stack<Double> = Stack()
        var l: String = ""
        for(i in rs){
            if(i.equals('+') || i.equals('-')||i.equals('/')||i.equals('x')){
                var x = st.peek()
                st.pop()
                var y = st.peek()
                st.pop()
                if(i.equals('+')) x = x + y
                else if(i.equals('-')) x = x - y
                else if(i.equals('/')) x = x/y
                else if(i.equals('x')) x = x * y
                st.push(x)
            }
            else if(i.equals('*')){
                st.push(l.toDouble())
                l = ""
            }
            else{
                l = l + i
            }
        }
        return st.peek()
    }

    public  fun privotOperator(i : Char): Int{
        if(i.equals('/') || i.equals('x')) return 2
        else return 1
    }
}