/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * zylkanexy server: Helps you to develop easily AJAX web applications 
 *                   by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/zylkanexy
 * 
 * zylkanexy server is distributed under the MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.daw.helper.statics;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class JsonMessage {

    public static String getJsonMsg(String strStatus, String strMessage) {
        return "{\"status\":" + strStatus + ",\"message\":" + strMessage + "}";
//        Map<String, String> data = new HashMap<>();
//        data.put("status", strStatus);
//        data.put("message", strMessage);
//        Gson gson = new Gson();
//        return gson.toJson(data);
    }

    public static String getJson(String strStatus, String strMessage) {
        return "{\"status\":" + strStatus + ",\"message\":" + strMessage + "}";
    }
}
