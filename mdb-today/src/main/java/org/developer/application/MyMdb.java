/*
 * Copyright 2012 David Blevins
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.developer.application;

import com.superconnectors.telnet.api.TelnetListener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "prompt", propertyValue = "pronto>")
})
public class MyMdb implements TelnetListener {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat();
    private final Properties properties = new Properties();

    @Override
    public String doDate() {
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }

    @Override
    public String doJoke() {
        return "Where do hamburgers go to dance?  To a meatball.";
    }

    @Override
    public int doAdd(int a, int b) {
        return a + b;
    }

    @Override
    public String doGet(String key) {
        return properties.getProperty(key);
    }

    @Override
    public String doSet(String key, String value) {
        final Object old = properties.setProperty(key, value);
        final StringBuilder sb = new StringBuilder();
        sb.append("set ").append(key).append(" to ").append(value);
        sb.append("\n");
        if (old != null) {
            sb.append("old value: ").append(old);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String doList(Pattern pattern) {
        if (pattern == null) pattern = Pattern.compile(".*");
        final StringBuilder sb = new StringBuilder();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            final String key = entry.getKey().toString();
            if (pattern.matcher(key).matches()) {
                sb.append(key).append(" = ").append(entry.getValue()).append("\n");
            }
        }
        return sb.toString();
    }
}