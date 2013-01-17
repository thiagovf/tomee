/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.openejb.arquillian.common;


import org.jboss.arquillian.container.spi.ConfigurationException;
import org.jboss.arquillian.container.spi.client.container.ContainerConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Properties;

public class TomEEConfiguration implements ContainerConfiguration {

    private int httpPort = 8080;
    private int stopPort = 8005;
    private String dir = System.getProperty("java.io.tmpdir") + "/arquillian-apache-tomee";
    private boolean plusContainer = true;
    private String tomcatVersion = null;
    private String openejbVersion = "1.0.0-beta-1";
    private String systemProperties = "openejb.log.factory = org.apache.openejb.util.Log4jLogStreamFactory\n" +
            "openejb.logger.external = true\n" +
            "log4j.rootLogger = info, stdout\n" +
            "log4j.appender.stdout = org.apache.log4j.ConsoleAppender\n" +
            "log4j.appender.stdout.layout = org.apache.log4j.SimpleLayout";

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public int getStopPort() {
        return stopPort;
    }

    public void setStopPort(int stopPort) {
        this.stopPort = stopPort;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public boolean isPlusContainer() {
        return plusContainer;
    }

    public void setPlusContainer(boolean plusContainer) {
        this.plusContainer = plusContainer;
    }
    
    public String getTomcatVersion() {
		return tomcatVersion;
	}

	public void setTomcatVersion(String tomcatVersion) {
		this.tomcatVersion = tomcatVersion;
	}

	public String getOpenejbVersion() {
		return openejbVersion;
	}

	public void setOpenejbVersion(String openejbVersion) {
		this.openejbVersion = openejbVersion;
	}

    public String getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(String systemProperties) {
        this.systemProperties = systemProperties;
    }

    public Properties systemProperties() {
        final Properties p = new Properties();
        final Reader reader = new StringReader(systemProperties);
        try {
            p.load(new InputStream() {
                @Override
                public int read() throws IOException {
                    return reader.read();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("can't read " + systemProperties);
        }
        return p;
    }

    public void validate() throws ConfigurationException {
    }
}